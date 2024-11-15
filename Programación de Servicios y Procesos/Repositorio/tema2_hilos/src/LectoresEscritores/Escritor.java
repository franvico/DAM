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
		// Si ya hay un esritor trabajando, me duermo
		// hasta que acaben los lectores que hay leyendo, me duermo. Pero bloqueo a los lectores nuevos
		bloquearMasLectores();
		
		while() { // compruebo que los lectores actuales han terminado
			boli.
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
	
	public void bloquearMasLectores() {
		libro.setPermitirMasLectores(false);
		
	}

}
