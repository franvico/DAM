package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name="empleado_datos_prof")
public class EmpleadoDatosProf {
	
	@EmbeddedId
    private EmpleadoDatosProfPK id;
	
	@MapsId("dni") // Relaciona con el campo de la clave primaria
    @ManyToOne
    @JoinColumn(name = "dni")
    private Empleado empleado;
	
	@Column(name="categoria")
	private String categoria;
	
	@Column(name="sueldo_bruto_anual")
	private double sueldo_bruto_anual;

	public EmpleadoDatosProf(EmpleadoDatosProfPK id, Empleado empleado, String categoria, double sueldo_bruto_anual) {
		super();
		this.id = id;
		this.empleado = empleado;
		this.categoria = categoria;
		this.sueldo_bruto_anual = sueldo_bruto_anual;
	}
	
	public EmpleadoDatosProf() {
		
	}

	public EmpleadoDatosProfPK getId() {
		return id;
	}

	public void setId(EmpleadoDatosProfPK id) {
		this.id = id;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
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

	@Override
	public String toString() {
		return "EmpleadoDatosProf [id=" + id + ", empleado=" + empleado + ", categoria=" + categoria
				+ ", sueldo_bruto_anual=" + sueldo_bruto_anual + "]";
	}
	
	

}
