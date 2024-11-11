package cuentaBancaria;

import java.util.ArrayList;

public class GestorBancario{

	public static void main(String[] args) throws InterruptedException {
		
		CuentaBancaria cuenta = new CuentaBancaria(); // este es el candado para todos los hilos
		
		ArrayList<Thread> lt = new ArrayList<Thread>();
		
		Thread t1 = new Thread(new Cliente("Pepe", cuenta)); lt.add(t1);
		Thread t2 = new Thread(new Cliente("Juan", cuenta)); lt.add(t2);
		Thread t3 = new Thread(new Cliente("Isabel", cuenta)); lt.add(t3);
		Thread t4 = new Thread(new Cliente("Lucía", cuenta)); lt.add(t4);
		Thread t5 = new Thread(new Cliente("Héctor", cuenta)); lt.add(t5);
		
		for(Thread t : lt) {
			t.start();
		}
		for(Thread t : lt) {
			t.join();
		}
		
		// PREGUNTAS PARA STEFANO:
		// 1. Entiendo que en retirar e ingresar (en Cliente) hay operaciones atómicas que hay que sincronizar.
		// pero se podría hacer un código más limpio??
		// 2. Cómo hago para que cuando todos los hilos se paren porque no hay para sacar, se ingrese dinero
		// para que siga la ejecución
		
		System.out.println("Saldo final: " + cuenta.getCantidad());

	}

}
