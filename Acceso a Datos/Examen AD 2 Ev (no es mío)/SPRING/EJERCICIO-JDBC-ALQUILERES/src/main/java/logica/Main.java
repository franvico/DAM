package logica;

import clases.Empleado;
import clases.Piso;
import funciones.Funciones;

public class Main {

	public static void main(String[] args) {
		
		Funciones f = new Funciones();
		
		Piso p = new Piso();
		p.setCodigo(1);
		p.setDireccion("Madrid");
		p.setMensualidad(500);
		p.setAlquilado("no");
		p.setNifEmpleado("1");
		
		Empleado e = new Empleado();
		e.setNif("1");
		e.setNombre("Juan");
		e.setSueldo_base(1000);
		
		f.insertarEmpleado(e);
		f.insertarEmpleado_v2(new Empleado("2", "Pepe", 1500));

		f.insertarPiso(p);
		f.insertarPiso_v2(new Piso(2, "Barcelona", 400, "no", "1"));
		
		f.modificarMensualidad(new Piso(1, null, 600, null, null));
		
		f.modificarEmpleadoDelPiso(new Piso(2, null, 0, null, "2"));
		
		f.alquilarPiso(new Piso(2, null, 0, "si", null));
		
		f.mostrarNombreEmpleado(1);
		f.mostrarNombreEmpleado_v2(2);
		
		f.mostrarSueldoEmpleado("1");
		
		f.mostrarEmpleadoConMasPisos();
		
	}

}
