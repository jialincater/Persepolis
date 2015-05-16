package baysian;

import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.io.*;


public final class Runtest
{
	public static Digraph<String> isCopyOf(Digraph<String> org){
		Digraph<String> NewGraph = new Digraph<String>(org);
		return NewGraph;
	}
	public static void main(String [] args) throws IOException
    {
    	data train = new data("/Users/Cater/Desktop/train-data.csv");
    	Digraph<String> ini = train.genBNGraph();
    	train.setR();
    	Digraph<String> Dini = train.genDBNGraph();
  
 
    	Learn Xuexi = new Learn();
    	Digraph<String> res = Xuexi.learnBNStructures(ini, train, 100);
    	
//    	ini.add("b", "c");
//    	ini.add("c", "b");
//    	System.out.println(ini.isDag());
//    	llscore ll = new llscore(train,ini);
//    	System.out.println(ll.resultOfScore());
//    	System.out.println(Arrays.toString(train.getR()));
//    	System.out.println(cal(ini,train));
    	System.out.println(res);
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

