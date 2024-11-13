package reloj;

public class Mensajero implements Runnable{
	
	String nombre;
	Timer timer;
	
	public Mensajero(String nombre, Timer timer) {
		this.nombre = nombre;
		this.timer = timer;
	}
	
	public void run() {
		while(true) {
			synchronized (timer) {
				while(timer.getSegundoGlobal() == timer.getSegundoPropio()) {
					try {
						timer.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println(nombre + " ha modificado el programa en el segundo " + timer.getSegundoPropio());
				timer.setSegundoGlobal();
				
				synchronized (timer.getDespertador()) {
					timer.getDespertador().notifyAll();	
				}
			}		
		}
		
	}

}
