package modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="juegaen")
public class JuegaEn {
	
	@EmbeddedId
	private JuegaEnPK id;
	
	@ManyToOne
	@JoinColumn(name = "idPartido", insertable = false, updatable = false)
	private Partido partido;
	
	@ManyToOne
	@JoinColumn(name = "idJugador", insertable = false, updatable = false)
	private Jugador jugador;

	public JuegaEn(JuegaEnPK id, Partido partido, Jugador jugador) {
		super();
		this.id = id;
		this.partido = partido;
		this.jugador = jugador;
	}
	
	public JuegaEn() {
		
	}

	public JuegaEnPK getId() {
		return id;
	}

	public void setId(JuegaEnPK id) {
		this.id = id;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	@Override
	public String toString() {
		return "JuegaEn [id=" + id + ", partido=" + partido + ", jugador=" + jugador + "]";
	}
	
	

}
