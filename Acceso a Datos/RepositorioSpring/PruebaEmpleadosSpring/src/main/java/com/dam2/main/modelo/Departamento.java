package com.dam2.main.modelo;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;



@Entity
public class Departamento {
	
	int id;
	String nombre;
	
	List<Empleado> empleados;
	
	@Id							//para indicar que es clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //porque es AI
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
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "departamento")
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	

}
