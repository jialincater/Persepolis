package baysian;

import java.util.*;

public abstract class Learn {	
	
	public abstract Digraph<String> learnStructures(Digraph<String> Nini, data da, int restime);
	
	public static Digraph<String> isCopyOf(Digraph<String> org){
		Digraph<String> NewGraph = new Digraph<String>(org);
		return NewGraph;
	}
	
	public abstract Digraph<String> dagGen(Digraph<String> ini,List<String> Vl);

}
