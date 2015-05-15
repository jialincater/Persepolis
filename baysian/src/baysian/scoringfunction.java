package baysian;

public abstract class scoringfunction {
	
	int firstParameter;   
	int secondParameter;
	int thirdParameter;
	
	int numberOfNode;     
	int totalNumber=0;
	
	int count[][][]=new int[10][40][5];
	int counts[][]=new int[10][40];
	protected int[] qll;
	protected int[] rll;
	//Initialization the result of the scoring function
	double llscore;
	double mdlscore;
	
		public int[] getQll() {
		return qll;
	}

	public int[] getRll() {
		return rll;
	}


	public double getLlscore() {
		return llscore;
	}

	public int getTotalNumber() {
		return totalNumber;
	}
	
	public abstract double resultOfScore();


}