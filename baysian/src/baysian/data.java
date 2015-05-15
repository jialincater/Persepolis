package baysian;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class data {
	public List<List<String>> getCore() {
		return core;
	}

	private List< List<String> > core;
	private List< List<Integer> > Dcore=new ArrayList< List<Integer> >();
	private List<String> Vl = new ArrayList<String>();
	private int N;
	private int r[];
	public String tString() {
		String res="";
		for(List<String> ls:core){
			res+=ls;
			res+="\n";
		}
		return res;
	}
	public void setR(){
		int p=0;
		r=new int[N];
		List<HashSet<Integer>> hstr = new ArrayList<HashSet<Integer>>();
		for(int i=0;i!=N;++i){
			hstr.add(new HashSet<Integer>());
		}
		for(int k=1;k!=core.size();++k){
			List<String> lstr = core.get(k);
			p=0;
			for(String s:lstr){
				int j = p%N;
				hstr.get(j).add(Integer.parseInt(s));
				++p;
			}
		}
		for(int i=0;i!=N;++i){
			r[i]=hstr.get(i).size();
		}
	}
	public int[] getR() {
		return r;
	}
	public data(String path) throws IOException{
		core = new ArrayList< List<String> >();
		List<String> lstr = getFile(Paths.get(path));
   	 	for(String x:lstr){
   	 		String[] buf = x.split(",",-1);
   	 		List<String> listr = new ArrayList<String>();
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
	
	public Digraph<String> genGraph(){
		Digraph<String> Vgraph = new Digraph<String>();
		List<String> lstr = core.get(0);
		N=0;
		for(String s:lstr){
			int len = s.length();
			String c = s.substring(len-1, len);
			String m = s.substring(0, len-2);
			if(c.equals("0")){
				Vgraph.add(m);
				Vl.add(m);
				N++;
			}
			else break;
		}
		
		ListIterator<List<String>> lis = core.listIterator(); 
   	 	lis.next();
   	 	while(lis.hasNext()){
   	 		List<String> temp = lis.next();
   	 		for(int i=0;i!=temp.size()/N-1;++i){
   	   	 		List<Integer> li = new ArrayList<Integer>();
   	 			for(int j=0;j!=2*N;++j){
   	 				li.add(Integer.parseInt(temp.get(j+i*N)));
   	 			}
   	 			Dcore.add(li);
   	 		}
   	 	}
		return Vgraph;
	}
	
	public List<List<Integer>> getDcore() {
		return Dcore;
	}
	public List<String> getVl() {
		return Vl;
	}
	public int getN() {
		return N;
	}

	public ArrayList<Integer> getDataSize(){
		ArrayList<Integer> ilist= new ArrayList<Integer>();
		for(List<String> lst:core){
			ilist.add(lst.size());
		}
		return ilist;
	}
}
