package com.dam2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dam2.model.Ingrediente;
import com.dam2.model.Receta;
import com.dam2.model.RecetaIngrediente;

public interface RecetaRepository extends JpaRepository<Receta,Integer> {
	
	
	@Query("SELECT ri FROM RecetaIngrediente ri WHERE receta = :receta AND ingrediente = :ingrediente")
    RecetaIngrediente findByRecetaEIngrediente(@Param("receta") Receta receta, @Param("ingrediente") Ingrediente ingrediente);
	
	@Query("INSERT INTO RecetaIngrediente VALUES (receta, ingrediente)")
    RecetaIngrediente insertIngredienteEnReceta(@Param("receta") Receta receta, @Param("ingrediente") Ingrediente ingrediente);
	

}
