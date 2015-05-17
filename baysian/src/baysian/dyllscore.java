package baysian;

import java.util.Iterator;
import java.util.List;

/**
 * ll algorithm using in the dynamic Bayesian network
 */
public class dyllscore extends scoringfunction{
	private int parent=0;

	/**
	 * Constructor
	 * @param data1 train-data
	 * @param digraph1 DAG
	 */
	dyllscore(data data1, Digraph<String> digraph1){
		rll = data1.getR();
		numberOfNode = data1.getN();
		dyrll = new int[2*numberOfNode];
		for(int i = 0 ; i<2*numberOfNode ; i++){
			dyrll[i]=rll[i%numberOfNode];

		}
		dyqll=new int[2*numberOfNode];
		
		for (int n = 0 ; n < 2*numberOfNode ; n++){
			List<String> ls= digraph1.getPais(data1.getDVl().get(n));
			int[] arrayOfParents = new int[ls.size()];

			Iterator<String> iterls = ls.iterator();
			if(ls.size()==0){
				dyqll[n]=1;
			}
			else{
				dyqll[n]=1;
				for(int aop=0;aop<ls.size();aop++){
					String parents = iterls.next();
					int index=0;
					while(data1.getDVl().get(index)!=parents){
						index++;
					}
					parent=index;

					arrayOfParents[aop]=parent;
					dyqll[n]*=dyrll[parent];
					
				}

			}		
			for(int i=0;i< data1.getDcore().size();i++){
					firstParameter=n;
					if(ls.size()==0){
						secondParameter=0;
					}
					else{
						secondParameter=0;
						for(int p=0;p<ls.size();p++){
							int coef = data1.getDcore().get(i).get(arrayOfParents[p]);
							int weight=1;
							int pp=p;
							if(pp+1<ls.size()){
								while(pp+1<ls.size()){
									weight *= dyrll[arrayOfParents[pp+1]];
									pp++;
								}
							}
							else
								weight=1;

							secondParameter+=coef*weight;

						}
					}	
					thirdParameter=data1.getDcore().get(i).get(n);
					count[firstParameter][secondParameter][thirdParameter]++;
				}
			}
		
		for(int i=0;i<2*numberOfNode;i++){
			for(int j=0;j<dyqll[i];j++){
				for(int k=0;k<dyrll[i];k++){
					counts[i][j]+=count[i][j][k];	
				}
			}
		}
		
	}
	/**
	 * sum up all the count and counts by implementing the ll algorithm
	 * @return value of dyllscore in dynamic Bayesian network
	 */
	public double resultOfScore() {
		for(int i=0;i<2*numberOfNode;i++){
			for(int j=0;j<dyqll[i];j++){
				for(int k=0;k<dyrll[i];k++){
					if(count[i][j][k]==0){
						continue;
					}
					else
						dyllscore=dyllscore+count[i][j][k]*Math.log((double)(count[i][j][k])/counts[i][j])/Math.log(2);
					//System.out.println(dyllscore);
				}
			}
		}
		return dyllscore;
	}
	
	public int getCount(int i,int j,int k){
		return count[i][j][k];
	}
	
	public int getCounts(int i,int j){
		return counts[i][j];
	}
}