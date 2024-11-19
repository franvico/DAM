package logica;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import ejercicioMatriculas.Asignatura;
import ejercicioMatriculas.Matricula;
import ejercicioMatriculas.Matriculas;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public class MainMatriculas {
	
	static List<Matricula> listaMatriculas = new LinkedList<>();
	
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws JAXBException {
		// TODO Auto-generated method stub
		
		Asignatura a1 = new Asignatura();
		a1.setNombre("mates");
		a1.setPrecio(500);
		
		Asignatura a2 = new Asignatura();
		a2.setNombre("fisica");
		a2.setPrecio(400);
		
		Matricula m1 = new Matricula();
		m1.setAlumno("pepe");
		m1.getListaAsignaturas().add(a1);
		m1.getListaAsignaturas().add(a2);
		
		Matricula m2 = new Matricula();
		m2.setAlumno("juan");
		m2.getListaAsignaturas().add(a1);
		
		boolean exit = false;
		while(!exit) {
			System.out.println("SELECCIONE UNA OPCIÓN:\n");
			System.out.println("1. Crear nueva matrícula");
			System.out.println("2. Comprobar alumnos matriculados");
			System.out.println("3. Ver asignatura más cara");
			System.out.println("0. Salir\n");
			int opcion = scan.nextInt();
			
			switch(opcion){
				case 1 : nuevaMatricula();
					break;
				case 2 : getAlumnosMatriculados();
					break;
				case 3 : getAsignaturaMasCara();
					break;
				case 0 : System.out.println("Adiós");
							exit = true;
					break;
				default : System.out.println("\nSeleccione una opción válida\n");
			}
			
			
			String alumno = scan.nextLine();	
		}
		
		
		
		Matriculas mads = new Matriculas();
		mads.getMatriculas().add(m1);
		mads.getMatriculas().add(m2);
		
		escribirMatriculas(mads, new File("matriculas.xml"));

	}
	
	private static void nuevaMatricula() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Nombre del alumno");
		String alumno = scan.nextLine();
		
		List<Asignatura> listaAsignaturas = new LinkedList<>();
		
		boolean exit = false;
		while(!exit) {
			System.out.println("SELECCIONE UNA OPCIÓN:\n");
			System.out.println("1. Nueva asignatura");
			System.out.println("0. Salir\n");
			int opcion = scan.nextInt();
			
			switch(opcion){
				case 1 : nuevaAsignatura(listaAsignaturas);
					break;
				case 0 : crearMatricula(alumno, listaAsignaturas); 
						System.out.println("Adiós");
				exit = true;
				default : System.out.println("\nSeleccione una opción válida\n");
			}
		}
		
	}
	
	private static void getAlumnosMatriculados() {
		System.out.println("alumnos matriculados");
	}
	
	private static void getAsignaturaMasCara(){
		System.out.println("asignatura mas cara");
	}
	
	private static void nuevaAsignatura(List<Asignatura> listaAsignaturas) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Introduzca el nombre de la asignatura");
		String nombre = scan.nextLine();
		System.out.println("Introduzca el precio de la asignatura");
		double precio = scan.nextDouble();
		
		Asignatura a = new Asignatura();
		a.setNombre(nombre);
		a.setPrecio(precio);
		
		listaAsignaturas.add(a);
	}
	
	public static void crearMatricula(String alumno, List<Asignatura> listaAsignaturas) {
		Matricula m = new Matricula();
		m.setAlumno(alumno);
		m.getListaAsignaturas().add((Asignatura) listaAsignaturas);
		
		listaMatriculas.add(m);
	}
	
	private static void escribirMatriculas(Matriculas mads, File fichero) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Matriculas.class);
		Marshaller marshall = jaxbContext.createMarshaller();
		
		marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshall.marshal(mads, fichero);
		
		
		
	}

}
