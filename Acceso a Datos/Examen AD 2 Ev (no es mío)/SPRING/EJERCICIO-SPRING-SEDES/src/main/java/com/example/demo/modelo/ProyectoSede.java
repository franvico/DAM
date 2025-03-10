package com.example.demo.modelo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="proyecto_sede")
public class ProyectoSede {
	
	@EmbeddedId
    private ProyectoSedePK id;
	
	@Column(name="f_inicio")
	private Date f_inicio;
	
	@Column(name="f_fin")
	private Date f_fin;
	
	@ManyToOne
	@JoinColumn(name = "id_proy", insertable = false, updatable = false)
	private Proyecto proyecto;

	@ManyToOne
	@JoinColumn(name = "id_sede", insertable = false, updatable = false)
	private Sede sede;

	public ProyectoSede(ProyectoSedePK id, Date f_inicio, Date f_fin, Proyecto proyecto, Sede sede) {
		super();
		this.id = id;
		this.f_inicio = f_inicio;
		this.f_fin = f_fin;
		this.proyecto = proyecto;
		this.sede = sede;
	}

	public ProyectoSede() {
		
	}

	public ProyectoSedePK getId() {
		return id;
	}

	public void setId(ProyectoSedePK id) {
		this.id = id;
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

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	@Override
	public String toString() {
		return "ProyectoSede [id=" + id + ", f_inicio=" + f_inicio + ", f_fin=" + f_fin + ", proyecto=" + proyecto
				+ ", sede=" + sede + "]";
	}
	
	
	
}
