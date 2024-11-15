package LectoresEscritores;

public class Libro {
	
	private String contenido;
	private int numEscritores;
	private int numLectores;
	private boolean permitirMasLectores;
	
	public Libro() {
		this.contenido = "LIBRO DE LECTORES Y ESCRITORES\n";
		this.numEscritores = 0;
		this.numLectores = 0;
		this.permitirMasLectores = true;
	}
	
	public synchronized void setContenido(String contenido) {
		this.contenido += contenido;
	}
	
	public String getContenido() {
		return contenido;
	}
	
	public void setNumEscritores() {
		this.numEscritores++;
	}
	
	public int getNumEscritores() {	
		return this.numEscritores;
	}
	
	public int getNumLectores() {
		return this.numLectores;
	}
	public void setNumLectores() {
		this.numLectores++;
	}
	
	public synchronized boolean getPermitirMasLectores() {
		return this.permitirMasLectores;
	}
	public synchronized void setPermitirMasLectores(boolean bol) {
		this.permitirMasLectores = bol;
	}
	

}
