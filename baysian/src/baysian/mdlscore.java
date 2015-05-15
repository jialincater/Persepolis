package baysian;

public class mdlscore extends scoringfunction{
	

	double B=0;           //network complexity
	
	mdlscore(data data1, Digraph<String> digraph1,double llscore, int[] qll,int totalNumber){
		rll = data1.getR();
		numberOfNode = data1.getN();
		this.llscore = llscore;
		this.qll=qll;
		this.totalNumber = totalNumber;
	}
	
	public double resultOfScore() {
		
		for(int i=0;i<numberOfNode;i++){
			B=B+(rll[i]-1)*qll[i];
		}
		mdlscore = llscore - 0.5*Math.log(totalNumber)*B;
		return mdlscore;
	}

	

}