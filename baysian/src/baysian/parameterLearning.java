package baysian;
/**
 * parameter learning given the selected structure
 */
public class parameterLearning {
	
	private double theta[][][];    	//initialize a set of probabilities 
	private int[] p_rll;		
	private int[] p_dyrll;
	private int p_numberOfNode;
	private int[] p_dyqll;
	private static double constN = 0.5;//pseudo-counts, always 0.5 in this project
	
	/**
	 * Constructor
	 * @param d1 all the count and counts in dyll
	 * @param data1 train-data
	 */
	parameterLearning(dyllscore d1,data data1){
		
		p_rll = data1.getR();
		p_numberOfNode = data1.getN();
		p_dyrll = new int[2*p_numberOfNode];
		for(int i = 0 ; i<2*p_numberOfNode ; i++){
			p_dyrll[i]=p_rll[i%3];
		}
		theta = new double[2*p_numberOfNode][10000][10];
		this.p_dyqll=d1.getDyqll();
		//iterate all the count and counts, compute the theta[i][j][k] given count and counts
		for(int i=0;i<2*p_numberOfNode;i++){
			for(int j=0;j<p_dyqll[i];j++){
				for(int k=0;k<p_dyrll[i];k++){
					theta[i][j][k]=(d1.getCount(i,j,k)+constN)/(d1.getCounts(i,j)+p_dyrll[i]*constN);
				}
			}
		}
	}

	/**
	 * 
	 * @param i firstParameter
	 * @param j secondParameter
	 * @param k thirdParameter
	 * @return probability given count and counts
	 */
	public double getTheta(int i,int j,int k) {
		return theta[i][j][k];
	}


}
