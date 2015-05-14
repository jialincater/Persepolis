package baysian;

import java.lang.String;
import java.util.Arrays;
import java.util.LinkedList;
import java.io.*;


public final class Runtest
{
	public static void main(String [] args) throws IOException
    {
    	data train = new data("/Users/Cater/Desktop/train-data.csv");
    	Digraph<String> ini = train.genGraph();
    	ini.add("a","b");
    	ini.add("b","c");
    	train.setR();
    	System.out.println(ini.getPais("b"));
    	llscore ll = new llscore(train,ini);
    	System.out.println(ll.resultOfScore());
    	System.out.println(Arrays.toString(train.getR()));
    	System.out.println(ini);
//    	System.out.println(train.getN());
    }
}

