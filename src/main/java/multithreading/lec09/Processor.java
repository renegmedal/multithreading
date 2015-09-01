package multithreading.lec09;

import java.util.LinkedList;
import java.util.Random;

public class Processor {

	private LinkedList<Integer> list = new LinkedList<Integer>();
	private static final int LIMIT = 10;
	private Object lock = new Object();
	
	public void produce() throws InterruptedException {
		
		int value = 0;
		
		while (true) {
			synchronized (lock) {
				while(list.size() == LIMIT) {
					lock.wait(); // pass the control to consume().synchronized block when list is full
				}
				list.add(value++);
				lock.notify();  // wake up the thread in wait/sleep
			}
		}
	}

	public void consume() throws InterruptedException {

		Random random = new Random();
		
		while (true) {
			
			synchronized (lock) {
				
				while (list.size() == 0) {
					lock.wait(); // pass the control to produce when list is empty
				}
				
				System.out.print("List size is: " + list.size());
				int value = list.removeFirst();
				System.out.println("; value is: " + value);
				lock.notify(); 
			}
			
			Thread.sleep(random.nextInt(1000));
		}
	}
}
