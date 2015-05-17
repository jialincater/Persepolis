package baysian;

public class dymdlscore extends scoringfunction{
	double B=0.0;           //network complexity
	
	dymdlscore(data data1, Digraph<String> digraph1,double dyllscore, int[] dyqll){
		rll = data1.getR();
		numberOfNode = data1.getN();
		dyrll = new int[2*numberOfNode];
		for(int i = 0 ; i<2*numberOfNode ; i++){
			dyrll[i]=rll[i%numberOfNode];
			totalNumber=totalNumber + dyrll[i];
		}
		this.dyllscore = dyllscore;
		this.dyqll=dyqll;
//		this.totalNumber = data1.getDcore().size();
	}
	
	public double resultOfScore() {
		
		for(int i=0;i<2*numberOfNode;i++){
			B=B+(dyrll[i]-1)*dyqll[i];
//			System.out.println(B + " " +dyrll[i]+ " " +dyqll[i]);
		}
//		System.out.println(totalNumber);
		dymdlscore = dyllscore - 0.5*Math.log(totalNumber)*B/Math.log(2);
		return dymdlscore;
	}
}
