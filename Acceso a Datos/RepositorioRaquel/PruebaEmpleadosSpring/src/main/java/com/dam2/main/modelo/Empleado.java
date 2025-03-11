package com.dam2.main.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Empleado {
	
	@Id							//para indicar que es clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //porque es AI
	int id;
	
	String nombre;
	
	//explorar el updatable: significa que esa columna (el objeto
	//departamento del empleado) no se puede 
	//modificar una vez insertado el objeto
	@ManyToOne
    @JoinColumn(name = "id_depto", nullable = false, updatable = false)
	Departamento departamento;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	@Override
	public String toString() {
		return "Empleado [nombre=" + nombre + "]";
	}
	
	
	

}
