package makefileImpl;

import com.google.inject.Inject;
import cs.technion.ac.il.sd.ExternalCompiler;
import cs.technion.ac.il.sd.app.Makefile;
import graph.Graph;
import graph.Node;
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
        HashSet<Node> nodes = graph.getVertexes();
        for (Node<String> n: nodes){
            if (external.wasModified(n.getVertexID()) ||
                    checkNeighbors(n.getNeighbors())){
                shouldCompile.add(n.getVertexID());
            }
        }
        ToposortImpl<String> toposort = new ToposortImpl<>();
        Optional<List<String> > sorted = toposort.getToposort(graph);
        if (sorted == null){
            external.fail();
            return;
        }
        for (int i = sorted.get().size(); i > 0; i--){
            if (shouldCompile.contains(sorted.get().get(i))){
                external.compile(sorted.get().get(i));
            }
        }
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
