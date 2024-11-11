package cuentaBancaria;

import java.util.Random;

public class Cliente implements Runnable{
	
	private String nombre;
	private CuentaBancaria cuenta;

	public Cliente() {
		super();
	}
	
	public Cliente(String nombre, CuentaBancaria cuenta) {
		this.nombre = nombre;
		this.cuenta = cuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void ingresarDinero(double cantidad) {
		
		synchronized (cuenta) {
			cuenta.setCantidad(cuenta.getCantidad() + cantidad);
			System.out.println("\n" + this.getNombre() + " ha ingresado: " + cantidad);
			System.out.println("Cuenta actualizada con: " + cuenta.getCantidad() + "\n");
			
			cuenta.notifyAll();
		}
		
		
	}
	
	public void retirarDinero(double cantidad) {
		synchronized (cuenta) {
			// si cuenta.cantidad < cantidad --> duerme
			while(cuenta.getCantidad() < cantidad) {
				try {
					System.out.println("Dinero insuficiente para " + this.getNombre() + " que quiere retirar: " + cantidad + "\n");
					cuenta.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
			cuenta.setCantidad(cuenta.getCantidad() - cantidad);		
			System.out.println("\n" + this.getNombre() + " ha retirado: " + cantidad);
			System.out.println("Cuenta actualizada con: " + cuenta.getCantidad() + "\n");
		}
	}
	

	public void run() {
		while(true) {
			double cantidad = new Random().nextDouble(100);
			if(tirarDado()) {
				ingresarDinero(cantidad);
			}else {
				retirarDinero(cantidad);
			}
		}
		
	}
	
	public boolean tirarDado() {		
		int num = new Random().nextInt(6) + 1;		
		return (num % 2 == 0) ? true : false;
	}
}
