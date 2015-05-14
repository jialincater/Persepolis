package baysian;
public abstract class scoringfunction {

	int count[][][];
	int counts[][];
	
	int numberOfNode;     //the first parameter of count
	int numberOfParent;   //the second parameter of count
	int numberOfValue;    //the third parameter of count
	
	//Initialization the result of the scroing function
	double llscore=0.0;
	double mdlscore=0.0;
	
	public abstract double resultOfScore();
		
	public static void main(String[] args) {
	
	}

}
