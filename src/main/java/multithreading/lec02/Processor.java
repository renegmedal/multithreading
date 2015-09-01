package multithreading.lec02;

public class Processor extends Thread {

	private volatile boolean running = true;
	
	public void run() {
		
		// an environment may decide to cache
		// this block has a copy on different thread
		while (running) {
			System.out.println("Hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		running = false;
	}
}
