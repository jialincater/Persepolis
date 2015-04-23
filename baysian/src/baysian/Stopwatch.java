package baysian;
//  Written in Apr 23rd,2015
//  For calculate the time that program executed
public class Stopwatch {
	private final long start;
	public Stopwatch(){
		start = System.currentTimeMillis();
	}
	public double elapsedTime(){
		long now = System.currentTimeMillis();
		return (now-start)/1000.0;
	}
}
