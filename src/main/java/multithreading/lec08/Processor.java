package multithreading.lec08;

import java.util.Scanner;

public class Processor {

	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Producer thread running.....");
			wait();
			System.out.println("Producer resumed.");
		}
	}

	public void consume() throws InterruptedException {

		Scanner scanner = new Scanner(System.in);	
		Thread.sleep(2000); // a delay to make sure produce() runs first
		
		synchronized (this) {
			System.out.println("Waiting for return key");
			scanner.nextLine();
			System.out.println("Return key pressed.");
			notify(); // doesn't relinquish control over produce() wait().
			Thread.sleep(5000); // to prove it, product has to wait 5 sec before consume() step out of synchronized block. Since consume() if finished, 
			                    // then product will take control at wait()
		}
	}
}
