package LectoresEscritores;

public class Escritor implements Runnable{
	
	private String nombre;
	private Libro libro;
	private Object boli; // objeto usado para wait() y notifyAll()
	
	public Escritor(String nombre, Libro libro, Object boli) {
		this.nombre = nombre;
		this.libro = libro;
		this.boli = boli;
	}
	
	public void run() {
				
		// dormir a otros Escritores?? NO PUEDO, CADA UNO SE DUERME A SÍ MISMO
		// Si ya hay un escritor trabajando, me duermo
		// hasta que acaben los lectores que hay leyendo, me duermo. Pero bloqueo a los lectores nuevos
		libro.añadirEscritor();
		
		while(libro.getNumLectores() > 0) { // compruebo que los lectores actuales han terminado y si no duermo
			synchronized (boli) {
				try {
					boli.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}			
		}		
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void escribir() {
		synchronized (boli) {
			libro.setContenido("\n\tEl escritor " + nombre + " ha escrito.");
		}
		
	}
	
	public void despertarLectores() {
		
	}
	
	public void despertarEscritores() {
		
	}

}
