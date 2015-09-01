package multithreading.lec03;

import java.util.concurrent.atomic.AtomicInteger;

public class App {

	
	//private volatile int count = 0; // volatile won't totally solve the problem

	
	private int count = 0;
	// using intrinsic lock from monitor object 
	// it is not always ideal to use intrinsic lock synchronized
	private void increment() {
		synchronized(this) {
		   count++;
		}
	}

	// Alternative solution to synchronized 
//	private AtomicInteger count = new AtomicInteger();	
//	private void increment() {
//		//count.incrementAndGet();
//		count.addAndGet(1);
//	}
	
	
	public static void main(String[] args) {
		App app = new App();
		app.doWork();

	}

	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment(); //count++;
					//System.out.println("Thread 1:" + Thread.currentThread().getName());
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment(); //count++;
					//System.out.println("Thread 2:" + Thread.currentThread().getName());
				}
			}
		});

		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("count is: " + count);

	}

}
