package spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.service.ProyectoService;

@SpringBootApplication
public class EjercicioSpringSedesApplication {

	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(EjercicioSpringSedesApplication.class, args);
		
		ProyectoService ps = context.getBean(ProyectoService.class);
		
		ps.añadirProyecto(1, 1);
		
		ps.mostrarSedeConMasProyectos();
		
		ps.mostrarNombreDepartamentoConMasSueldo();
		
	}

}
