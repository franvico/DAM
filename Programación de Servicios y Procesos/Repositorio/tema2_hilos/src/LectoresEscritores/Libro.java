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
	
	public synchronized void setContenido(String contenido) {
		this.contenido += contenido;
	}
	
	public String getContenido() {
		return contenido;
	}	
	public synchronized int getNumLectores() {
		return this.numLectores;
	}
	public void añadirLector() {
		this.numLectores++;
	}
	public void restarLector() {
		this.numLectores--;
	}
	public synchronized int getNumEscritores() {
		return this.numEscritores;
	}
	public synchronized void añadirEscritor() {
		this.numEscritores++;
	}
	public Object getBoli() {
		return this.boli;
	}
	public Object getGafas() {
		return this.gafas;
	}
	

}
