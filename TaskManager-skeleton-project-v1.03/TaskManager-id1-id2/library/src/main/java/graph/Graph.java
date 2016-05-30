package graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by rina.berlin on 4/11/2016.
 */

public class Graph<T> {
    private HashSet<Node> vertexes;
    private HashSet<Node> sources;

    public Graph(HashSet<T> vertexes, HashSet<Edge> edges){
        this.vertexes = new HashSet<Node>();
        this.sources = new HashSet<Node>();
        for (T v : vertexes) {
            addVertex(v);
        }
        for (Edge e : edges) {
            try {
                addEdge(e);
            } catch (Exception exp){
                System.out.println(exp.getMessage());
            }
        }
        initializeSources();
    }

    private void initializeSources() {
        sources.addAll(vertexes.stream().filter(v -> v.getInDegree() == 0).
                collect(Collectors.toCollection(ArrayList<Node>::new)));
    }

    private void addVertex(T v){
        Node node = new Node(v);
        vertexes.add(node);
    }
    private void addEdge(Edge edge) throws Exception{
        Optional<Node> source = vertexes.stream().filter(node->node.getVertexData().equals(edge.getSource())).findFirst();
        Optional<Node> target = vertexes.stream().filter(node->node.getVertexData().equals(edge.getTarget())).findFirst();
        try {
            source.get().addNeighbor(edge.getTarget());
            target.get().incInDegree();
        } catch (NullPointerException e){
            throw new Exception("node wasn't found in add edge");
        }
    }

    public void removeVertex(Node node){
        List<Integer> list = node.getNeighbors();
        sources.remove(node);
        vertexes = vertexes.stream().map(v-> {
            if(list.contains(v.getVertexData())) {
                v.decInDegree();
                if (v.getInDegree() == 0)
                    sources.add(v);
            }
            return v;
        }).collect(Collectors.toCollection(HashSet<Node>::new));
        vertexes.remove(node);
    }

    public Optional<Node> getSource(){
        if (sources.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(this.sources.iterator().next());
    }

    public HashSet<Node> getVertexes(){
        return vertexes;
    }

    public HashSet<Node> getSources(){return sources;}

    public Boolean isEmpty(){
        return vertexes.isEmpty();
    }
}
