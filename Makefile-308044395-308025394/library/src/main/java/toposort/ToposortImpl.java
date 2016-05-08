package toposort;

import graph.Edge;
import graph.Graph;
import graph.Node;

import java.util.*;

/**
 * Created by rina.berlin on 4/11/2016.
 */
public class ToposortImpl<T> {
    private List<Node<T> > sorted;

    public ToposortImpl() {
        sorted = new ArrayList<>();
    }

    public Optional<List<Node<T>>> getToposort(Graph<T> graph) {
        while (!graph.isEmpty()){
            Optional<Node> source = graph.getSource();
            Node<T> node;
            try {
                 node = source.get();
            } catch( NoSuchElementException e){
                return Optional.ofNullable(null);
            }
            graph.removeVertex(node);
            sorted.add(node);
        }
        return Optional.of(this.sorted);
    }
}
