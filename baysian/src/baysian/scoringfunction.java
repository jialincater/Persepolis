package baysian;

public abstract class scoringfunction {
	
	int firstParameter;   
	int secondParameter;
	int thirdParameter;
	
	
	int numberOfNode;     
	
	int count[][][]=new int[10][40][5];
	int counts[][]=new int[10][40];
	protected int[] qll;
	
	//Initialization the result of the scoring function
	double llscore;
	double mdlscore;
	
	public abstract double resultOfScore();


}