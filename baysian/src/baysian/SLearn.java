package baysian;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * class can do structure learning of a BN
 * @author Cater
 * @see Learn
 */
public class SLearn extends Learn{
	
	/**
	 * Generate a random DAG
	 * with random number of edges and random edges
	 * designed for BN
	 * No vertex have more than 3 parents
	 * @param ini		is the Initial Network of BN, which contains N vertex and without any edge
	 * @param Vl		variable list that contains that name of the vertex
	 * @return	a random DAG
	 * @author Cater
	 */
	public Digraph<String> dagGen(Digraph<String> ini,List<String> Vl){
		Digraph<String> Res = ini;
		int N = ini.neighbors.size();
		int CNT = 0;
		int NumberOfEdge = (int)(Math.random()*(N*N-N)/2);
		for(int i =0;i!=NumberOfEdge;++i){
			CNT++;
			if(CNT>50){
				break;
			}
			int x=(int)(Math.random()*N),y=(int)(Math.random()*N);
			if(Res.getPais(Vl.get(y)).size()>=3||Res.getPais(Vl.get(x)).size()>=3){
				i--;
				continue;
			}
			if(x!=y){
				if(!Res.contains(Vl.get(x), Vl.get(y))){
					Res.add(Vl.get(x), Vl.get(y));
				}
				else{
					i--;
					continue;
				}
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
	
	/**
	* To Learn a Baysian Network
	* @param Nini	 	is the Initial Network of DBN, which contains 2N vertex and without any edge
	* @param da 		is object of the data class that contains the data and parameters of train file
	* @param restime	is the time of random restart will run
	* @param SM			is a flag describe whether we should use MDL or LL
	* @return	A Digraph of String that contains the graph that fits our train data
	* @author Cater
	*/
	public Digraph<String> learnStructures(Digraph<String> Nini, data da, int restime,boolean SM){
//		Nini is a Digraph that without any edge
		Digraph<String> Nres = Nini;
		Double Sres=Double.NEGATIVE_INFINITY;
//		Nres is the result of the Digraph
//		The biggest of every step
		Double SNp=Double.NEGATIVE_INFINITY;
		Digraph<String> Np = Nres;
//		restart time here
		int timeCNT = 0;
		LinkedList<Digraph<String>> TABU = new LinkedList<Digraph<String>>();
		TABU.add(Nini);
//		Start the loop, time of loop is constrained by restime
		while(timeCNT<restime){
			Map<Digraph<String>,Double> temp = new HashMap<Digraph<String>,Double>();
			Digraph<String> graphToFix = isCopyOf(Np);
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
						Digraph<String> graphContainsEdgeRemove = isCopyOf(graphToFix);
						Digraph<String> graphContainsEdgeReverse = isCopyOf(graphToFix);
						graphContainsEdgeReverse.remove(da.getVl().get(i), da.getVl().get(j));
						graphContainsEdgeRemove.reverse(da.getVl().get(i), da.getVl().get(j));
						if(graphContainsEdgeRemove.isDag()&&!TABU.contains(graphContainsEdgeRemove)){
//							System.out.println(graphContainsEdgeRemove);
							temp.put(graphContainsEdgeRemove, null);
							TABU.add(graphContainsEdgeRemove);
						}
						if(graphContainsEdgeReverse.isDag()&&!TABU.contains(graphContainsEdgeReverse)){
//							System.out.println(graphContainsEdgeReverse);
							temp.put(graphContainsEdgeReverse, null);
							TABU.add(graphContainsEdgeReverse);
						}
					}
//					if t doesn't contains i->j, try to add one 
					else{
						Digraph<String> graphNotContainsEdgeAdd = isCopyOf(graphToFix);
						graphNotContainsEdgeAdd.add(da.getVl().get(i), da.getVl().get(j));
						if(graphNotContainsEdgeAdd.getPais(da.getVl().get(j)).size()>3){
							continue;
						}
						if(graphNotContainsEdgeAdd.isDag()&&!TABU.contains(graphNotContainsEdgeAdd)){
//							System.out.println(graphNotContainsEdgeAdd);
							temp.put(graphNotContainsEdgeAdd, null);
							TABU.add(graphNotContainsEdgeAdd);
						}			
					}
				}
//				
			}
//			Scoring the temp HERE
//			Travel through the temp MAP, get the best neighbour 
			Double max=Double.NEGATIVE_INFINITY;
			Digraph<String> Npp=new Digraph<String>();
			Iterator<HashMap.Entry<Digraph<String>, Double>> entries = temp.entrySet().iterator();  

			while (entries.hasNext()) {  
			    Map.Entry<Digraph<String>, Double> entry = entries.next();
//			    To score 
//			    System.out.println(entry.getKey());
			    llscore ll = new llscore(da,entry.getKey());
			    double score = ll.resultOfScore();
			    if(SM){
			    	mdlscore mdl = new mdlscore(da,entry.getKey(),ll.resultOfScore(),ll.getQll());
				    score = mdl.resultOfScore();
			    }
//			    mdlscore mscore = new mdlscore(da,entry.getKey(),getScore.getLlscore(),getScore.getQll(),getScore.getTotalNumber());
//			    score = mscore.resultOfScore();
//			    System.out.println("Score:"+score);
//			    System.out.println(entry.getKey());
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
//			    System.out.println("New local best: "+max);
//			    System.out.println(Np);
			}
			else{
//				random restart assign Np a random value
				if(timeCNT <restime){
					timeCNT++;
					Np=dagGen(Nini,da.getVl());
					SNp = Double.NEGATIVE_INFINITY;
//				    System.out.println("Restart:"+timeCNT);
					continue;
				}
				else{
					break;
				}
			}
			if(SNp>Sres){
//				System.out.println("NEW GLOBAL BEST: "+SNp);
//				System.out.println(Npp);
				Sres = SNp;
				Nres = Npp;
			}
			Np=Npp;
		}
		return Nres; 
	}
}
