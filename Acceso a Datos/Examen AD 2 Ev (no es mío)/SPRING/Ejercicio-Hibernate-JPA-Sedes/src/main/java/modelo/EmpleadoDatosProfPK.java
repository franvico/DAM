package modelo;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EmpleadoDatosProfPK {
	
	@Column(name = "dni")
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

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpleadoDatosProfPK other = (EmpleadoDatosProfPK) obj;
		return Objects.equals(dni, other.dni);
	}
	
	

}
