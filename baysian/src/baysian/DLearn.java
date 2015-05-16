package baysian;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DLearn extends Learn{
	/*
	To Learn a Dynamic Baysian Network
	*/
	public Digraph<String> learnStructures(Digraph<String> Nini, data da, int restime){
		int thatIsWhatICallN = da.getN();
		List<String> thatIsWhatICallDVL = da.getDVl();
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
			for(int i =0;i!=thatIsWhatICallN*2;++i){	
//				Have a N-times Loop
//				Fix every Vertex each time
				for(int j=thatIsWhatICallN;j!=2*thatIsWhatICallN;j++){
//					if i == j, it must not be a DAG
					if(i==j)	continue;
//					Test if t contains the edge i->j
					else if(graphToFix.contains(thatIsWhatICallDVL.get(i),thatIsWhatICallDVL.get(j))){
//						if t contains i->j, try to delete or reverse one 
						Digraph<String> graphContainsEdgeRemove = isCopyOf(graphToFix);
						Digraph<String> graphContainsEdgeReverse = isCopyOf(graphToFix);
						graphContainsEdgeReverse.remove(thatIsWhatICallDVL.get(i), thatIsWhatICallDVL.get(j));
						graphContainsEdgeRemove.reverse(thatIsWhatICallDVL.get(i), thatIsWhatICallDVL.get(j));
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
						graphNotContainsEdgeAdd.add(thatIsWhatICallDVL.get(i), thatIsWhatICallDVL.get(j));
						if(graphNotContainsEdgeAdd.getPais(thatIsWhatICallDVL.get(j)).size()>3){
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
			    
//			    llscore ll = new llscore(da,entry.getKey());
//		    	System.out.println("llscore for the data : " + ll.resultOfScore());
			    System.out.println(entry.getKey());
		    	dyllscore dyll = new dyllscore(da,entry.getKey());
//		    	System.out.println("dyllscore for the data : " + dyll.resultOfScore());
		    	dymdlscore dymdl = new dymdlscore(da,entry.getKey(),dyll.getDyllscore(),dyll.getDyqll());
//		    	System.out.println("dymdlscore for the data : " + dymdl.resultOfScore() );
			    double rsc = dymdl.resultOfScore();
			    System.out.println("Score:"+ rsc);
			    entry.setValue(rsc);
			    if(entry.getValue()>max){
			    	Npp=entry.getKey();
			    	max=entry.getValue();
			    }
			}
//			if the best neighbor is better than current result, replace it.
			if(max>SNp){
				Np=Npp;
				SNp=max;
			    System.out.println("New local best: "+max);
			    System.out.println(Np);
			}
			else{
//				random restart assign Np a random value
				if(timeCNT <restime){
					timeCNT++;
					Np=dagGen(Nini,thatIsWhatICallDVL);
					SNp = Double.NEGATIVE_INFINITY;
				    System.out.println("Restart:"+timeCNT);
					continue;
				}
				else{
					break;
				}
			}
			if(SNp>Sres){
				System.out.println("NEW GLOBAL BEST: "+SNp);
				System.out.println(Npp);
				Sres = SNp;
				Nres = Npp;
			}
			Np=Npp;
		}
		return Nres; 
	}
	
	public Digraph<String> dagGen(Digraph<String> ini,List<String> Vl){
		Digraph<String> Res = ini;
		int N = ini.neighbors.size();
		int CNT = 0;
		int NumberOfEdge = (int)(Math.random()*(N*N-N)/4);
		for(int i =0;i!=NumberOfEdge;++i){
			CNT++;
			if(CNT>500){
				break;
			}
			int x=(int)(Math.random()*N),y=(int)(Math.random()*N);
			if(Res.getPais(Vl.get(y)).size()>=3){
				i--;
				continue;
			}
//			System.out.println(x+" "+y);
			if(x!=y){
				if(x<N/2&&y<N/2){
					i--;
					continue;
				}
				else if (x<N/2&&y>=N/2){
					if(!Res.contains(Vl.get(x), Vl.get(y))){
						Res.add(Vl.get(x), Vl.get(y));
					}
					else{
						i--;
						continue;
					}
				}
				else if(x>=N/2&&y<N/2){
					if(!Res.contains(Vl.get(y), Vl.get(x))){
						Res.add(Vl.get(y), Vl.get(x));
					}
					else{
						i--;
						continue;
					}
				}
				else{
					if(!Res.contains(Vl.get(y), Vl.get(x))){
						Res.add(Vl.get(y), Vl.get(x));
					}
					else{
						i--;
						continue;
					}
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
}
