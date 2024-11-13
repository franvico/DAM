package reloj;

public class Timer implements Runnable{
	
	// Crear un thread timer que implemente un reloj que cuenta los segundos. Otros Threads usaran este thread para escribir por pantalla un mensaje cada segundo
	
	private int segundoPropio;
	private int segundoGlobal;
	private Object despertador;
	
	public Timer() {
		this.segundoPropio = 0;
		this.segundoGlobal = 0;
		this.despertador = new Object();
	}
	
	public void run() {
		
		while(true) {

			try {
				Thread.sleep(3000);
				sumarSegundoPropio();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public synchronized int getSegundoPropio() {
		return segundoPropio;
	}
	
	public void sumarSegundoPropio() {
		setSegundoPropio();
		synchronized (this) {
			notifyAll();
		}

		synchronized (despertador) {
			try {
				despertador.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		
	}
	
	public synchronized int getSegundoGlobal() {
		return segundoGlobal;
	}
	
	public synchronized void setSegundoGlobal() {
		segundoGlobal++;
	}
	
	public Object getDespertador() {
		return despertador;
	}
	
	public synchronized void setSegundoPropio() {
		segundoPropio++;
	}
	
	
	
	// Hacer un programa que cada segundo escribe una palabra por pantalla. El Usuario puede cambiaar la palabra que se escribe introduciendo una nueva palabra por teclado
	
	

}
