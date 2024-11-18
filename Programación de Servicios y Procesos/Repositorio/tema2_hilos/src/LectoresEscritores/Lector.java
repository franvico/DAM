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
		
		while(libro.getNumEscritores() > 0) {
			synchronized (gafas) {
				try {
					System.out.println("El lector " + nombre + " está a la espera.");
					gafas.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}			
		}
		synchronized (libro) {
			libro.añadirLector(nombre);
			leer();
			libro.restarLector();
			// añado este método porque en ocasiones, si queda una última tanda de lectores por despertar,
			// estos no se van a despertar porque no habrá un escritor que los despierte.
			despertarLectores();
			
		}
		
	}
	
	public void leer() {
		System.out.println("\nEl lector " + nombre + " ha leído:\n\n " + libro.getContenido() + "\n");
		
	}
	
	public void despertarEscritores() {
		
		if(libro.getNumEscritores() > 0) {
			synchronized (libro.getBoli()) {
				System.out.println("\nDESPERTANDO A ESCRITORES DESDE LECTORES\n");
				libro.getBoli().notifyAll();	
			}
			
		}
	}
	public void despertarLectores() {
		if((libro.getNumEscritores() == 0)) {
			synchronized (gafas) {
				System.out.println("\nDESPERTANDO A LECTORES DESDE LECTORES\n");
				gafas.notifyAll();	
			}
		}
	}
	

}
