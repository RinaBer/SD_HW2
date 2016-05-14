package parser;

import graph.Edge;
import graph.Graph;
import makefileVertex.FileOrAssignment;
import makefileVertex.MakefileVertex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by rina.berlin on 5/7/2016.
 */
public class Parser {
    public static Graph<MakefileVertex> parse(File file){
        HashMap<String, FileOrAssignment> fileToType = new HashMap<>();
        HashMap<String, List<String>> fileToDependencies = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line = null;
            String[] lineArr = null;
            FileOrAssignment type = FileOrAssignment.Unknown;
            while ((line = reader.readLine()) != null) {
                if (line.contains("=")){
                    lineArr = Arrays.stream(line.split("=")).map(String::trim).toArray(String[]::new);
                    type = FileOrAssignment.Assignment;
                }
                if (line.contains(":")){
                    lineArr = Arrays.stream(line.split(":")).map(String::trim).toArray(String[]::new);
                    type = FileOrAssignment.File;
                }
                String name =lineArr[0];
                fileToType.put(name, type);
                if (lineArr.length > 1) {
                    String[] filesDependencies = Arrays.stream(lineArr[1].split(",")).map(String::trim).toArray(String[]::new);
                    fileToDependencies.put(name, Arrays.asList(filesDependencies));
                    for (String fileDependency : filesDependencies) {
                        if (!fileToType.containsKey(fileDependency))
                            fileToType.put(fileDependency, FileOrAssignment.File);
                    }
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        HashSet<MakefileVertex> vertexes = fileToType.keySet().stream()
                .map(key -> new MakefileVertex(key, fileToType.get(key)))
                .collect(Collectors.toCollection(HashSet::new));
        HashSet<Edge<MakefileVertex>> edges = new HashSet<>();
        for (String key :
                fileToDependencies.keySet()) {
            for (String s :
                    fileToDependencies.get(key)) {
                Optional<MakefileVertex> source = vertexes.stream().filter(n -> n.GetName().equals(key)).findFirst();
                Optional<MakefileVertex> target = vertexes.stream().filter(n -> n.GetName().equals(s)).findFirst();
                try {
                    edges.add(new Edge<>(source.get(), target.get()));
                    source.get();
                    target.get();
                } catch (NullPointerException e){
                    System.out.println(String.format("failed to create edge %s->%s", key, s));
                }
            }
        }
        return new Graph(vertexes, edges);
    }
}
