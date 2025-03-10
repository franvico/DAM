package clases;

public class JuegaEn {
	
	private int idJugador;
	private int idPartido;
	
	public JuegaEn(int idJugador, int idPartido) {
		super();
		this.idJugador = idJugador;
		this.idPartido = idPartido;
	}
	
	public JuegaEn() {
		
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	@Override
	public String toString() {
		return "JuegaEn [idJugador=" + idJugador + ", idPartido=" + idPartido + "]";
	}
	
	

}
