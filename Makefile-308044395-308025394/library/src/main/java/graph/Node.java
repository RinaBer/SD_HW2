package graph;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by rina.berlin on 4/11/2016.
 */
public class Node<T> {
    private T v;
    private List<T> neighbors;
    private int inDegree;

    public Node(T v){
        this.v = v;
        neighbors = new LinkedList<>();
        this.inDegree = 0;
    }

    @Override
    public boolean equals(Object n){
        if (this == n){
            return true;
        }
        if (!(n instanceof Node)) {
            return false;
        }
        Node other = (Node)n;
        return this.v == other.v;
    }

//    @Override
//    public T hashCode(){
//        return this.v;
//    }

    public T getVertexID(){
        return this.v;
    }

    int getInDegree(){
        return this.inDegree;
    }

    void incInDegree(){
        this.inDegree++;
    }

    void decInDegree(){
        if (this.inDegree <= 0){
            return; //TODO: maybe it should throw an exception
        }
        this.inDegree--;
    }

    public List<T> getNeighbors(){
        return this.neighbors;
    }

    void addNeighbor(T neighbor){
        this.neighbors.add(neighbor);
    }
}
