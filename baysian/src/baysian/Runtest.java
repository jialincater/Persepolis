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
    	train.setR();
    	System.out.println(Arrays.toString(train.getR()));
    	System.out.println(ini);
    	System.out.println(train.getN());
    }
}

