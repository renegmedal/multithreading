package multithreading.lec10;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private int count = 0;
	
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	private void increment() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException {
		lock.lock();
		
		System.out.println("Waiting.....");
		cond.await();   // relinquish control to secondThread and then wait
		
		System.out.println("Woken up!");
		try {
			increment();
		} finally {
			lock.unlock(); // this is the disadvantage of ReentrantLock. You have to unlock it always.
		}
		
		
	}
	
	public void secondThread() throws InterruptedException {
	
		 
		Thread.sleep(1000); // to let firstThread to run first
		 
		lock.lock(); 
		
		System.out.println("Press the return key!");
		
		new Scanner(System.in).nextLine();
		
		System.out.println("Got return key!");
	
		cond.signal();  // turn over the control to firstThread cond.await()
		
		System.out.println("secondThread increments...");
		
		try {
			increment();
		} finally {
			lock.unlock();
		}
		
	}
	
	public void finished() {
		System.out.println("Count is: " + count);
	}
}
