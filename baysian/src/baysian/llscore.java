package scoringfunction;

import java.util.Iterator;
import java.util.List;


public class llscore extends scoringfunction{
	
	private int[] rll;
	private int[] qll={1};
	private int[] numberOfState;
	private int parent=0;
//	private int numberOfParents;
	
	llscore(data data1, Digraph<String> digraph1){
		rll[] = data1.getR();
		numberOfNode = data1.getN();
		for (int i = 0 ; i < numberOfNode ; i++){
			
			List<String> ls= digraph1.getPais(""+(char)(i+65));
			Iterator<String> iterls = ls.iterator();
			//得到第i个节点的父节点共有多少种组合，存在qll[i]中
			while(iterls.hasNext()){
//				numberOfParents++;
				String parents = iterls.next();
				parent = Integer.parseInt(parents)-65;
				qll[i]*=rll[parent];			
			}
				for(int k = 0; k<rll[i];k++){
					for(int j = 0;j<qll[i];j++){
						if(data[numberOfState[i]][i]==k){
							for(int nop = 0;nop<ls.size();nop++){
								if(data[numberOfState[parent]][parent]==j){
									count[i][j][k]++;	
								}
							}
						}
					}
				}
			}	
	}


	public double resultOfScore() {
		for(int i=1;i<numberOfNode+1;i++){
			for(int j=1;j<qll[i-1]+1;j++){
				for(int k=1;k<rll[i-1]+1;k++){
					llscore=llscore+count[i][j][k]+Math.log(count[i][j][k]/counts[i][j]);
				}
			}
		}
		return llscore;
	}

}
