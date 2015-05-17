package baysian;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
/**
 * data class read the data from the file, 
 * delete the spaces, 
 * get out of the parameters.
 * @author Cater
 */
public class data {

	private List< List<String> > core;
	private List< List<Integer> > Dcore=new ArrayList< List<Integer> >();
	private List<String> Vl = new ArrayList<String>();
	private List<String> DVl = new ArrayList<String>();
	public List<String> getDVl() {
		return DVl;
	}

	private int N;
	private int r[];
	
	/*
	 * translate the data to String
	 * ONLY for debugging
	 * Don't use that any way
	 */
//	private String tString() {
//		String res="";
//		for(List<String> ls:core){
//			res+=ls;
//			res+="\n";
//		}
//		return res;
//	}
	
	/** 
	 * Like a 2nd constructor
	 * should be use every time after you read the data
	 * R is the value list of the 
	 * @author Cater
	 */
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
				s=s.trim();
				hstr.get(j).add(Integer.parseInt(s));
				++p;
			}
		}
		for(int i=0;i!=N;++i){
			r[i]=hstr.get(i).size();
		}
	}
	
	/** 
	 * A method can get R
	 * @return	int[] that saves R
	 * @author Cater
	 */
	public int[] getR() {
		return r;
	}
	
	/**
	 * Constructor that read the file
	 * Read the train file and Initialize core
	 * 
	 * @param  path: The path where your train data exist
	 * @author Cater
	 * @throws	just throw a exception
	 */
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
	
	
	/**
	 * A function only used by constructor
	 * So that's private~
	 * @param	file: path that hold your file
	 * @return	List of string that holds the data in file
	 * @author Cater
	 */
	private static List<String> getFile( Path file) throws IOException{
		List<String> hi  = Files.readAllLines(file, StandardCharsets.US_ASCII);
        return hi;
    }
	
	/**
	 * Generate a BN Graph
	 * NOT DBN
	 * Only have N vertex
	 * 
	 * N which is the number of Nodes is also Initialized HERE
	 * 
	 * Also generate Dcore...
	 * @return	A Digraph of string that present the BN
	 * @author Cater
	 */
	public Digraph<String> genBNGraph(){
		Digraph<String> Vgraph = new Digraph<String>();
		List<String> lstr = core.get(0);
		N=0;
		for(String s:lstr){
			int len = s.length();
			String c = s.substring(len-1, len);
			String m = s.substring(0, len-2);
			m=m.trim();
			if(c.equals("0")){
				Vgraph.add(m);
				Vl.add(m);
				N++;
			}
			else break;
		}
		// method to generate Dcore, important to call every time!
		ListIterator<List<String>> lis = core.listIterator(); 
   	 	lis.next();
   	 	while(lis.hasNext()){
   	 		List<String> temp = lis.next();
   	 		for(int i=0;i!=temp.size()/N-1;++i){
   	   	 		List<Integer> li = new ArrayList<Integer>();
   	 			for(int j=0;j!=2*N;++j){
   	 				String Sp = temp.get(j+i*N);
   	 				Sp = Sp.trim();
//   	 				System.out.println(Sp);
   	 				try{li.add(Integer.parseInt(Sp));}
   	 				catch(NumberFormatException e){
   	 					System.out.println(Sp);
   	 				}
   	 			}
   	 			Dcore.add(li);
   	 		}
   	 	}
		return Vgraph;
	}
	/**
	 * Please Use this function after N is generated
	 * Use it after genBNGraph()
	 * 
	 * To generate a DBNGraph
	 * @return	A Digraph of string that present the DBN
	 * @author Cater
	 */
	public Digraph<String> genDBNGraph(){
		 Digraph<String> Dig = new Digraph<String>();
		 for(int i=0;i!=N;++i){
			 DVl.add(Vl.get(i));
		 }
		 for(int i=0;i!=N;++i){
			 DVl.add(Vl.get(i)+'*');
		 }
		 System.out.println(DVl);
		 for(int i=0;i!=2*N;++i){
			 Dig.add(DVl.get(i));
		 }
		 return Dig;
	}
	
	/**
	 * To get the Dcore data
	 * Use By DBN Learning
	 * 
	 * @return	A set of data that contains the regular data in form of int
	 * @author Cater
	 */
	public List<List<Integer>> getDcore() {
		return Dcore;
	}
	
	/**
	 * To get Vl
	 * Which is Vertex List
	 * 
	 * @return	List of String contains Variable list
	 * @author Cater
	 */
	public List<String> getVl() {
		return Vl;
	}
	
	/**
	 * To get N
	 * Which is number of vertex
	 * 
	 * @return	Number of Vertex(Variable) in the data
	 * @author Cater
	 */
	public int getN() {
		return N;
	}

	/*
	 * I think No one use that
	 * DEBUG ONLY
	 */
//	private ArrayList<Integer> getDataSize(){
//		ArrayList<Integer> ilist= new ArrayList<Integer>();
//		for(List<String> lst:core){
//			ilist.add(lst.size());
//		}
//		return ilist;
//	}
	
	/**
	 * To get the Core data which is read directly from csv file
	 * 
	 * @return	List of List of String that contains core
	 * @author Cater
	 */
	public List<List<String>> getCore() {
		return core;
	}
}
