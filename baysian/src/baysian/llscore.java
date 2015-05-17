package baysian;

import java.util.Iterator;
import java.util.List;


public class llscore extends scoringfunction{
	private int parent=0;

	llscore(data data1, Digraph<String> digraph1){
		rll = data1.getR();
		numberOfNode = data1.getN();
		qll=new int[numberOfNode];
		
		for (int n = 0 ; n < numberOfNode ; n++){
			List<String> ls= digraph1.getPais(data1.getVl().get(n));
			int[] arrayOfParents = new int[ls.size()];

			Iterator<String> iterls = ls.iterator();
			if(ls.size()==0){
				qll[n]=1;
			}
			else{
				qll[n]=1;
				for(int aop=0;aop<ls.size();aop++){
					String parents = iterls.next();
					int index=0;
					while(data1.getDVl().get(index)!=parents){
						index++;
					}
					parent=index;
//					parent = parents.charAt(0)-shift;
					arrayOfParents[aop]=parent;
					qll[n]*=rll[parent];
					
				}
//				System.out.println("qll["+n+"]="+qll[n]);
			}
				
			
			for(int i=1;i<data1.getCore().size();i++){
				for(int j=0;j<data1.getCore().get(i).size();j+=numberOfNode){
					firstParameter=n;
					if(ls.size()==0){
						secondParameter=0;
					}
					else{
						secondParameter=0;
						for(int p=0;p<ls.size();p++){
							String sttemp = data1.getCore().get(i).get(arrayOfParents[p]+j);
							sttemp = sttemp.trim();
							int coef = Integer.parseInt(sttemp);
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
							secondParameter+=coef*Math.pow(weight,ls.size()-p-1);
						}
					}	
					String ttemp = data1.getCore().get(i).get(n+j);
					ttemp = ttemp.trim();
					thirdParameter=Integer.parseInt(ttemp);
					count[firstParameter][secondParameter][thirdParameter]++;
//					System.out.println(firstParameter + " " +
//										secondParameter + " " +
//										thirdParameter + " " +
//										count[firstParameter][secondParameter][thirdParameter]);
				}
			}
		}	
		for(int i=0;i<numberOfNode;i++){
			for(int j=0;j<qll[i];j++){
				for(int k=0;k<rll[i];k++){
					counts[i][j]+=count[i][j][k];	
				}
//				totalNumber++;
//				System.out.println(totalNumber + " " +counts[i][j]);
			}
		}
		
	}
	
	public double resultOfScore() {
		for(int i=0;i<numberOfNode;i++){
			for(int j=0;j<qll[i];j++){
				for(int k=0;k<rll[i];k++){
					if(count[i][j][k]==0){
						continue;
					}
					else
						llscore=llscore+count[i][j][k]*Math.log10((double)(count[i][j][k])/counts[i][j])/Math.log(2);
				}
			}
		}
		return llscore;
	}
}
