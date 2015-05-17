package baysian;

import java.util.*;
/**
 * Abstract class that present a structure that holds the learning classes
 * @author Cater
 *
 */
public abstract class Learn {	
	/**
	 * To learn a network whatever it is BN or DBN
	 * @param Nini		is the Initial Network of Network, which don't have any edge
	 * @param da		is object of the data class that contains the data and parameters of train file
	 * @param restime	is the time of random restart will run
	 * @param flag		is a flag describe whether we should use MDL or LL
	 * @return	A Digraph of String that contains the graph that fits our train data
	 * @author Cater
	 */
	public abstract Digraph<String> learnStructures(Digraph<String> Nini, data da, int restime,boolean flag);
	
	/**
	 * To copy a graph using the copy constructor
	 * @param org	original Digraph to be copied
	 * @return
	 */
	public static Digraph<String> isCopyOf(Digraph<String> org){
		Digraph<String> NewGraph = new Digraph<String>(org);
		return NewGraph;
	}
	
	/**
	 * Generate a random DAG
	 * with ramdom number of edges and random edges
	 * @param ini		is the Initial Network
	 * @param Vl		variable list that contains that name of the vertex
	 * @return	a random DAG
	 * @author Cater
	 */
	public abstract Digraph<String> dagGen(Digraph<String> ini,List<String> Vl);

}
