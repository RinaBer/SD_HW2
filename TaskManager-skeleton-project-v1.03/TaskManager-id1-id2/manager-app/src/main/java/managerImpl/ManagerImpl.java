package managerImpl;

import com.google.inject.Inject;
import cs.technion.ac.il.sd.ExternalManager;
import cs.technion.ac.il.sd.app.ManagerApp;
import graph.Graph;
import managerVertex.ManagerVertex;
import parser.Parser;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by rina.berlin on 5/30/2016.
 */
public class ManagerImpl implements ManagerApp {
    private int cpus;
    private int memories;
    private int disks;
    private final ExternalManager external;

    @Inject
    public ManagerImpl(ExternalManager external) {
        this.external = external;
    }

    @Override
    public void processFile(File file) {
        cpus = Parser.getCpu(file);
        memories = Parser.parseMemories(file);
        disks = Parser.parseDisks(file);
        Graph<ManagerVertex> graph = Parser.parse(file);
        graph.getVertexes().stream()
                .map(node -> checkResources((ManagerVertex) node.getVertexData()))
                .forEach(hasResources -> {if (!hasResources) external.fail();});
        findNextTask(graph);
    }

    private void findNextTask(Graph<ManagerVertex> graph) {
        while (!graph.isEmpty()){
            ArrayList<ManagerVertex> sources = graph.getSources().stream()
                    .map(node -> (ManagerVertex) node.getVertexData())
                    .collect(Collectors.toCollection(ArrayList::new));
            sources.sort((o1, o2) -> o1.getPriority()-o2.getPriority());
            for (ManagerVertex s : sources) {
                if (checkResources(s)){
                    runNextTask(graph, s);
                    break;
                }
            }
        }
    }

    private void runNextTask(Graph<ManagerVertex> graph, ManagerVertex s) {
        //removing the chosen task to run from the graph
        graph.getVertexes().stream()
                .filter(node -> ((ManagerVertex) node.getVertexData()).getName().equals(s.getName()))
                .forEach(graph::removeVertex);
        cpus -= s.getCpu();
        memories -= s.getMemory();
        disks -= s.getDisk();
        external.run(s.getName(), s.getCpu(), s.getMemory(), s.getDisk(), () -> findNextTask(graph));
    }

    private boolean checkResources(ManagerVertex s) {
        return s.getCpu() <= cpus && s.getMemory() <= memories && s.getDisk() <= disks;
    }
}
