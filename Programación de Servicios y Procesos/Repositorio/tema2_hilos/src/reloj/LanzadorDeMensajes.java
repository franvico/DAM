package reloj;

import java.util.ArrayList;

public class LanzadorDeMensajes {

	public static void main(String[] args) throws InterruptedException {
		
		
		// primero instancio el candado Timer común para todos los mensajeros
		// este necesita un candado propio para ser despertado por los mensajeros que se inicia en el propio constructor de Timer
		// por último también lanzo el thread propio del timer para que empiece a contar los segundos
		Timer timer = new Timer();		
		Thread tTimer = new Thread(timer);
		tTimer.start();
		
		// ahora creo los hilos mensajeros		
		ArrayList<Thread> lt = new ArrayList<Thread>();
		
		Thread t1 = new Thread(new Mensajero("Juan", timer)); lt.add(t1);
		Thread t2 = new Thread(new Mensajero("Irene", timer)); lt.add(t2);
		Thread t3 = new Thread(new Mensajero("Raquel", timer)); lt.add(t3);
		Thread t4 = new Thread(new Mensajero("Pepe", timer)); lt.add(t4);
		Thread t5 = new Thread(new Mensajero("Julián", timer)); lt.add(t5);
		
		for(Thread t : lt) {
			t.start();
		}
		
		for(Thread t : lt) {
			t.join();
		}
	}

}
