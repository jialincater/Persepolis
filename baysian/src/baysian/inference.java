package baysian;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class inference {
	private List< List<String> > core;
	private List<List<Integer>> Dcore;
	/**
	 * contructor that read the data from the test file
	 * @param path	the path where your file locate
	 * @throws IOException
	 * @author Cater
	 */
	public inference(String path) throws IOException{
//		Read file & ini core
		core = new ArrayList< List<String> >();
		Dcore = new ArrayList< List<Integer> >();
		List<String> lstr = getFile(Paths.get(path));
   	 	for(String x:lstr){
   	 		String[] buf = x.split(",",-1);
   	 		List<String> listr = new ArrayList<String>();
   	 		for(String s:buf){
   	 			listr.add(s);
   	 		}
   	 		core.add(listr);
   	 	}
   	 	
//   	Inialize Dcore
   	 	Iterator<List<String>> ils = core.iterator();
   	 	ils.next();
   	 	while(ils.hasNext()){
   	 		List<String> ls = ils.next();
   	 		List<Integer> Li = new ArrayList<Integer>();
   	 		for(String stl:ls){
   	 			stl=stl.trim();
   	 			Li.add(Integer.parseInt(stl));
   	 		}
   	 		Dcore.add(Li);
   	 	}
   	 	
//   	 	System.out.println(Dcore);
	}
	/**
	 * private get file function that used by constructor
	 * @param file	the path where your file locate
	 * @return	A list of String contains every line of file
	 * @throws IOException
	 * @author Cater
	 */
	private static List<String> getFile( Path file) throws IOException{
		List<String> hi  = Files.readAllLines(file, StandardCharsets.US_ASCII);
        return hi;
    }
	
	/**
	 * predict method that can predict the data with existing learned network
	 * @param var		the number of data you want to predict
	 * @param dyll		the object of LL score in DBN 
	 * @param train		the data stores the train file
	 * @param pL		the object of parameter learning class
	 * @param digraph1	the digraph learned in the previous steps
	 * @return	a list of integer contains the result
	 * @author Cater
	 */
	public List<Integer> predict(Integer var,dyllscore dyll,data train,parameterLearning pL,Digraph<String> digraph1){
		List<String> DVl = train.getDVl();
//		System.out.println(DVl);
//		System.out.println("Digraph::"+digraph1);
		String target = DVl.get(var+train.getN());
//		System.out.println("target::"+target);
		List<String> parent = digraph1.getPais(target);
//		System.out.println("parents::"+parent);
		List<Integer> resList = new ArrayList<Integer>();
		for(int r = 0;r!=Dcore.size();++r){
			List<Integer> indexLine = Dcore.get(r);
//			System.out.println("index::"+indexLine);
			List<Double> pList = new LinkedList<Double>();
			for(int v=0;v!=dyll.getDyrll()[var];++v){
				List<Integer> weight = new ArrayList<Integer>();
//				List save the index of every parents
				List<Integer> NoP = new ArrayList<Integer>();
				List<Integer> Pvalue = new ArrayList<Integer>();
				for(String str:parent){
					NoP.add(DVl.indexOf(str));
				}
//				System.out.println("    NoP::"+NoP);
				for(int givenParents=0;givenParents!=NoP.size();++givenParents){
					if(NoP.get(givenParents)<train.getN()){
						Integer e =indexLine.get(NoP.get(givenParents));
						Pvalue.add(e);
					}
					else{
						break;
					}
				}
//				System.out.println("    PValue::"+Pvalue);
				for(int z = 0;z!=NoP.size();++z){
					int s =z;				
					int temp = 1;
					while(s<NoP.size()-1){
						temp*=dyll.getDyrll()[NoP.get(s+1)];
						s++;
					}
					weight.add(temp);
				}
//				System.out.println("    weight::"+weight);
//				weight.add(1);

				
//				List<Integer> jList = new ArrayList<Integer>();
				int dis = weight.size()-Pvalue.size();
				int time =1;
				for(int c =0;c!=dis;++c){
					time*=dyll.getDyrll()[weight.size()+c];
				}
//				System.out.println("    time::"+time);
				double sumTheta = 0.0;
				for(int pp=0;pp!=time;++pp){
					int jj = 0;
					for(int a=0;a!=Pvalue.size();++a){
						jj+=weight.get(a)*Pvalue.get(a);
//						System.out.println("            jj::"+jj );
					}
//					System.out.println("            pp::"+pp );
					sumTheta+=pL.getTheta(var+train.getN(),jj+pp , v);
//					System.out.println("        sumTheta::"+pL.getTheta((var+train.getN()),jj+pp , v));
//					System.out.println("        ijk::"+(var+train.getN())+" "+(jj+pp)+" "+v );
				}
				pList.add(sumTheta);
			}
			double max=Double.NEGATIVE_INFINITY;
//			int Nmax=0;
			List<Integer> NmaxList = new LinkedList<Integer>();
			for( int g =0;g!=pList.size();++g){
				if(max<pList.get(g)){
//					Nmax=g;
					NmaxList.clear();
					NmaxList.add(g);
					max=pList.get(g);
				}else if((int)(max*1000)==(int)(pList.get(g)*1000)){
//					System.out.println(max+" equals"+pList.get(g));
					NmaxList.add(g);
				}
			}
			int num = (int)(NmaxList.size()*Math.random());
//			System.out.println(num+"  "+NmaxList.size());
			resList.add(NmaxList.get(num));
		}
		
		return resList;
	}
}
