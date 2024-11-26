package ejer1;

import java.util.ArrayList;

public class Almacen {
	private ArrayList<Integer> almacenPrincipal;
	private ArrayList<Integer> pares;
	private ArrayList<Integer> impares;
	int capacidadAlmacen;

	public Almacen(int capacidad) {
		almacenPrincipal = new ArrayList<Integer>();		
		pares = new ArrayList<Integer>();
		impares = new ArrayList<Integer>();
		capacidadAlmacen = capacidad;
	}

	public synchronized void insertaAlmacenPrincipal(int n) {
		almacenPrincipal.add(n);
	}

	public synchronized int extraeAlmacenPrincipal() {
		int n = almacenPrincipal.get(0);
		almacenPrincipal.remove(0);
		return n;
	}

	public synchronized void insertaPares(int n) {
		pares.add(n);
	}

	public synchronized int extraePares() {
		int n = pares.get(0);
		almacenPrincipal.remove(0);
		return n;
	}

	public synchronized void insertaImpares(int n) {
		impares.add(n);
	}

	public synchronized int extraeImpares() {
		int n = impares.get(0);
		almacenPrincipal.remove(0);
		return n;
	}
	
	public ArrayList getAlmacenPrincipal() {
		return this.almacenPrincipal;
	}
	public ArrayList getPares() {
		return this.pares;
	}
	public ArrayList getImpares() {
		return this.impares;
	}
}
