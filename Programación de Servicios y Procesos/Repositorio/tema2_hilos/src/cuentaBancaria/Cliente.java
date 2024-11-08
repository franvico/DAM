package cuentaBancaria;

public class Cliente {
	
	private double cantidad;
	private String nombre;

	public Cliente() {
		super();
	}
	
	public Cliente(String nombre) {
		this.nombre = nombre;
		this.cantidad = 0;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void ingresarDinero(CuentaBancaria cuenta, double cantidad) {
		cuenta.setCantidad(cuenta.getCantidad() + cantidad);
		this.setCantidad(this.getCantidad() - cantidad);
	}
	
	public void sacarDinero(CuentaBancaria cuenta, double cantidad) {
		cuenta.setCantidad(cuenta.getCantidad() - cantidad);
		this.setCantidad(this.getCantidad() + cantidad);
	}	
	

	
	
	

}
