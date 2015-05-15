package baysian;

import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.io.*;


public final class Runtest
{
	public static void main(String [] args) throws IOException
    {
		Stopwatch sw = new Stopwatch();
    	data train = new data("/Users/Cater/Desktop/train-data.csv");
    	Digraph<String> ini = train.genGraph();
//    	ini.add("c","a");
//    	ini.add("a","b");
    	ArrayList<Digraph<String>> m = new ArrayList<Digraph<String>>();
    	m.add(ini);
    	ini.add("n");
    	m.add(ini);
    	System.out.println(m);
//    	train.setR();
//    	Learn miao = new Learn();
//    	Digraph<String> graphContainsEdgeRemove = new Digraph<String>();
//    	ini = miao.dagGen(graphContainsEdgeRemove, train.getVl());
//    	System.out.println(ini);
//    	System.out.println(graphContainsEdgeRemove);
//		System.out.println(sw.elapsedTime());
//    	System.out.println(ini.getPais("b"));
//    	llscore ll = new llscore(train,ini);
//    	System.out.println(ll.resultOfScore());
//    	System.out.println(Arrays.toString(train.getR()));
//    	System.out.println(ini);
//    	System.out.println(train.getN());
    }
}

