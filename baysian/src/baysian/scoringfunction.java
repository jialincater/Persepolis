package baysian;
public abstract class scoringfunction {
	
	int firstParameter;   
	int secondParameter;
	int thirdParameter;
	int count[][][]=new int[3][20][5];
	int counts[][]=new int[3][20];
	
	int numberOfNode;     
	
	
	
	
	//Initialization the result of the scoring function
	double llscore;
	double mdlscore;
	
	public abstract double resultOfScore();


}
