package graph;

/**
 * Created by rina.berlin on 4/14/2016.
 */
public class Edge<T> {
    T source;
    T target;

    @Override
    public boolean equals(Object e){
        if (e == this){
            return true;
        }
        if (!(e instanceof Edge)){
            return false;
        }
        Edge edge = (Edge)e;
        return (this.source == edge.source) && (this.target == edge.target);
    }

//    @Override
//    public int hashCode(){
//        return source + target;
//    }

    public Edge(T source, T target){
        this.source = source;
        this.target = target;
    }

    public T getSource() {
        return this.source;
    }

    public T getTarget() {
        return this.target;
    }
}
