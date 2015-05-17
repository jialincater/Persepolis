package baysian;
/**
 * mdl algorithm using in the Bayesian network
 */
public class mdlscore extends scoringfunction{
	
	double B=0;           //network complexity
	/**
	 * Constructor
	 * @param train-data, DAG, value of llscore and qll
	 */
	mdlscore(data data1, Digraph<String> digraph1,double llscore, int[] qll){
		rll = data1.getR();
		numberOfNode = data1.getN();
		this.llscore = llscore;
		this.qll=qll;
		//initialize the total number of instances in data
		for(int i = 0 ; i<numberOfNode ; i++){
			totalNumber=totalNumber + rll[i];
		}
	}
	/**
	 * @return value of mdlscore in Bayesian network
	 */
	public double resultOfScore() {
		
		//iterate the rll and qll determined in the llscore function, compute the network complexity
		for(int i=0;i<numberOfNode;i++){
			B=B+(rll[i]-1)*qll[i];
		}
		mdlscore = llscore - 0.5*Math.log(totalNumber)*B/Math.log(2);
		return mdlscore;
	}
}