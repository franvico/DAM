package cenaFilosofos;

public class Palillo {
	
	private boolean enUso;
	
	public Palillo() {
		this.enUso = false;
	}
	
	public synchronized boolean enUso() {
		return this.enUso;
	}	
	public synchronized void cogerPalillo() {
		this.enUso = true;
	}
	public synchronized void dejarPalillo() {
		this.enUso = false;
		synchronized (this) {
			notifyAll();	
		}	
	}
}
