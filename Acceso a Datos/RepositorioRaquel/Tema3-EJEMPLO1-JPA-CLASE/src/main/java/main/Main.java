package main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Departamento;
import modelo.Empleado;

public class Main {

	public static void main(String[] args) {
		
		addDepartamento();
		//addEmpleado();
		//listaEmpleados(1);

	}
	
	private static void addDepartamento() {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		
		Departamento dept = new Departamento();
		dept.setNombreDepto("INFORMÁTICA");
		dept.setNotas("Prueba de notas transitorias");
		
		sesion.persist(dept);
		
		tx.commit();
		sesion.close();
	}
	
	private static void addEmpleado() {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		
		Empleado emp = new Empleado();
		emp.setNombre("Juanito");
		//Departamento d = sesion.get(Departamento.class, 1);
		Departamento d = new Departamento();
		d.setId(1);
		emp.setDepartamento(d);
		
		sesion.persist(emp);
		tx.commit();
		sesion.close();
		
	}
	
	private static void listaEmpleados(int id) {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		
		Departamento d = sesion.get(Departamento.class, id);
		
		for(Empleado e : d.getEmpleados())
			System.out.println(e.getNombre());
		
		tx.commit();
		sesion.clear();
	}

}
