package RaceCondition;

public class RC implements Runnable {
	public static long c1 = 0;
	public static long c2  = 0;
	
	Object candado;
	
	Object candado1;
	Object candado2;
	
	public RC(Object candado) {
		this.candado = candado;
	}

	public static void main(String[] args) throws InterruptedException {
		Object candado1 = new Object();
		Object candado2 = new Object();		
		
		Thread t = new Thread(new RC(candado1));
		t.start();

		Thread t2 = new Thread(new RC(candado2));
		t2.start();

		t.join();
		t2.join();
		
		System.out.println("Total:" + c1);
		System.out.println("Total:" + c2);
	}

	public void run() {
		for (int i = 0; i < 1000; i++) {
			inc1();
			inc2();
		}
	}
	public synchronized void inc1() {
		synchronized(candado1) {
			c1++;			
		}

	}
	public synchronized void inc2() {
		synchronized(candado2) {
			c2++;			
		}
	}
}