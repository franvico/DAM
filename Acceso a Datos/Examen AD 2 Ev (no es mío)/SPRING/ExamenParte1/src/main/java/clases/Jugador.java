package clases;

public class Jugador {
	
	private int id;
	private String nombre;
	
	public Jugador(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public Jugador() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Jugador [id=" + id + ", nombre=" + nombre + "]";
	}
	
	

}
