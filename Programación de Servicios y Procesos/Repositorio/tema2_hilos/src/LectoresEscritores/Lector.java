package LectoresEscritores;

public class Lector implements Runnable{
	
	private String nombre;
	private Libro libro;
	private Object gafas; // objeto usado para los métodos wait() y notifyAll()
	
	public Lector(String nombre, Libro libro, Object gafas) {
		this.nombre = nombre;
		this.libro = libro;
		this.gafas = gafas;
	}
	
	public void run() {
		
		while(!(libro.getNumEscritores() > 0)) {
			synchronized (gafas) {
				try {
					gafas.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}			
		}
		
		libro.añadirLector();
		leer();
		libro.restarLector();
		
	}
	
	public void leer() {
		System.out.println("El lector " + nombre + " ha leído:\n " + libro.getContenido() + "\n");
		
	}
	
	public void despertarEscritores() {
		
	}
	

}
