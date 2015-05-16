package baysian;

public abstract class scoringfunction {
	
	int firstParameter;   
	int secondParameter;
	int thirdParameter;
	
	int numberOfNode;     
	int totalNumber=0;
	
	int count[][][]=new int[15][10000][100];
	int counts[][]=new int[15][10000];
	protected int[] qll;
	protected int[] dyqll;
	public int[] getDyqll() {
		return dyqll;
	}

	protected int[] rll;
	protected int[] dyrll;
	public int[] getDyrll() {
		return dyrll;
	}

	public double getDyllscore() {
		return dyllscore;
	}

	public double getDymdlscore() {
		return dymdlscore;
	}

	//Initialization the result of the scoring function
	double llscore;
	double mdlscore;
	double dyllscore;
	double dymdlscore;
	
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