package clases;

import java.util.Objects;

public class Piso {
	
	private int codigo;
	private String direccion;
	private double mensualidad;
	private String alquilado;
	private String nifEmpleado;
	
	public Piso(int codigo, String direccion, double mensualidad, String alquilado, String nifEmpleado) {
		super();
		this.codigo = codigo;
		this.direccion = direccion;
		this.mensualidad = mensualidad;
		this.alquilado = alquilado;
		this.nifEmpleado = nifEmpleado;
	}
	
	public Piso() {
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getMensualidad() {
		return mensualidad;
	}

	public void setMensualidad(double mensualidad) {
		this.mensualidad = mensualidad;
	}

	public String getAlquilado() {
		return alquilado;
	}

	public void setAlquilado(String alquilado) {
		this.alquilado = alquilado;
	}

	public String getNifEmpleado() {
		return nifEmpleado;
	}

	public void setNifEmpleado(String nifEmpleado) {
		this.nifEmpleado = nifEmpleado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piso other = (Piso) obj;
		return codigo == other.codigo;
	}

	@Override
	public String toString() {
		return "Piso [codigo=" + codigo + ", direccion=" + direccion + ", mensualidad=" + mensualidad + ", alquilado="
				+ alquilado + ", nifEmpleado=" + nifEmpleado + "]";
	}
	
	

}
