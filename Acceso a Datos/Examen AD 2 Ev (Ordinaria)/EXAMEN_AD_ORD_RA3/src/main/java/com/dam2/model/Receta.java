package com.dam2.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "receta")
public class Receta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_receta")
	private Integer id;
	
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "id_cocina")
	private Cocina cocina;
	
	
	@OneToMany(mappedBy = "receta")
	private List<RecetaIngrediente> recetaIngrediente;


	public Receta(Integer id, String nombre, Cocina cocina, List<RecetaIngrediente> recetaIngrediente) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cocina = cocina;
		this.recetaIngrediente = recetaIngrediente;
	}


	public Receta() {
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


	public Cocina getCocina() {
		return cocina;
	}


	public void setCocina(Cocina cocina) {
		this.cocina = cocina;
	}


	public List<RecetaIngrediente> getRecetaIngrediente() {
		return recetaIngrediente;
	}


	public void setRecetaIngrediente(List<RecetaIngrediente> recetaIngrediente) {
		this.recetaIngrediente = recetaIngrediente;
	}
	
	

}
