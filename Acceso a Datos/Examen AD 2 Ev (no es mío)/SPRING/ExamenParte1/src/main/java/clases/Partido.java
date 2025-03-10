package clases;

import java.util.Date;

public class Partido {
	
	private int id;
	private Date fecha;
	private String lugar;
	
	public Partido(int id, Date fecha, String lugar) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.lugar = lugar;
	}
	
	public Partido() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	@Override
	public String toString() {
		return "Partido [id=" + id + ", fecha=" + fecha + ", lugar=" + lugar + "]";
	}
	
	

}
