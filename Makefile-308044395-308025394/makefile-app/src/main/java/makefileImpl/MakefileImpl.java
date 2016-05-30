package makefileImpl;

import com.google.inject.Inject;
import cs.technion.ac.il.sd.ExternalCompiler;
import cs.technion.ac.il.sd.app.Makefile;
import graph.Graph;
import graph.Node;
import makefileVertex.FileOrAssignment;
import makefileVertex.MakefileVertex;
import parser.Parser;
import toposort.ToposortImpl;

import java.io.File;
import java.util.*;

/**
 * Created by rina.berlin on 5/7/2016.
 */
public class MakefileImpl implements Makefile {
    private final ExternalCompiler external;

    @Inject
    public MakefileImpl(ExternalCompiler external){
        this.external = external;
    }

    @Override
    public void processFile(File file) {
        Graph<MakefileVertex> graph = Parser.parse(file);
        List<MakefileVertex> shouldCompile = new LinkedList<>();
        ToposortImpl<MakefileVertex> toposort = new ToposortImpl<>();
        Optional<List<Node<MakefileVertex>>> sorted = toposort.getToposort(graph);
        if (!sorted.isPresent()){
            external.fail();
            return;
        }
        List<Node<MakefileVertex>> sortedLst = sorted.get();
        for (int i = sortedLst.size()-1; i >= 0; i--){
            Node<MakefileVertex> node = sortedLst.get(i);
            if ((isFile(node.getVertexID()) && external.wasModified(node.getVertexID().GetName()))
                    || checkNeighbors(node.getNeighbors(), shouldCompile)){
                shouldCompile.add(node.getVertexID());
            }
            if (shouldCompile.contains(node.getVertexID())){
                external.compile(node.getVertexID().GetName());
            }
        }
    }

    private boolean isFile (MakefileVertex v){
        return v.GetType() == FileOrAssignment.File;
    }

    private boolean checkNeighbors(List<MakefileVertex> neighbors, List<MakefileVertex> shouldCompile) {
        for (MakefileVertex v: neighbors){
            if ((external.wasModified(v.GetName())) || (shouldCompile.contains(v))){
                return true;
            }
        }
        return false;
    }
}
