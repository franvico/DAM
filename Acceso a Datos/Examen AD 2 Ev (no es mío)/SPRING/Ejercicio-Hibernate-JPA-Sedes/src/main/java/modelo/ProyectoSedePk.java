package modelo;

import java.util.Objects;

import jakarta.persistence.Column;

public class ProyectoSedePk {
	
	@Column(name = "id_proy")
	private int id_proy;
	
	@Column(name = "id_proy")
	private int id_sede;

	public ProyectoSedePk(int id_proy, int id_sede) {
		super();
		this.id_proy = id_proy;
		this.id_sede = id_sede;
	}
	
	public ProyectoSedePk() {
		
	}

	public int getId_proy() {
		return id_proy;
	}

	public void setId_proy(int id_proy) {
		this.id_proy = id_proy;
	}

	public int getId_sede() {
		return id_sede;
	}

	public void setId_sede(int id_sede) {
		this.id_sede = id_sede;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_proy, id_sede);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProyectoSedePk other = (ProyectoSedePk) obj;
		return id_proy == other.id_proy && id_sede == other.id_sede;
	}
	
	

}
