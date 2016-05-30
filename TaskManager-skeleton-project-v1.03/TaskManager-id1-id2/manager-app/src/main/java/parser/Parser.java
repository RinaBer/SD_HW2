package parser;

import graph.Edge;
import graph.Graph;
import managerVertex.ManagerVertex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by rina.berlin on 5/7/2016.
 */
public class Parser {
    public static Graph<ManagerVertex> parse(File file){
        return new Graph(new HashSet<>(), new HashSet<>());
    }
    //TODO: implement
    public static int getCpu(File file) {
        return 0;
    }
    //TODO: implement
    public static int parseMemories(File file) {
        return 0;
    }
    //TODO: implement
    public static int parseDisks(File file) {
        return 0;
    }
//        HashMap<String, FileOrAssignment> fileToType = new HashMap<>();
//        HashMap<String, List<String>> fileToDependencies = new HashMap<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
//            String line = null;
//            String[] lineArr = null;
//            FileOrAssignment type = FileOrAssignment.Unknown;
//            while ((line = reader.readLine()) != null) {
//                if (isNullOrBlank(line)) continue;
//                if (line.contains("=")){
//                    lineArr = Arrays.stream(line.split("=")).map(String::trim).toArray(String[]::new);
//                    type = FileOrAssignment.Assignment;
//                }
//                if (line.contains(":")){
//                    lineArr = Arrays.stream(line.split(":")).map(String::trim).toArray(String[]::new);
//                    type = FileOrAssignment.File;
//                }
//                String name =lineArr[0];
//                fileToType.put(name, type);
//                if (lineArr.length > 1) {
//                    String[] filesDependencies = Arrays.stream(lineArr[1].split(",")).map(String::trim).toArray(String[]::new);
//                    fileToDependencies.put(name, Arrays.asList(filesDependencies));
//                    for (String fileDependency : filesDependencies)
//                        if (!fileToType.containsKey(fileDependency))
//                            fileToType.put(fileDependency, FileOrAssignment.File);
//                }
//            }
//        } catch (IOException e){
//            System.out.println(e.getMessage());
//        }
//        HashSet<ManagerVertex> vertexes = fileToType.keySet().stream()
//                .map(key -> new ManagerVertex(key, fileToType.get(key)))
//                .collect(Collectors.toCollection(HashSet::new));
//        HashSet<Edge<ManagerVertex>> edges = new HashSet<>();
//        for (String key :
//                fileToDependencies.keySet()) {
//            for (String s :
//                    fileToDependencies.get(key)) {
//                Optional<ManagerVertex> source = vertexes.stream().filter(n -> n.GetName().equals(key)).findFirst();
//                Optional<ManagerVertex> target = vertexes.stream().filter(n -> n.GetName().equals(s)).findFirst();
//                try {
//                    edges.add(new Edge<>(source.get(), target.get()));
//                    source.get();
//                    target.get();
//                } catch (NullPointerException e){
//                    System.out.println(String.format("failed to create edge %s->%s", key, s));
//                }
//            }
//        }
//        return new Graph(vertexes, edges);
//    }
//
//    public static boolean isNullOrBlank(String param) {
//        return param == null || param.trim().length() == 0 || param.equals(System.getProperty("line.seperator"));
//    }
}
