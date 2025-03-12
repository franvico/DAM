package com.dam2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam2.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente,Integer> {

}
