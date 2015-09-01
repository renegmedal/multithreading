package multithreading.lec05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

	private final static int NUMBER_OF_THREADS = 2;
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

		// submit 5 task
		for (int i=0; i<5; i++){
			executor.submit(new Processor(i));
		}
		
		System.out.println("All tasks submitted.");
		//
		// Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted.
		//
		// This method does not wait for previously submitted tasks to complete execution. Use awaitTermination to do that.
		//
		executor.shutdown();			
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All tasks completed.");
	}

}
