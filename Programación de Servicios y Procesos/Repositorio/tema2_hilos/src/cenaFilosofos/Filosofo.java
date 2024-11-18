package cenaFilosofos;

public class Filosofo implements Runnable{
	
	private String nombre;
	private Palillo palilloIzquierdo;
	private Palillo palilloDerecho;
	
	public Filosofo(String nombre, Palillo palilloIzq, Palillo palilloDech) {
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
			
			System.out.println(nombre + " ha cogido el palillo izquierdo");
			palilloIzquierdo.cogerPalillo();
			
//			}
			
//			synchronized (palilloDerecho) {
				
			
			
			while(palilloDerecho.enUso()) {
				synchronized (palilloDerecho) {				
					try {
						System.out.println("---" + nombre + " a la espera del palillo derecho---");
						palilloIzquierdo.dejarPalillo();
						palilloDerecho.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			System.out.println(nombre + " ha cogido el palillo derecho");
			palilloDerecho.cogerPalillo();
//			}
			comer(platos);
			platos++;
		}
		
	}
	public void comer(int platos) {
		System.out.println(nombre + " ha comido " + platos + " platos");
		palilloIzquierdo.dejarPalillo();
		palilloDerecho.dejarPalillo();
	}
	
	public String getNombre() {
		return this.nombre;
	}
	public Palillo getPalilloIzquierdo() {
		return this.palilloIzquierdo;
	}
	public Palillo getPalilloDerecho() {
		return this.palilloDerecho;
	}

}
