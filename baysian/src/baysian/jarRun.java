package baysian;

import java.io.IOException;
import java.util.List;

public class jarRun {

	public static void main(String[] args) throws IOException {
//		For Inputs
		String trainDataPath = args[0];
		String testDataPath = args[1];
//		if SM is false, use LL; else use MDL
		boolean SM = false;
		if(args[2].equals("LL")){
			SM = false;
		}else if(args[2].equals("MDL")){
			SM = true;
		}
		
		
		trainDataPath = "/Users/Cater/Desktop/train-data-2.csv";
		testDataPath = "/Users/Cater/Desktop/test-data-2.csv";
		
		Integer randrest = Integer.parseInt(args[3]);
		int var = Integer.parseInt(args[4]);
		
//		Input data and generate ini networks
		data train = new data(trainDataPath);
    	Digraph<String> ini = train.genBNGraph();
    	train.setR();
    	Digraph<String> Dini = train.genDBNGraph();

//    	Learning BN
    	Learn SXuexi = new SLearn();
    	Digraph<String> Sres = SXuexi.learnStructures(ini, train, randrest);
    	
//    	Learning DBN
    	Stopwatch sw = new Stopwatch();
    	Learn Xuexi = new DLearn();
    	Digraph<String> Dres = Xuexi.learnStructures(Dini, train, randrest);
    	double TDBN = sw.elapsedTime();

//    	Parameter Learning
    	dyllscore dyll = new dyllscore(train,Dres);
    	parameterLearning Pl = new parameterLearning(dyll,train);
    	
//    	inference
    	inference inf = new inference(testDataPath);
    	List<Integer> inferenceResult = inf.predict(var, dyll, train, Pl, Dres);

//    	
    	
//    	First OUTPUT
    	System.out.println("Building DBN:           "+TDBN);
//    	Second OUTPUT
    	System.out.println("Initial network:        "+Sres);
//    	Third OUTPUT
    	System.out.println("Transition network:     "+Dres);
//    	4th OUTPUT
    	System.out.println("Performing inference:   ");
    	for(int i=0;i!=inferenceResult.size();++i){
    		System.out.println("-> instance "+i+":      "+inferenceResult.get(i));
    	}
    	
	}

}
