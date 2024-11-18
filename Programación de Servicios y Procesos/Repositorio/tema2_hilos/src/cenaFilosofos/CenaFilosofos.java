package cenaFilosofos;

import java.util.ArrayList;
import java.util.HashMap;

public class CenaFilosofos {

	public static void main(String[] args) throws InterruptedException {
		
		ArrayList<Palillo> palillos = new ArrayList<Palillo>();
		ArrayList<Thread> lt = new ArrayList<Thread>();
		
		Palillo p0 = new Palillo(); palillos.add(p0);
		Palillo p1 = new Palillo(); palillos.add(p1);
		Palillo p2 = new Palillo(); palillos.add(p2);
		Palillo p3 = new Palillo(); palillos.add(p3);
		Palillo p4 = new Palillo(); palillos.add(p4);
		
		Thread t0 = new Thread(new Filosofo("Aristóteles", p0, p4)); lt.add(t0);
		Thread t1 = new Thread(new Filosofo("Platón", p1, p0)); lt.add(t1);
		Thread t2 = new Thread(new Filosofo("Séneca", p2, p1)); lt.add(t2);
		Thread t3 = new Thread(new Filosofo("Kant", p3, p2)); lt.add(t3);
		Thread t4 = new Thread(new Filosofo("Descartes", p4, p3)); lt.add(t4);
		
		for(Thread t : lt) {
			t.start();
		}
		for(Thread t : lt) {
			t.join();
		}
		

	}

}
