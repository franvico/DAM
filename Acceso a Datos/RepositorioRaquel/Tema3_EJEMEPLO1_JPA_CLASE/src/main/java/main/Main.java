package main;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import modelo.Departamento;
import modelo.Empleado;

public class Main {

	// Usar el hibernate útil para las sesiones (copiar)
	
	public static void main(String[] args) {

		// me dará error de primeras, porque el hibernateconfig se le añade configuración
		// tengo que decirle cuáles son mis entidades por defecto, de esta forma:
        // <mapping class="modelo.Departamento"/>
        // <mapping class="modelo.Empleado"/>
		
		// si sale este error:  Could not locate cfg.xml resource [hibernate.cfg.xml]
		// SOLUCIÓN 1 : mover el hibernate.cfg.xml a la carpeta resources
		// SOLUCIÓN 2 : añadir new File("hibernate.cfg.xml") a la línea sessionFactory = new Configuration().configure(new File("hibernate.cfg.xml")).buildSessionFactory(); en el hibernate.cfg.xml
		addDepartamento();
		
		addEmpleado();
		
		listaEmpleados(2);
		
		
		
	}
	
	private static void addDepartamento() {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		
		Departamento dept = new Departamento();
		dept.setNombreDepto("INFORMÁTICA");
		dept.setNotas("Prueba notas transitorias");
		
		sesion.persist(dept);
		
		tx.commit();
		sesion.close();
	}
	
	private static void addEmpleado() {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		
		Empleado emp = new Empleado();
		emp.setNombre("Juan");
		
		// OPCIÓN 1
		// pillo el departamento de la BBDD
		// Departamento d = sesion.get(Departamento.class, 2);
		
		// OPCIÓN 2
		// si sé cuál es el id del dept al que quiero asociarlo, creo el dept sin llamar a la BBDD (menos carga)
		Departamento d = new Departamento();		
		d.setId(2);
		
		emp.setDepartamento(d);
		
		sesion.persist(emp);
		
		tx.commit();
		sesion.close();
	}
	
	private static void listaEmpleados(int id) {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		
		Departamento d = sesion.get(Departamento.class, id);
		
		for(Empleado e : d.getEmpleados()) {
			System.out.println(e.getNombre());
		}
		
		tx.commit();
		sesion.close();
		
	}

}
