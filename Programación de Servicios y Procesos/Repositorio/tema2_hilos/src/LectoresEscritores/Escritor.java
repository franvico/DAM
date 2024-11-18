package LectoresEscritores;

public class Escritor implements Runnable{
	
	private String nombre;
	private Libro libro;
	private Object boli; // objeto usado para wait() y notifyAll()
	
	public Escritor(String nombre, Libro libro, Object boli) {
		this.nombre = nombre;
		this.libro = libro;
		this.boli = boli;
	}
	
	public void run() {
				
		// dormir a otros Escritores?? NO PUEDO, CADA UNO SE DUERME A SÍ MISMO
		// Si ya hay un escritor trabajando, me duermo
		// hasta que acaben los lectores que hay leyendo, me duermo. Pero bloqueo a los lectores nuevos
		
		libro.añadirEscritor(nombre);
		
		
		while(libro.getNumLectores() > 0) { // compruebo que los lectores actuales han terminado y si no duermo
			synchronized (boli) {
				try {
					System.out.println("El escritor " + nombre + " está a la espera.");
					boli.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}			
		}

		escribir();
		libro.restarEscritor();
		if(!despertarEscritores()) {
			despertarLectores();
		}	
		
	}

	public String getNombre() {
		return nombre;
	}

	public void escribir() {
		libro.setContenido("\n\tTexto del escritor: " + nombre, nombre);
	}

	public void despertarLectores() {
		synchronized (libro.getGafas()) {
			System.out.println("\nDESPERTANDO A LECTORES DESDE ESCRITORES\n");
			libro.getGafas().notifyAll();	
		}
	}

	public boolean despertarEscritores() {
		if(libro.getNumEscritores() > 0) {
			synchronized (boli) {
				System.out.println("\nDESPERTANDO A ESCRITORES DESDE ESCRITORES\n");
				boli.notifyAll();	
			}			
			return true;
		}
		return false;
	}

}
