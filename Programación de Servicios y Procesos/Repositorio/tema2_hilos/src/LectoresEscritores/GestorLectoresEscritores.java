package LectoresEscritores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestorLectoresEscritores {

	public static void main(String[] args) throws InterruptedException {
		
		Object boli = new Object();
		Object gafas = new Object();
		
		Libro libro = new Libro(boli, gafas);
		
		String nombres[] = {"Juan", "Pepe", "Ricardo", "Marta", "Stefano", "Raquel", "Lidia", "Marcos", "Roberto", "Kike"};
		List<Thread> lt = new ArrayList<Thread>();
		
		int instancia = 0;
		while(instancia < 10) {
			if(nuevoLector()) {
				Thread t = new Thread(new Lector(nombres[instancia], libro, gafas));
				lt.add(t);
			}
			else {
				Thread t = new Thread(new Escritor(nombres[instancia], libro, boli));
				lt.add(t);
			}
			instancia++;
		}		

		for(Thread t : lt) {
			t.start();
		}
		for(Thread t : lt) {
			t.join();
		}
		
	}
	
	public static boolean nuevoLector() {
		int num = new Random().nextInt(6) + 1;		
		return (num <= 4) ? true : false;
	}

}
