package modelo;

import java.util.Date;
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
@Table(name = "partido")
public class Partido {
	
	@Id //para indicar que es clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generar automaticamente
	private int id;
	
	@Column(name="fecha")
	private Date fecha;
	
	@Column(name="lugar")
	private String lugar;
	
	@OneToMany(mappedBy = "partido", fetch = FetchType.LAZY)
	List<JuegaEn> combo;

	public Partido(int id, Date fecha, String lugar, List<JuegaEn> combo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.lugar = lugar;
		this.combo = combo;
	}
	
	public Partido() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public List<JuegaEn> getCombo() {
		return combo;
	}

	public void setCombo(List<JuegaEn> combo) {
		this.combo = combo;
	}

	@Override
	public String toString() {
		return "Partido [id=" + id + ", fecha=" + fecha + ", lugar=" + lugar + "]";
	}
	
	

}
