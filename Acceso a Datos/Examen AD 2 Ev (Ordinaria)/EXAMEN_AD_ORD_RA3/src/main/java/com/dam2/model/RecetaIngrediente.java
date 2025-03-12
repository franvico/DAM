package com.dam2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "receta_ingrediente")
public class RecetaIngrediente {
	
	@Id
	@ManyToOne()
	@JoinColumn(name = "id_receta")
	private Receta receta;
	
	@Id
	@ManyToOne()
	@JoinColumn(name = "id_ingrediente")
	private Ingrediente ingrediente;

	public RecetaIngrediente(Receta receta, Ingrediente ingrediente) {
		super();
		this.receta = receta;
		this.ingrediente = ingrediente;
	}

	public RecetaIngrediente() {
		super();
	}

	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	
	
	
}
