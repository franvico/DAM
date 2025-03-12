package com.dam2.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "ingrediente")
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ingrediente")
	private Integer id;
	
	private String nombre;
	
	@OneToMany(mappedBy = "ingrediente")
	private List<RecetaIngrediente> recetaIngrediente;

	public Ingrediente(Integer id, String nombre, List<RecetaIngrediente> recetaIngrediente) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.recetaIngrediente = recetaIngrediente;
	}

	public Ingrediente() {
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

	public List<RecetaIngrediente> getRecetaIngrediente() {
		return recetaIngrediente;
	}

	public void setRecetaIngrediente(List<RecetaIngrediente> recetaIngrediente) {
		this.recetaIngrediente = recetaIngrediente;
	}
	
	
	
}
