package ejercicioMatriculas;

import java.util.LinkedList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"alumno", "listaAsignaturas"})
public class Matricula {

	private String alumno;
	private List<Asignatura> listaAsignaturas = new LinkedList<>();
	
	public Matricula() {
		super();
	}

	public Matricula(String alumno, List<Asignatura> listaAsignaturas) {
		super();
		this.alumno = alumno;
		this.listaAsignaturas = listaAsignaturas;
	}

	public String getAlumno() {
		return alumno;
	}

	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}

	@XmlElementWrapper(name = "asignaturas")
	@XmlElement(name = "asignatura")
	public List<Asignatura> getListaAsignaturas() {
		return listaAsignaturas;
	}

	public void setListaAsignaturas(List<Asignatura> listaAsignaturas) {
		this.listaAsignaturas = listaAsignaturas;
	}
	
	
	
}
