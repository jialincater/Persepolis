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
    	Digraph<String> ini = train.genGraph();
    	train.setR();
    	Learn Xuexi = new Learn();
    	Digraph<String> res = Xuexi.learnDBStructures(ini, train, 100);
    	
//    	llscore ll = new llscore(train,ini);
//    	System.out.println(ll.resultOfScore());
//    	System.out.println(Arrays.toString(train.getR()));
    	System.out.println(res);
//    	System.out.println(train.getN());
    }
}

