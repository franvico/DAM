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
@Table(name = "proyecto")
public class Proyecto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_proy;
	
	@Column(name = "f_inicio")
	private Date f_inicio;
	
	@Column(name = "f_fin")
	private Date f_fin;
	
	@Column(name = "nom_proy")
	private String nom_proy;
	
	@OneToMany(mappedBy = "proyecto", fetch = FetchType.LAZY)
	List<ProyectoSede> proyectosSede;

	public Proyecto(int id_proy, Date f_inicio, Date f_fin, String nom_proy, List<ProyectoSede> proyectosSede) {
		super();
		this.id_proy = id_proy;
		this.f_inicio = f_inicio;
		this.f_fin = f_fin;
		this.nom_proy = nom_proy;
		this.proyectosSede = proyectosSede;
	}
	
	public Proyecto() {
		
	}

	public int getId_proy() {
		return id_proy;
	}

	public void setId_proy(int id_proy) {
		this.id_proy = id_proy;
	}

	public Date getF_inicio() {
		return f_inicio;
	}

	public void setF_inicio(Date f_inicio) {
		this.f_inicio = f_inicio;
	}

	public Date getF_fin() {
		return f_fin;
	}

	public void setF_fin(Date f_fin) {
		this.f_fin = f_fin;
	}

	public String getNom_proy() {
		return nom_proy;
	}

	public void setNom_proy(String nom_proy) {
		this.nom_proy = nom_proy;
	}

	@Override
	public String toString() {
		return "Proyecto [id_proy=" + id_proy + ", f_inicio=" + f_inicio + ", f_fin=" + f_fin + ", nom_proy=" + nom_proy
				+ "]";
	}
	
	

}
