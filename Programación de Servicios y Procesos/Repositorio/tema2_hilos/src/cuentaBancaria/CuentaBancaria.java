package cuentaBancaria;

public class CuentaBancaria {
	
	private double cantidad;

	public CuentaBancaria() {
		this.cantidad = 100.00;
	}

	public synchronized double getCantidad() {
		return cantidad;
	}

	public synchronized void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	public void aprobarPrestamo() {
		cantidad =+ 100;
		System.out.println("\nSe ha aprobado un préstamo de 100 euros a esta cuenta");
		notifyAll();
	}

}
