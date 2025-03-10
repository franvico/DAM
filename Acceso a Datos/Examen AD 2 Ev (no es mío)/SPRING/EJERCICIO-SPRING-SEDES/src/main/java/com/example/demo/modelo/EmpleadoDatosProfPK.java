package com.example.demo.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class EmpleadoDatosProfPK implements Serializable{

	private String dni;

	public EmpleadoDatosProfPK(String dni) {
		super();
		this.dni = dni;
	}
	
	public EmpleadoDatosProfPK() {
		
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
	
}
