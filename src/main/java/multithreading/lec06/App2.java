package multithreading.lec06;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class AddOperation implements Runnable {

	// no need to synchronize this. it is thread-safe
	private CountDownLatch latch;

	public AddOperation(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("AddOperation Started.");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("AddOperation Thread: " + Thread.currentThread().getName());

		latch.countDown();

		System.out.println("AddOperation Latch count: " + latch.getCount());
	}
	
	public int value() {
		return 1;
	}
}

class SubtractionOperation implements Runnable {

	// no need to synchronize this. it is thread-safe
	private CountDownLatch latch;

	public SubtractionOperation(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("SubtractionOperation Started.");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("SubtractionOperation Thread: " + Thread.currentThread().getName());

		latch.countDown();

		System.out.println("SubtractionOperation Latch count: " + latch.getCount());
	}
	
	public int value() {
		return 2;
	}
}

class DivisionOperation implements Runnable {

	// no need to synchronize this. it is thread-safe
	private CountDownLatch latch;

	public DivisionOperation(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("DivisionOperation Started.");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("DivisionOperation Thread: " + Thread.currentThread().getName());

		latch.countDown();

		System.out.println("DivisionOperation Latch count: " + latch.getCount());
	}
	
	public int value() {
		return 3;
	}
}
public class App2 {

	private final static int NUMBER_OF_LATCH = 3;
	private final static int NUMBER_OF_THREAD = 3;

	public static void main(String[] args) {

		CountDownLatch latch = new CountDownLatch(NUMBER_OF_LATCH);

		ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREAD);

//		for (int i = 0; i < 3; i++) {
//			executor.submit(new Processor(latch)); // designate thread 1 to a
//													// task, thread 2 to another
//													// task and so on
//		}
		AddOperation add = new AddOperation(latch);
		SubtractionOperation sub = new SubtractionOperation(latch);
		DivisionOperation div = new DivisionOperation(latch);
		
		executor.submit(add);
		executor.submit(sub);
		executor.submit(div);
		
		executor.shutdown();
		
		try {

			// wait until latch countdown = 0 i.e all designated threads
			// completed their tasks
			latch.await();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Completed. Total: " + (add.value() + sub.value() + div.value()));		
		
		//System.exit(1);
	}

}
