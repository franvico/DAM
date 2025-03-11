package com.dam2.main;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.dam2.main.modelo.Empleado;
import com.dam2.main.repository.DepartamentoRepository;
import com.dam2.main.repository.EmpleadoRepository;

@SpringBootApplication
public class PruebaEmpleadosSpringApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PruebaEmpleadosSpringApplication.class, args);
		
		var empleadoRepo = context.getBean(EmpleadoRepository.class);
		var deptoRepo = context.getBean(DepartamentoRepository.class);
		
		//listado de empleados:
		List<Empleado> emps = empleadoRepo.findAll();
		emps.stream().forEach(e -> System.out.println(e));
		
		emps = empleadoRepo.findByNombre("ANA");
		emps.stream().forEach(System.out::println);
		
		emps = empleadoRepo.findByNombreDepto("INFORMÁTICA");
		emps.stream().forEach(System.out::println);
		
		List<String> nombres = empleadoRepo.findNombreByIdDepto(1);
		nombres.stream().forEach(System.out::println);
	}

}
