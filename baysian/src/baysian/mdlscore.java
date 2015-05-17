package baysian;

public class mdlscore extends scoringfunction{
	

	double B=0;           //network complexity
	
	mdlscore(data data1, Digraph<String> digraph1,double llscore, int[] qll){
		rll = data1.getR();
		numberOfNode = data1.getN();
		this.llscore = llscore;
		this.qll=qll;
		for(int i = 0 ; i<numberOfNode ; i++){
			totalNumber=totalNumber + rll[i];
//			System.out.println(totalNumber + " " + rll[i]);
		}
	}
	
	public double resultOfScore() {
		
		
		for(int i=0;i<numberOfNode;i++){
			B=B+(rll[i]-1)*qll[i];
//			System.out.println(qll[i]);
		}
//		System.out.println(B);
		mdlscore = llscore - 0.5*Math.log(totalNumber)*B/Math.log(2);
		return mdlscore;
	}
}