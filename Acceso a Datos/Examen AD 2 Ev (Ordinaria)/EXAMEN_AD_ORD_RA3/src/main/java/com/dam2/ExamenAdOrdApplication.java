package com.dam2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.dam2.service.RecetasService;

@SpringBootApplication
public class ExamenAdOrdApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ExamenAdOrdApplication.class, args);
		
		
		// LLAMO A LOS .SERVICES
		RecetasService recetasService = context.getBean(RecetasService.class);
		
		// LLAMOS A LOS MÉTODOS
//		recetasService.cambiaReceta(1, 1, 2);
		
		// De este funciona la comprobación de si ese ingrediente ya está asignado a la receta pero no consigo hacer el insert si no lo está
//		recetasService.addIngrediente(1, 1);
		
		
		
	}

}
