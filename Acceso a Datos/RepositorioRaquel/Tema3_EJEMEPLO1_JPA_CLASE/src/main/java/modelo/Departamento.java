package modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
// import jakarta.persistence.Table;

@Entity // para decirle que es una entidad de mi BBDD
// @Table(name="departamento") // si no le especifico nombre lo toma como que es igual al de la BBDD, si quiero que se llame de otra forma tengo que decirle a qué tabla se corresponde mediante la etiqueta @Table
public class Departamento {

	@Id // id es clave primaria así que uso la etiqueta @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que sea autoincremental
	private int id;
	
	@Column(name="nombre") // nombreDepto no coincide con la columna nombre de la BBDD, así que uso la etiqueta @Column para referenciarla al nombre real de la columna en BBDD
	private String nombreDepto;
	
	@Transient // notas no es un campo de la BBDD, así que añado la etiqueta @Transient para que lo ignore
	private String notas;
	
	@OneToMany(mappedBy="departamento") // la propiedad empleados refleja que un departamento tiene muchos empleados asociados. Define la relación entre tablas
	private List<Empleado> empleados;
	
	
	public Departamento(int id, String nombreDepto) {
		super();
		this.id = id;
		this.nombreDepto = nombreDepto;
	}


	public Departamento() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombreDepto() {
		return nombreDepto;
	}


	public void setNombreDepto(String nombreDepto) {
		this.nombreDepto = nombreDepto;
	}


	public String getNotas() {
		return notas;
	}


	public void setNotas(String notas) {
		this.notas = notas;
	}


	public List<Empleado> getEmpleados() {
		return empleados;
	}


	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	
	
	
}
