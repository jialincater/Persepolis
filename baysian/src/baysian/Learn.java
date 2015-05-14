package baysian;

import java.util.*;

public class Learn {	
	
	/*
	To Learn a Baysian Network, not Dynamic
	*/
	public Digraph<String> learnDBStructures(Digraph<String> Nini, data da, int restime){
//		Nini is a Digraph that without any edge
		Digraph<String> Nres = Nini;
		Double Sres=Double.MIN_VALUE;
//		Nres is the result of the Digraph
//		The biggest of every step
		Double SNp=Double.MIN_VALUE;
		Digraph<String> Np = Nres;
//		restart time here
		int timeCNT = 0;
		LinkedList<Digraph<String>> TABU = new LinkedList<Digraph<String>>();
		TABU.add(Nini);
//		Need to get the right flag
		while(timeCNT<restime){
			Map<Digraph<String>,Double> temp = new HashMap<Digraph<String>,Double>();
			Digraph<String> graphToFix = new Digraph<String>(Np);
//			Have a N-times Loop
//			Fix every Vertex each time
			for(int i =0;i!=da.getN();++i){	
//				Have a N-times Loop
//				Fix every Vertex each time
				for(int j=0;j!=da.getN();j++){
//					if i == j, it must not be a DAG
					if(i==j)	continue;
//					Test if t contains the edge i->j
					else if(graphToFix.contains(da.getVl().get(i),da.getVl().get(j))){
//						if t contains i->j, try to delete or reverse one 
						Digraph<String> graphContainsEdgeRemove = new Digraph<String>(graphToFix);
						Digraph<String> graphContainsEdgeReverse = new Digraph<String>(graphToFix);
						graphContainsEdgeReverse.remove(da.getVl().get(i), da.getVl().get(j));
						graphContainsEdgeRemove.reverse(da.getVl().get(i), da.getVl().get(j));
						if(graphContainsEdgeRemove.isDag()&&!TABU.contains(graphContainsEdgeRemove)){
							temp.put(graphContainsEdgeRemove, null);
							TABU.add(graphContainsEdgeRemove);
						}
						if(graphContainsEdgeReverse.isDag()&&!TABU.contains(graphContainsEdgeReverse)){
							temp.put(graphContainsEdgeReverse, null);
							TABU.add(graphContainsEdgeReverse);
						}
					}
//					if t doesn't contains i->j, try to add one 
					else{
						Digraph<String> graphNotContainsEdgeAdd = new Digraph<String>(graphToFix);
						graphNotContainsEdgeAdd.add(da.getVl().get(i), da.getVl().get(j));
						if(graphNotContainsEdgeAdd.isDag()&&!TABU.contains(graphNotContainsEdgeAdd)){
							temp.put(graphNotContainsEdgeAdd, null);
							TABU.add(graphNotContainsEdgeAdd);
						}			
					}
				}
//				
			}
//			Scoring the temp HERE
//			Travel through the temp MAP, get the best neighbour 
			Double max=Double.MIN_VALUE;
			Digraph<String> Npp=new Digraph<String>();
			Iterator<HashMap.Entry<Digraph<String>, Double>> entries = temp.entrySet().iterator();  

			while (entries.hasNext()) {  
			    Map.Entry<Digraph<String>, Double> entry = entries.next();
//			    To score 
			    double score = llscore(da.getCore(),entry.getKey());
			    entry.setValue(score);
			    if(entry.getValue()>max){
			    	Npp=entry.getKey();
			    	max=entry.getValue();
			    }
			}
//			if the best neighbor is better than current result, replace it.
			if(max>SNp){
				Np=Npp;
				SNp=max;
			}
			else{
//				Tudo: random restart should assign Nini another value
				if(timeCNT <restime){
					timeCNT++;
					Np=dagGen(Nini,da.getVl());
					continue;
				}
				else{
					break;
				}
			}
			if(max>Sres){
				Nres = Npp;
			}
			Np=Npp;
		}
		return Nres; 
	}
	
	public Digraph<String> dagGen(Digraph<String> ini,List<String> Vl){
		Digraph<String> Res = ini;
		int N = ini.neighbors.size();
		int NumberOfEdge = (int)(Math.random()*(N*N-N)/2);
		for(int i =0;i!=NumberOfEdge;++i){
			int x=(int)(Math.random()*N),y=(int)(Math.random()*N);
			if(x!=y){
				Res.add(Vl.get(x), Vl.get(y));
				if(false==Res.isDag()){
					Res.remove(Vl.get(x), Vl.get(y));
					i--;
				}
			}
			else{
				i--;
				continue;
			}
		}
		return Res;
	}
}
