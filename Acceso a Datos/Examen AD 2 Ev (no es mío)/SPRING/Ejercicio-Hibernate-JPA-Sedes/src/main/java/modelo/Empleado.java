package modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleado")
public class Empleado {
	
	@Id
	private String dni;
	
	@Column(name = "nom_emp")
	private String nom_emp;
	
	@ManyToOne
	@JoinColumn(name = "id_depto", insertable = false, updatable = false)
	Departamento departamento;
	
	@OneToMany(mappedBy = "empleado", fetch = FetchType.LAZY)
	List<EmpleadoDatosProf> datosEmpleados;

	public Empleado(String dni, String nom_emp, Departamento departamento, List<EmpleadoDatosProf> datosEmpleados) {
		super();
		this.dni = dni;
		this.nom_emp = nom_emp;
		this.departamento = departamento;
		this.datosEmpleados = datosEmpleados;
	}

	public Empleado() {
		
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNom_emp() {
		return nom_emp;
	}

	public void setNom_emp(String nom_emp) {
		this.nom_emp = nom_emp;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Empleado [dni=" + dni + ", nom_emp=" + nom_emp + ", departamento=" + departamento + "]";
	}
	
	
	
}
