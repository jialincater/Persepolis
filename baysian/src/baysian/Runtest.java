package baysian;

//import org.jgrapht.*;
import org.jgrapht.experimental.dag.DirectedAcyclicGraph;
import org.jgrapht.graph.*;

public final class Runtest
{
    public static void main(String [] args)
    {
    	DirectedAcyclicGraph<Object, DefaultEdge> dag;
    	dag = new DirectedAcyclicGraph<Object,DefaultEdge>(DefaultEdge.class);
    	String v0 = "miao"; 
    	dag.addVertex(v0);
    	String v1 = "wow";
    	dag.addVertex(v1);
    	dag.addEdge(v1, v0);
    	dag.addVertex("shit");
    	dag.addEdge("shit",v1);
    	dag.addEdge("shit", "miao");
    	System.out.println(dag);
    }
}

