package baysian;

import java.lang.String;
import java.util.LinkedList;
import java.io.*;


public final class Runtest
{
	public static void main(String [] args) throws IOException
    {
    	data train = new data("/Users/Cater/Desktop/train-data.csv");
    	
    	System.out.println(train.tString());
    	LinkedList<Integer> list = train.getDataSize();
    	System.out.println(list);
    }
}

