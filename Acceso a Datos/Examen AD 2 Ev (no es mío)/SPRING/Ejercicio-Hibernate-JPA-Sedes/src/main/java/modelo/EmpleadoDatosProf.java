package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleado_datos_prof")
public class EmpleadoDatosProf {
	
	@EmbeddedId
	private EmpleadoDatosProfPK id;
	
	@Column(name = "categoria")
	private String categoria;
	
	@Column(name = "sueldo_bruto_anual")
	private double sueldo_bruto_anual;
	
	@ManyToOne
	@JoinColumn(name = "dni", insertable = false, updatable = false)
	Empleado empleado;

	public EmpleadoDatosProf(EmpleadoDatosProfPK id, String categoria, double sueldo_bruto_anual, Empleado empleado) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.sueldo_bruto_anual = sueldo_bruto_anual;
		this.empleado = empleado;
	}
	
	public EmpleadoDatosProf() {
		
	}

	public EmpleadoDatosProfPK getId() {
		return id;
	}

	public void setId(EmpleadoDatosProfPK id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getSueldo_bruto_anual() {
		return sueldo_bruto_anual;
	}

	public void setSueldo_bruto_anual(double sueldo_bruto_anual) {
		this.sueldo_bruto_anual = sueldo_bruto_anual;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@Override
	public String toString() {
		return "EmpleadoDatosProf [id=" + id + ", categoria=" + categoria + ", sueldo_bruto_anual=" + sueldo_bruto_anual
				+ ", empleado=" + empleado + "]";
	}
	
	
	
}
