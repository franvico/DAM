package main;

import java.text.ParseException;

import org.hibernate.Session;

public class MainPruebas {

	public static void main(String[] args) throws ParseException {
		
		//pruebaConfiguracion();
		//AccesoDatos.insertProyecto();
		//AccesoDatos.insertEmpleado();
		//AccesoDatos.asociarProyectoSede(1,3);
		//AccesoDatos.eliminarSede(1);
		AccesoDatos.masProyectos();
		

	}
	
	private static void pruebaConfiguracion() {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		
		sesion.close();
	}

}
