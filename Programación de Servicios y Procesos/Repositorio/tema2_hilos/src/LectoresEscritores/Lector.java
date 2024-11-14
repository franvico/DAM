package LectoresEscritores;

public class Lector {
	
	private String nombre;
	private Libro libro;
	private Object gafas; // objeto usado para los métodos wait() y notifyAll()
	
	public Lector(String nombre, Libro libro, Object gafas) {
		this.nombre = nombre;
		this.libro = libro;
		this.gafas = gafas;
	}
	
	public String leer() {
		return libro.getContenido();
	}
	

}
