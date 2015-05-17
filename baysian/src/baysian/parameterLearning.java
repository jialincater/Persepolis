package baysian;

public class parameterLearning {
	
	private double theta[][][];
	private int[] p_rll;
	private int[] p_dyrll;
	private int p_numberOfNode;
	private int[] p_dyqll;
	private static double constN = 0.5;
	
	
	parameterLearning(dyllscore d1,data data1){
		
		p_rll = data1.getR();
		p_numberOfNode = data1.getN();
		p_dyrll = new int[2*p_numberOfNode];
		for(int i = 0 ; i<2*p_numberOfNode ; i++){
			p_dyrll[i]=p_rll[i%3];
		}
		theta = new double[2*p_numberOfNode][10000][10];
		this.p_dyqll=d1.getDyqll();
		for(int i=0;i<2*p_numberOfNode;i++){
			for(int j=0;j<p_dyqll[i];j++){
				for(int k=0;k<p_dyrll[i];k++){
//					System.out.println("i:"+i+"j:"+j+"k:"+k);
					theta[i][j][k]=(d1.getCount(i,j,k)+constN)/(d1.getCounts(i,j)+p_dyrll[i]*constN);
//					System.out.println(theta[i][j][k]);
				}
			}
		}
	}


	public double getTheta(int i,int j,int k) {
		return theta[i][j][k];
	}


}
