package cenaFilosofos;

public class Filosofo2 implements Runnable{
	
	private String nombre;
	private Palillo2 palilloIzquierdo;
	private Palillo2 palilloDerecho;
	
	public Filosofo2(String nombre, Palillo2 palilloIzq, Palillo2 palilloDech) {
		this.nombre = nombre;
		this.palilloIzquierdo = palilloIzq;
		this.palilloDerecho = palilloDech;
	}
	
	public void run() {
		
		int platos = 1;
		while(platos <= 5) {
//			synchronized (palilloIzquierdo) {
				
			
			while(palilloIzquierdo.enUso()) {
				synchronized (palilloIzquierdo) {
					try {
						System.out.println("---" + nombre + " a la espera del palillo izquierdo---");
						palilloIzquierdo.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}			
			}
			
			palilloIzquierdo.cogerPalillo(nombre, "izquierdo");
			
//			}
			
//			synchronized (palilloDerecho) {
				
			
			
			while(palilloDerecho.enUso()) {
				synchronized (palilloDerecho) {				
					try {
						System.out.println("---" + nombre + " a la espera del palillo derecho---");
						palilloIzquierdo.dejarPalillo(nombre, "izquierdo");
						palilloDerecho.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			palilloDerecho.cogerPalillo(nombre, "derecho");
//			}
			comer(platos);
			platos++;
		}
		
	}
	public void comer(int platos) {
		System.out.println(nombre + " ha comido " + platos + " platos");
		palilloIzquierdo.dejarPalillo(nombre, "izquierdo");
		palilloDerecho.dejarPalillo(nombre, "derecho");
	}
	
	public String getNombre() {
		return this.nombre;
	}
	public Palillo2 getPalilloIzquierdo() {
		return this.palilloIzquierdo;
	}
	public Palillo2 getPalilloDerecho() {
		return this.palilloDerecho;
	}

}