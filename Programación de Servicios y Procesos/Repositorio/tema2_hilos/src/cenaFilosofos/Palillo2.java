package cenaFilosofos;

public class Palillo2 {
	
	private boolean enUso;
	
	public Palillo2() {
		this.enUso = false;
	}
	
	public synchronized boolean enUso() {
		return this.enUso;
	}	
	public synchronized void cogerPalillo(String nombre, String posicionPalillo) {
		System.out.println(nombre + " ha cogido el palillo " + posicionPalillo);
		this.enUso = true;
	}
	public synchronized void dejarPalillo(String nombre, String posicionPalillo) {
		System.out.println(nombre + " ha cogido el palillo " + posicionPalillo);
		this.enUso = false;
		synchronized (this) {
			notifyAll();	
		}	
	}
}