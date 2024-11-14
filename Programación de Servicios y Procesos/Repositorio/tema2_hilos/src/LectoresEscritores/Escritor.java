package LectoresEscritores;

public class Escritor {
	
	private String nombre;
	private Libro libro;
	private Object boli; // objeto usado para wait() y notifyAll()
	
	public Escritor(String nombre, Libro libro, Object boli) {
		this.nombre = nombre;
		this.libro = libro;
		this.boli = boli;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void escribir(String contenido) {
		synchronized (boli) {
			libro.setContenido(contenido);
		}
		
	}

}
