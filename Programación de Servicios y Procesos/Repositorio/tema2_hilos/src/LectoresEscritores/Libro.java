package LectoresEscritores;

public class Libro {
	
	private String contenido;
	
	public Libro(String contenido) {
		this.contenido = contenido;
	}
	
	public void setContenido(String contenido) {
		this.contenido += " " + contenido;
	}
	
	public String getContenido() {
		return contenido;
	}

}
