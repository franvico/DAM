package com.dam2.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dam2.model.Cocina;
import com.dam2.model.Ingrediente;
import com.dam2.model.Receta;
import com.dam2.model.RecetaIngrediente;
import com.dam2.repository.CocinaRepository;
import com.dam2.repository.IngredienteRepository;
import com.dam2.repository.RecetaRepository;


@Service
public class RecetasService {
	
	@Autowired
	RecetaRepository recetaRepo;
	@Autowired
	CocinaRepository cocinaRepo;
	@Autowired
	IngredienteRepository ingrRepo;
	
	/**
	 * Método que dado un id de receta y dos id's de cocina, 
	 * mueve una receta de un tipo de cocina a la otra, 
	 * indicando además en un mensaje tanto el nombre de la
	 * receta que se está moviendo como los nombres de la
	 * cocina antigua y nueva
	 */
	public void cambiaReceta(int idReceta, int idCocinaOrigen, int idCocinaDestino) {
		
		Receta receta = recetaRepo.findById(idReceta).get();
		Cocina cocinaOrigen = cocinaRepo.findById(idCocinaOrigen).get();
		Cocina cocinaDestino = cocinaRepo.findById(idCocinaDestino).get();
		receta.setCocina(cocinaDestino);
		recetaRepo.save(receta);
		
		System.out.println("Se ha movido la receta " + receta.getNombre() + " de la cocina " + cocinaOrigen.getNombre() + " a la cocina " + cocinaDestino.getNombre());
		
	}
	
	/**
	 * Método que añade un ingrediente a una receta. Debe verificar
	 * que no lo contenga previamente
	 */
	public void addIngrediente(int idIng, int idReceta) {
		
		Receta r = recetaRepo.findById(idReceta).get();
		Ingrediente i = ingrRepo.findById(idIng).get();
		
		RecetaIngrediente ri = recetaRepo.findByRecetaEIngrediente(r, i);
		if(ri != null) {
			System.out.println("Ese ingrediente ya esta en la receta");
			return;
		}
		else {
			recetaRepo.insertIngredienteEnReceta(r,i);
		}
		
		
	}
	
	
}
