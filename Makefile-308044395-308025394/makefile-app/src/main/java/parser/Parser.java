package parser;

import graph.Edge;
import graph.Graph;

import java.io.File;
import java.util.HashSet;

/**
 * Created by rina.berlin on 5/7/2016.
 */
public class Parser {
    public static Graph<String> parse(File file) {
        HashSet<String> vertexes = new HashSet<>();
        HashSet<Edge> edges = new HashSet<>();
        return new Graph<>(vertexes, edges);
    }
}
