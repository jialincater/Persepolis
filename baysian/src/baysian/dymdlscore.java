package baysian;
/**
 * mdl algorithm using in the dynamic Bayesian network
 */
public class dymdlscore extends scoringfunction{
	double B=0.0;           //network complexity
	/**
	 * Constructor
	 * @param data1 	train-data
	 * @param digraph1 	DAG
	 * @param dyllscore 
	 * @param dyqll
	 */
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

	}
	
	/**
	 * @return value of mdlscore in dynamic Bayesian network
	 */
	public double resultOfScore() {
		
		for(int i=0;i<2*numberOfNode;i++){
			B=B+(dyrll[i]-1)*dyqll[i];

		}

		dymdlscore = dyllscore - 0.5*Math.log(totalNumber)*B/Math.log(2);
		return dymdlscore;
	}
}
