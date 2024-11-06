package tema4_JASPER_REPORT;

public class DatosAlumno {
	
	private String nombre;
	private int nota;
	
	
	public DatosAlumno() {
		super();
	}
	
	
	
	public DatosAlumno(String nombre, int nota) {
		super();
		this.nombre = nombre;
		this.nota = nota;
	}



	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	
	

}
