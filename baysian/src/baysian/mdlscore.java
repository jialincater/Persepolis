package baysian;


public class mdlscore extends scoringfunction{
	
	int totalNumber;
	double B=0;           //network complexity
	
	mdlscore(){
		
	}
	
	public double resultOfScore() {
		
		for(int i=1;i<numberOfNode;i++){
			B=B+(numberOfNode-1)*q[i-1];
		}
		mdlscore = llscore - 0.5*Math.log(totalNumber)*B;
		return mdlscore;
	}

	

}
