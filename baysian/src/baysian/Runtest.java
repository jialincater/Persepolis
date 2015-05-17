package baysian;

import java.lang.String;
import java.io.*;


public final class Runtest
{
	public static Digraph<String> isCopyOf(Digraph<String> org){
		Digraph<String> NewGraph = new Digraph<String>(org);
		return NewGraph;
	}
	public static void main(String [] args) throws IOException
    {
//    	data train = new data("/Users/Cater/Desktop/train-data-2.csv");
//    	Digraph<String> ini = train.genBNGraph();
//    	train.setR();
//    	Digraph<String> Dini = train.genDBNGraph();
//    	System.out.println(Dini);
		double peng = 1.0/3;
		double cong = 10.0/30;
		if(peng == cong)
			System.out.println("pengcong");
//    	Learn Xuexi = new DLearn();
//    	System.out.println(Xuexi.dagGen(Dini, train.getDVl()));
//    	Digraph<String> res = Xuexi.learnStructures(Dini, train,3);
//    	llscore ll = new llscore(train,ini);
//    	System.out.println("llscore for the data : " + ll.resultOfScore());
//    	dyllscore dyll = new dyllscore(train,res);
//    	dymdlscore dymdl = new dymdlscore(train,res,dyll.getDyllscore(),dyll.getDyqll());

//    	parameterLearning Pl = new parameterLearning(dyll,train);
//    	for(int i = 0;i!=train.getN()*2;++i){
//    		for(int j=0;j!=dyll.getDyqll()[i];++j){
//    			for(int k=0;k!=dyll.getDyrll()[i];++k){
//    				System.out.println(Pl.getTheta(i, j, k));
//    				System.out.println(i+" "+j+" "+k);
//    			}
//    		}
//    	}
//    	inference inf = new inference("/Users/Cater/Desktop/test-data-2.csv");
//    	System.out.println(inf.predict(1, dyll, train, Pl, res));
//    	System.out.println("dyllscore for the data : " + dyll.resultOfScore());
//    	dymdlscore dymdl = new dymdlscore(train,Dini,dyll.getDyllscore(),dyll.getDyqll());
//    	System.out.println("dymdlscore for the data : " + dymdl.resultOfScore() );
//    	ini.add("b", "c");
//    	ini.add("c", "b");
//    	System.out.println(ini.isDag());
//    	llscore ll = new llscore(train,ini);
//    	System.out.println(ll.resultOfScore());
//    	System.out.println(Arrays.toString(train.getR()));
//    	System.out.println(cal(ini,train));
//    	System.out.println(res);
    }
	
	public static double cal(Digraph<String> ini,data da){
		ini.add("b", "c");
//		ini.add("a", "c");
//		ini.add("c","b");
		System.out.println(ini);
		llscore score = new llscore(da,ini);
//		mdlscore mscore = new mdlscore(da,ini,score.getLlscore(),score.getQll(),score.getTotalNumber());
//	    Double MDscore = mscore.resultOfScore();
		return score.resultOfScore();
	}
}

