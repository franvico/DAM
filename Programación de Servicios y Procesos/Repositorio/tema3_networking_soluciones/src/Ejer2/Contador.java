package Ejer2;

public class Contador {
	private int numero;
	private int solicitudes;

	public synchronized int getNumero() {
		
		solicitudes++;
		if(solicitudes==4)
		{
			numero++;
			solicitudes=1;
		}
		return numero;
	}

	public synchronized void setNumero(int numero) {
		this.numero = numero;
	}

	public Contador() {
		this.numero = 0;
		this.solicitudes=0;
	}

}
