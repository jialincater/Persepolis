package baysian;

import java.util.*;

public class Learn {
	public Digraph<String> learnStructures(Digraph<String> Nini, data da){
		Digraph<String> Nres = Nini;
		Digraph<String> Np = Nres;
		Integer Sres=0;
		boolean flag= false;
		LinkedList<Digraph<String>> TABU = new LinkedList<Digraph<String>>();
//		Need to get the right flag
		while(flag){
			for(int i =0;i!=da.getN();++i){
				Map<Digraph<String>,Integer> temp = new HashMap<Digraph<String>,Integer>();
				Digraph<String> t = new Digraph<String>(Np);
				for(int j=0;j!=da.getN();j++){
//					if i == j, it must not be a DAG
					if(i==j)	continue;
//					Test if t contains the arc i->j
					else if(t.contains(da.getVl().get(i),da.getVl().get(j))){
//						if t contains i->j, try to delete or reverse one 
						Digraph<String> tt = new Digraph<String>(t);
						t.remove(da.getVl().get(i), da.getVl().get(j));
						tt.reverse(da.getVl().get(i), da.getVl().get(j));
						if(t.isDag()&&!TABU.contains(t)){
							temp.put(t, null);
							TABU.add(t);
						}
						if(tt.isDag()&&!TABU.contains(tt)){
							temp.put(tt, null);
							TABU.add(tt);
						}
					}
//					if t doesn't contains i->j, try to add one 
					else{
						t.add(da.getVl().get(i), da.getVl().get(j));
						if(t.isDag()&&!TABU.contains(t)){
							temp.put(t, null);
							TABU.add(t);
						}			
					}
				}
//				Scoring the temp HERE
				
//				Travel through the temp MAP, get the best neighbour 
				Integer max=Integer.MIN_VALUE;
				Digraph<String> Npp=new Digraph<String>();
				Iterator<HashMap.Entry<Digraph<String>, Integer>> entries = temp.entrySet().iterator();  

				while (entries.hasNext()) {  
				    Map.Entry<Digraph<String>, Integer> entry = entries.next();  
				    if(entry.getValue()>max){
				    	Npp=entry.getKey();
				    	max=entry.getValue();
				    }
				}
//				if the best neighbor is better than current result, replace it.
				if(max>Sres){
					Nres=Npp;
				}
				Np=Npp;
			}
			
		}
		return Nres; 
	}
}
