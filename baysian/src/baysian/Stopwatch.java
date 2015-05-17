package baysian;
 
/**
 * For calculate the time that program executed
 * @author Cater
 * Written in Apr 23rd,2015
 */
public class Stopwatch {
	private final long start;
	/**
	 * Constructor that starts a stopwatch
	 */
	public Stopwatch(){
		start = System.currentTimeMillis();
	}
	/**
	 * to get the time elapsed 
	 * @return	the time elapsed in double
	 */
	public double elapsedTime(){
		long now = System.currentTimeMillis();
		return (now-start)/1000.0;
	}
}
