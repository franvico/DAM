package clases;

import java.util.Objects;

public class Empleado {
	
	private String nif;
	private String nombre;
	private double sueldo_base;
	
	public Empleado(String nif, String nombre, double sueldo_base) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.sueldo_base = sueldo_base;
	}
	
	public Empleado() {
		
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSueldo_base() {
		return sueldo_base;
	}

	public void setSueldo_base(double sueldo_base) {
		this.sueldo_base = sueldo_base;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nif);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(nif, other.nif);
	}

	@Override
	public String toString() {
		return "Empleado [nif=" + nif + ", nombre=" + nombre + ", sueldo_base=" + sueldo_base + "]";
	}
	
	

}
