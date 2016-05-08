package makefileImpl;

import com.google.inject.Inject;
import cs.technion.ac.il.sd.ExternalCompiler;
import cs.technion.ac.il.sd.app.Makefile;
import graph.Graph;
import graph.Node;
import jdk.nashorn.internal.objects.NativeArray;
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
        Graph<String> graph = Parser.parse(file);
        List<String> shouldCompile = new LinkedList<>();
        ToposortImpl<String> toposort = new ToposortImpl<>();
        Optional<List<Node<String>>> sorted = toposort.getToposort(graph);
        if (sorted == null){
            external.fail();
            return;
        }
        for (int i = sorted.get().size()-1; i >= 0; i--){
            Node<String> node = sorted.get().get(i);
            if ((isFile(node.getVertexID()) && external.wasModified(node.getVertexID()))
                    || checkNeighbors(node.getNeighbors())){
                shouldCompile.add(node.getVertexID());
            }
            if (shouldCompile.contains(node)){
                external.compile(node.getVertexID());
            }
        }
    }

    // TODO: implement
    private boolean isFile (String s){
        return true;
    }

    private boolean checkNeighbors(List<String> neighbors) {
        for (String s: neighbors){
            if (external.wasModified(s)){
                return true;
            }
        }
        return false;
    }
}
