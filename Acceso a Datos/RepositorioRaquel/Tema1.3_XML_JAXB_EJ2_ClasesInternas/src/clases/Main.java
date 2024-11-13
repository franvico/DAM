package clases;

public class Main {

	public static void main(String[] args) {
		
		Alumno.IES = "Rosa Chacel";
		
		Alumno a = new Alumno();
		
		a.nombre = "Pepito";
		
		Alumno.Matricula mat = a.new Matricula();
		
		mat.id = 1;
		mat.precio = 45;
		mat.nombre = "dam";
		
		mat.mostrarDatos();
		
		Alumno.Normativa norm = new Alumno.Normativa();
		
		norm.descripcion = "No fumar";
		
		norm.mostrarNormativa();
		
		Alumno.Normativa norm2 = new Alumno.Normativa();
		
		norm2.descripcion = "No beber";
		
		norm2.mostrarNormativa();
		
		norm.mostrarNormativa();
		
		

	}

}
