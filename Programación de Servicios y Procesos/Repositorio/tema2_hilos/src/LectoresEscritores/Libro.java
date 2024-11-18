package LectoresEscritores;

public class Libro {
	
	private String contenido;
	private int numEscritores;
	private int numLectores;
	private boolean permitirMasLectores;
	private Object boli; // objeto usado para wait() y notifyAll() de Escritores
	private Object gafas; // objeto usado para los métodos wait() y notifyAll() de Lectores
	
	public Libro(Object boli, Object gafas) {
		this.contenido = "LIBRO DE LECTORES Y ESCRITORES\n";
		this.numEscritores = 0;
		this.numLectores = 0;
		this.permitirMasLectores = true;
		this.boli = boli;
		this.gafas = gafas;
	}
	
	// CONTROLAR CONTENIDO DEL LIBRO
	public synchronized void setContenido(String contenido, String nombre) {
		this.contenido += contenido;
		System.out.println("El escritor " + nombre + " ha escrito.");
	}
	
	public String getContenido() {
		return contenido;
	}
	
	// CONTROLAR LECTORES
	public synchronized int getNumLectores() {
		return this.numLectores;
	}
	public synchronized void añadirLector(String nombre) {
		System.out.println("Añadido lector " + nombre);
		this.numLectores++;
	}
	public synchronized void restarLector() {
		this.numLectores--;
	}
	
	// CONTROLAR ESCRITORES
	public synchronized int getNumEscritores() {
		return this.numEscritores;
	}
	public synchronized void añadirEscritor(String nombre) {
		this.numEscritores++;
		System.out.println("Añadido escritor " + nombre);
	}
	public synchronized void restarEscritor() {
		this.numEscritores--;
	}
	
	// OBTENER MONITORES PARA ESCRITORES Y LECTORES
	public Object getBoli() {
		return this.boli;
	}
	public Object getGafas() {
		return this.gafas;
	}
	

}
