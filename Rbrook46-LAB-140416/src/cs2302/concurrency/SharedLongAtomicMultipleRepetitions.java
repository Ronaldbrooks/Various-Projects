package cs2302.concurrency;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Two threads will update a shared variable
 * The expected and actual results will be compared
 * 
 * @author rbrook46
 *
 */

public class SharedLongAtomicMultipleRepetitions {

	public static void main(String[] args) throws InterruptedException {
		A runnableA = new A();
		B runnableB = new B();
		Thread thrA = new Thread(runnableA);
		Thread thrB = new Thread(runnableB);
		thrA.start();
		thrB.start();
		Thread.sleep(500);
		System.out.println("Final value of x is " + x );
		long expected = initialX + (aInc + bInc) * numRepetitions;
		System.out.println("Expected value of x is " + expected);
	}
	
	 private static final long initialX = 23; 
	 //private static long x = initialX; // shared variable
	 private static AtomicLong x = new AtomicLong(initialX);
	 
	 private static int numRepetitions = 1000; // number of times A and B add to x
	 private static int aInc = 1;
	 private static int bInc = 2;
	 
	 private static class A implements Runnable {
		 
		@Override 
		public void run () {
			for( int i = 0; i < numRepetitions; i++) {
//			long y = x;
//			x = y + aInc;
//			x = y;
				x.addAndGet(aInc);
		}
			System.out.println("A is Finished");
	 }
 }
	 
	 private static class B implements Runnable {
		 
		 @Override
		 public void run() {
			 for( int i = 0; i < numRepetitions; i++) {
//			 long z = x;
//			 z = z + bInc;
//			 x = z;
				 x.addAndGet(bInc);
		 }
			 System.out.println("B is Finished");
	 }
}
}

