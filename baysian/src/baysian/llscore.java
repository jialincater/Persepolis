package baysian;

import java.util.Iterator;
import java.util.List;

/**
 * ll algorithm using in the Bayesian network
 */
public class llscore extends scoringfunction{
	private int parent=0;
	private double result = 0.0;

	/**
	 * Constructor
	 * @param train-data , DAG
	 */
	llscore(data data1, Digraph<String> digraph1){
		//initialize the rll given the data
		rll = data1.getR();
		//initialize the number of nodes given the data
		numberOfNode = data1.getN();
		qll=new int[numberOfNode];
		
		for (int n = 0 ; n < numberOfNode ; n++){
			List<String> ls= digraph1.getPais(data1.getVl().get(n));
			int[] arrayOfParents = new int[ls.size()];

			Iterator<String> iterls = ls.iterator();
			
			//determine the number of parent configuration of each nodes
			if(ls.size()==0){
				qll[n]=1;
			}
			else{
				qll[n]=1;
				for(int aop=0;aop<ls.size();aop++){
					String parents = iterls.next();
					int index=0;
					while(data1.getVl().get(index)!=parents){
						index++;
					}
					parent=index;
					arrayOfParents[aop]=parent;
					
					qll[n]*=rll[parent];
				}
				
			}

			// iterate each set of nodes,  determine the value of the count with three parameters.  
			for(int i=1;i<data1.getCore().size();i++){
				for(int j=0;j<data1.getCore().get(i).size();j+=numberOfNode){
					firstParameter=n;
					if(ls.size()==0){
						secondParameter=0;
					}
					else{
						secondParameter=0;
						for(int p=0;p<ls.size();p++){
							String str = data1.getCore().get(i).get(arrayOfParents[p]+j);
							int coef = Integer.parseInt(str.trim());
							int weight=1;
							int pp=p;
							if(pp+1<ls.size()){
								while(pp+1<ls.size()){
									weight *= rll[arrayOfParents[pp+1]];
									pp++;
								}
							}
							else
								weight=1;
							secondParameter+=coef*weight;
						}
					}	
					String miao = data1.getCore().get(i).get(n+j);
					miao = miao.trim();
					thirdParameter=Integer.parseInt(miao);
					count[firstParameter][secondParameter][thirdParameter]++;
				}
			}
		}	
		
		//iterate each count,  determine the value of the count with three parameters.
		for(int i=0;i<numberOfNode;i++){
			for(int j=0;j<qll[i];j++){
				for(int k=0;k<rll[i];k++){
					counts[i][j]+=count[i][j][k];	
				}
			}
		}
		
	}
	
	/**
	 * sum up all the count and counts by implementing the ll algorithm
	 * @return value of llscore in Bayesian network
	 */
	public double resultOfScore() {
		for(int i=0;i<numberOfNode;i++){
			for(int j=0;j<qll[i];j++){
				for(int k=0;k<rll[i];k++){
					if(count[i][j][k]==0){
						continue;
					}
					else
						result = Math.log((double)(count[i][j][k])/counts[i][j])/Math.log(2);
						llscore=llscore+count[i][j][k]*result;
				}
			}
		}
		return llscore;
	}
}
