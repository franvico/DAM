package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class JuegaEnPK {
	
	@Column(name = "idJugador")
    private int idJugador;
	
	@Column(name = "idPartido")
    private int idPartido;

	public JuegaEnPK(int idJugador, int idPartido) {
		super();
		this.idJugador = idJugador;
		this.idPartido = idPartido;
	}
	
	public JuegaEnPK() {
		
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
	
	

}
