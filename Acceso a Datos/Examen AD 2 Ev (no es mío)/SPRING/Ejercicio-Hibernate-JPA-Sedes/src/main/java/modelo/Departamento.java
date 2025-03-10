package modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamento")
public class Departamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_depto;
	
	@Column(name = "nom_depto")
	private String nom_depto;
	
	@ManyToOne
	@JoinColumn(name = "id_sede", insertable = false, updatable = false)
	Sede sede;
	
	@OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
	List<Empleado> empleados;

	public Departamento(int id_depto, String nom_depto, Sede sede, List<Empleado> empleados) {
		super();
		this.id_depto = id_depto;
		this.nom_depto = nom_depto;
		this.sede = sede;
		this.empleados = empleados;
	}
	
	public Departamento() {
		
	}

	public int getId_depto() {
		return id_depto;
	}

	public void setId_depto(int id_depto) {
		this.id_depto = id_depto;
	}

	public String getNom_depto() {
		return nom_depto;
	}

	public void setNom_depto(String nom_depto) {
		this.nom_depto = nom_depto;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	@Override
	public String toString() {
		return "Departamento [id_depto=" + id_depto + ", nom_depto=" + nom_depto + ", sede=" + sede + "]";
	}
	
	

}
