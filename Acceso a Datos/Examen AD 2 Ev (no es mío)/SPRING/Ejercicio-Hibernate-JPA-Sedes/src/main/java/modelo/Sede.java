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
@Table(name = "sede")
public class Sede {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_sede;
	
	@Column(name = "nom_sede")
	private String nom_sede;
	
	@OneToMany(mappedBy = "sede", fetch = FetchType.LAZY)
	List<ProyectoSede> proyectosSede;
	
	@OneToMany(mappedBy = "sede", fetch = FetchType.LAZY)
	List<Departamento> departamentos;

	public Sede(int id_sede, String nom_sede, List<ProyectoSede> proyectosSede, List<Departamento> departamentos) {
		super();
		this.id_sede = id_sede;
		this.nom_sede = nom_sede;
		this.proyectosSede = proyectosSede;
		this.departamentos = departamentos;
	}
	
	public Sede() {
		
	}

	public int getId_sede() {
		return id_sede;
	}

	public void setId_sede(int id_sede) {
		this.id_sede = id_sede;
	}

	public String getNom_sede() {
		return nom_sede;
	}

	public void setNom_sede(String nom_sede) {
		this.nom_sede = nom_sede;
	}

	@Override
	public String toString() {
		return "Sede [id_sede=" + id_sede + ", nom_sede=" + nom_sede + "]";
	}
	
	

}
