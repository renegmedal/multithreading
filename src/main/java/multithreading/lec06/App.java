package multithreading.lec06;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {

	// no need to synchronize this. it is thread-safe
	private CountDownLatch latch;
	
	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}
	
	
	@Override
	public void run() {
		System.out.println("Started.");
		
		try {
			
			Thread.sleep(3000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Thread: " + Thread.currentThread().getName());
		
		latch.countDown();
		
		System.out.println("Latch count: " + latch.getCount());
		
	}
	
}

public class App {

	private final static int NUMBER_OF_LATCH = 3;
	private final static int NUMBER_OF_THREAD = 3;
	
	public static void main(String[] args) {
		
		CountDownLatch latch = new CountDownLatch(NUMBER_OF_LATCH);
		
		ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREAD);
		
		for (int i=0; i<3; i++) {
			executor.submit(new Processor(latch)); // designate thread 1 to a task, thread 2 to another task and so on
		}
		
		executor.shutdown();
		
		try {
			
			//wait until latch countdown = 0 i.e all designated threads completed their tasks			
			latch.await();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		System.out.println("Completed.");
		//System.exit(1);
	}

}

