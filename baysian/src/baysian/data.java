package baysian;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class data {
	private List< List<String> > core;

	public String tString() {
		String res="";
		for(List<String> ls:core){
			res+=ls;
			res+="\n";
		}
		return res;
	}
	
	public data(String path) throws IOException{
		core = new LinkedList< List<String> >();
		List<String> lstr = getFile(Paths.get(path));
   	 	for(String x:lstr){
   	 		String[] buf = x.split(",",-1);
   	 		List<String> listr = new LinkedList<String>();
   	 		for(String s:buf){
   	 			listr.add(s);
   	 		}
   	 		core.add(listr);
   	 	}
	}
	
	private static List<String> getFile( Path file) throws IOException{
		List<String> hi  = Files.readAllLines(file, StandardCharsets.US_ASCII);
        return hi;
    }
	
	public LinkedList<Integer> getDataSize(){
		LinkedList<Integer> ilist= new LinkedList<Integer>();
		for(List<String> lst:core){
			ilist.add(lst.size());
		}
		return ilist;
	}
}
