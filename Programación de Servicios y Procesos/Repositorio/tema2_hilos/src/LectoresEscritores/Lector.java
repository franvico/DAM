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
		
	}
	
	public String leer() {
		return libro.getContenido();
	}
	
	public void despertarEscritores() {
		
	}
	

}
