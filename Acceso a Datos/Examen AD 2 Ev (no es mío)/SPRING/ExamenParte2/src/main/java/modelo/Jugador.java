package modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="jugador") 
public class Jugador {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@OneToMany(mappedBy = "jugador", fetch = FetchType.LAZY)
	List<JuegaEn> combo;

	public Jugador(int id, String nombre, List<JuegaEn> combo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.combo = combo;
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

	public List<JuegaEn> getCombo() {
		return combo;
	}

	public void setCombo(List<JuegaEn> combo) {
		this.combo = combo;
	}

	@Override
	public String toString() {
		return "Jugador [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
	
}
