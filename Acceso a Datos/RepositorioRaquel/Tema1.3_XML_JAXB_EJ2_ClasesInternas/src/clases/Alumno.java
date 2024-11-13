package clases;

public class Alumno {
	
	String nombre;
	static String IES;
	
	class Matricula{
		
		//LA VENTAJA DE NO HACERLA COMO CLASE APARTE
		//ES QUE AQUÍ ES OBLIGATORIO QUE EXISTA UN ALUMNO
		//PARA PODER CREAR SU OBJETO MATRÍCULA
		int id;
		int precio;
		String nombre;
		void mostrarDatos() {
			
			System.out.println(id + " " + precio + " " + nombre);
		}
		 
	}
	
	static class Normativa{
		
		//LA VENTAJA RESPECTO A HACERLA APARTE ES
		//QUE AQUÍ TIENE ACCESO DIRECTO A TODO EL
		//CONTEXTO ESTÁTICO DE ALUMNO
		String descripcion;
		
		void mostrarNormativa() {
			
			System.out.println(IES + " " + descripcion);
		}
	}

}
