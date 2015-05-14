package baysian;

import java.util.Iterator;
import java.util.List;


public class llscore extends scoringfunction{
	
	private int[] rll;
	private int[] numberOfState;
	private int parent=0;
//	private int numberOfParents;
	private int[] qll;

	llscore(data data1, Digraph<String> digraph1){
		rll = data1.getR();
		numberOfNode = data1.getN();
		qll=new int[numberOfNode];
		for (int n = 0 ; n < numberOfNode ; n++){
			
			List<String> ls= digraph1.getPais(""+(char)(n+65));
			int[] arrayOfParents = new int[ls.size()];

			Iterator<String> iterls = ls.iterator();
			for(int aop=0;aop<ls.size();aop++){
				String parents = iterls.next();
				parent = Integer.parseInt(parents)-65;
				arrayOfParents[aop]=parent;
				qll[n]*=rll[parent];
			}
			for(int i=1;i<data1.getCore().size()-1;i++){
				for(int j=n;j<data1.getCore().get(i).size();j+=j+numberOfNode){
					firstParameter=n;
					for(int p=0;p<ls.size();p++){
						secondParameter+=Integer.parseInt(data1.getCore().get(i).get(arrayOfParents[p]))*Math.pow(2,ls.size()-p-1);
					}
					thirdParameter=Integer.parseInt(data1.getCore().get(i).get(j));
					count[firstParameter][secondParameter][thirdParameter]++;
				}
			}
		}	
		for(int i=0;i<numberOfNode;i++){
			for(int j=0;j<qll[i];j++){
				for(int k=0;k<rll[i];k++){
					counts[i][j]+=count[i][j][k];
				}
			}
		}
		
	}


	public double resultOfScore() {
		for(int i=0;i<numberOfNode;i++){
			for(int j=0;j<qll[i];j++){
				for(int k=0;k<rll[i];k++){
					llscore=llscore+count[i][j][k]+Math.log(count[i][j][k]/counts[i][j]);
				}
			}
		}
		return llscore;
	}

}
