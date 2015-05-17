package baysian;
/**
 * provide solutions to score the given structure using different scoring algorithms
 * @author A.Cleverley
 *
 */
public abstract class scoringfunction {
	
	int firstParameter;     //first parameter in the count
	int secondParameter;	//second parameter in the count
	int thirdParameter;	 	//third parameter in the count
	
	int numberOfNode;     
	int totalNumber=0;
	

	int count[][][];
	int counts[][];
	
	protected int[] qll;	//number of parent configurations of each nodes in bayesian network
	protected int[] dyqll;	//number of parent configurations of each nodes in dynamic bayesian network
	protected int[] rll;	//number of different values of each nodes in bayesian network
	protected int[] dyrll;	//number of different values of each nodes in dynamic bayesian network
	
	//Initialization the result of different kinds of scoring function
	double llscore;
	double mdlscore;
	double dyllscore;
	double dymdlscore;
	
	public int[] getDyqll() {
		return dyqll;
	}
	
	public int[] getQll() {
		return qll;
	}

	public int[] getRll() {
		return rll;
	}

	public int[] getDyrll() {
		return dyrll;
	}

	public double getDyllscore() {
		return dyllscore;
	}

	public double getDymdlscore() {
		return dymdlscore;
	}
	
	public double getLlscore() {
		return llscore;
	}

	public int getTotalNumber() {
		return totalNumber;
	}
	
	public abstract double resultOfScore();
}