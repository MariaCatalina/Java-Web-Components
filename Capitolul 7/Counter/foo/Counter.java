package foo;

public class Counter {
	
	private static int counter;
	
	public static synchronized int getCount(){
		counter ++;
		return counter;
	}
}
