package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="trenes")
public class Tren {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codTren;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="tipo")
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name = "codLinea", nullable = false)//es NotNull
	private Linea linea;
	
	@ManyToOne
	@JoinColumn(name = "codCochera", nullable = false)
	private Cochera cochera;

	public Tren(int codTren, String nombre, String tipo, Linea linea, Cochera cochera) {
		super();
		this.codTren = codTren;
		this.nombre = nombre;
		this.tipo = tipo;
		this.linea = linea;
		this.cochera = cochera;
	}
	
	public Tren() {
		
	}

	public int getCodTren() {
		return codTren;
	}

	public void setCodTren(int codTren) {
		this.codTren = codTren;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}

	public Cochera getCochera() {
		return cochera;
	}

	public void setCochera(Cochera cochera) {
		this.cochera = cochera;
	}

	@Override
	public String toString() {
		return "Tren [codTren=" + codTren + ", nombre=" + nombre + ", tipo=" + tipo + ", linea=" + linea + ", cochera="
				+ cochera + "]";
	}
	
	

}
