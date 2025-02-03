package main;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import consola.Leer;
import modelo.Proyecto;
import modelo.ProyectoSede;
import modelo.ProyectoSedeId;
import modelo.Sede;
import modelo.Departamento;
import modelo.Empleado;
import modelo.EmpleadoDatosProf;

public class AccesoDatos {
	
	public static void insertProyecto() throws ParseException {
		
		System.out.println("Nombre proyecto:");
		String nombre = Leer.cadena();
		SimpleDateFormat parserFecha = new SimpleDateFormat("dd-mm-yyyy"); 
        Date fInicio = parserFecha.parse("01-01-2026"); 
        Date fFin = parserFecha.parse("31-12-2026"); 
        
        Proyecto p = new Proyecto();
        p.setNomProy(nombre);
        p.setFInicio(fInicio);
        p.setFFin(fFin);
        
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        sesion.persist(p);
        
        System.out.println("En cuántas sedes se va a trabajar?");
        int num = Leer.entero();
        for(int i=0;i<num;i++) {
        	System.out.println("Id de sede:");
        	int idSede = Leer.entero();
        	ProyectoSedeId psId = new ProyectoSedeId();
        	psId.setIdProy(p.getIdProy());
        	psId.setIdSede(idSede);
        	ProyectoSede ps = new ProyectoSede();
        	ps.setId(psId);
        	ps.setFInicio(fInicio);
        	ps.setFFin(fFin);
        	sesion.persist(ps);
        	
        }
        
        tx.commit();
        sesion.close();
        
    
	}
	
	public static void incorporarDatosProfesionales() {
		
		System.out.println("Dni empleado:");
		String dni = Leer.cadena();
		System.out.println("Categoría:");
		String cat = Leer.cadena();
		double sueldo = Leer.decimal();
		BigDecimal sueldoBruto = new BigDecimal(sueldo);
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		
		Empleado emp = sesion.get(Empleado.class, dni);
		
		if(emp.getEmpleadoDatosProf()==null) {
			System.out.println("Se insertarán sus datos profesionales");
			EmpleadoDatosProf datosProf = new EmpleadoDatosProf(emp,cat,sueldoBruto);
//			datosProf.setCategoria(cat);
//			datosProf.setSueldoBrutoAnual(sueldoBruto);
//			datosProf.setEmpleado(emp);
			//datosProf.setDni(emp.getDni());
			sesion.persist(datosProf);
			
			
		}else {
			System.out.println("Se modificarán sus datos profesionales");
			emp.getEmpleadoDatosProf().setCategoria(cat);
			emp.getEmpleadoDatosProf().setSueldoBrutoAnual(sueldoBruto);
		}
		
		tx.commit();
		sesion.close();
		
	}
	
	//??????? REVISAR PROBAR
	public static void insertEmpleado() {

		Empleado emp = new Empleado();

		System.out.print("Dni del empleado: ");
		String dni = Leer.cadena();
		emp.setDni(dni);
		System.out.print("Nombre del empleado: ");
		String nomEmp = Leer.cadena();
		emp.setNomEmp(nomEmp);
		System.out.print("Id de su departamento: ");
		int idDep = Leer.entero();

		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		try {

			Departamento dep = sesion.get(Departamento.class, idDep);
			
			//SI TENEMOS LA SEGURIDAD DE QUE EL DEPTO EXISTE
			//CON ESTO SERÍA SUFICIENTE PARA CREAR EL
			//DEPARTAMENTO QUE VAMOS A SETEAR EL EMPLEADO
			// Departamento dep2 = new Departamento();
			// dep2.setIdDepto(idDep);
			
			if (dep == null) {
				Departamento depNuevo = new Departamento();
				depNuevo.setIdDepto(idDep);
				System.err.println("No existe ese departamento, introduce sus datos: ");
				System.out.print("Nombre del departamento: ");
				String nomDep = Leer.cadena();
				depNuevo.setNomDepto(nomDep);

				System.out.print("Id sede: ");
				int idSede = Leer.entero();
				Sede sede = sesion.get(Sede.class, idSede);
				Sede sedeNueva = null;
				if (sede == null) {
					sedeNueva = new Sede();
					sedeNueva.setIdSede(idSede);
					System.err.println("No existe esa sede, introduce sus datos: ");
					System.out.print("Nombre de la sede: ");
					String nomSede = Leer.cadena();
					sedeNueva.setNomSede(nomSede);

					sesion.persist(sedeNueva); // Guardamos primero la Sede
					depNuevo.setSede(sedeNueva);
					emp.setDepartamento(depNuevo);
				}else {

					depNuevo.setSede(sede);
					sesion.persist(depNuevo); // Ahora guardamos el Departamento
				}
			}else {

				emp.setDepartamento(dep);
				sesion.persist(emp); // Finalmente guardamos el Empleado
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			sesion.close();
		}
	}
	
	public static void asociarProyectoSede(int idProyecto, int idSede) {
		
		ProyectoSedeId psId = new ProyectoSedeId();
		psId.setIdProy(idProyecto);
		psId.setIdSede(idSede);
		ProyectoSede ps = new ProyectoSede();
		ps.setId(psId);
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		
//		Proyecto p = sesion.get(Proyecto.class, idProyecto);
//		Sede s = sesion.get(Sede.class, idSede);
//		ps.setProyecto(p);
//		ps.setSede(s);
		ps.setFFin(null);
		ps.setFInicio(new Date());
		
		sesion.persist(ps);
		
		tx.commit();
		sesion.close();
		
	}
	
	public static void eliminarSede(int idSede) {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		
		Sede sede = sesion.get(Sede.class, idSede);
		
		sesion.remove(sede);
		
		tx.commit();
		sesion.close();
		
	}
	
	public static Sede masProyectos() {
		
		return null;
	}

}
