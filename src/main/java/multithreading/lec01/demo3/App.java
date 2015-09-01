package multithreading.lec01.demo3;

import multithreading.lec01.demo2.Runner;

public class App {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runner() {
			public void run() {

				for (int i = 0; i < 10; i++) {
					System.out.println("Hello " + i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		t1.start();

	}

}
