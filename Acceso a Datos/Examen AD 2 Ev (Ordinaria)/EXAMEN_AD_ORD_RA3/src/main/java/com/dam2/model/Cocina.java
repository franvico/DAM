package com.dam2.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "cocina")
public class Cocina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cocina")
	private Integer id;	
	private String nombre;
	
	
	@OneToMany(mappedBy = "cocina")
	private List<Receta> recetas;


	public Cocina(Integer id, String nombre, List<Receta> recetas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.recetas = recetas;
	}


	public Cocina() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<Receta> getRecetas() {
		return recetas;
	}


	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}
	
	

}
