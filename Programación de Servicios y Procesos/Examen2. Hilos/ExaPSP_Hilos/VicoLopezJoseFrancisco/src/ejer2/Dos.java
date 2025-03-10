package ejer2;

import java.util.LinkedList;
import java.util.Queue;

/*
* 4 puntos
*En este programa, se simula el funcionamiento de una empresa.
*Hay una solo supervisor.
*Existen 2 manager. 
*Algunos empleados trabajan para un manager y otros para el otro.
*Los dos manager son supervisionados por el mismo supervisor.
*Los dos manager no pueden reunirse a la vez con el supervisor.
*Los empleados, hacen informes de sus trabajos y se los entregan a los correspondientes manager.
*
*En el programa no se ha sincronizado nada. Si lo considera necesario, añade el código necesario para la sincronización.
*Además, escribe en este mismo comentario, qué has añadido y por qué.
*
*
*CAMBIOS REALIZADOS:
* - CAMBIO 1 : 	He sincronizado el método agregarInforme en Manager porque dos empleados no pueden escribir a la vez en la variable "informes"
* - CAMBIO 2 : 	He sincronizado la reunión del manager con el supervisor usando como mutex el objeto supervisor para que no se reúnan a la vez
* - CAMBIO 3 : 	He puesto a dormir al manager en caso de que no haya informes que enviar al supervisor
* - CAMBIO 4 : 	Los empleados despiertan a su manager cuando han enviado un informe para que en caso de que esté dormido porque no hay informes, se despierte
* 				- Nota sobre este cambio: podría añadir que el empleado sólo despierte al manager si no hay informes antes de enviar el suyo, simplemente para que no esté despertando constantemente
* 									al manager si ya está despierto. Dejo el programa como está porque en este caso considero que no molesta si el empleado trata de despertar al manager que ya está despierto.
* - CAMBIO 5 : 	He considerado que la operación de comprobar si informes está vacía tiene que ser atómica y sincronizada sobre manager porque los empleados están constantemente escribiendo en informes
* 				y podría darse inconsistencia de memoria si se comprueba si está vacío (podría irse a dormir el manager) y luego un empleado escribiera un informe
* - CAMBIO 6 :	He añadido una variable nombreMag para darle un nombre a cada manager y ver más clara la salida por consola.
* - NOTA IMPORTANTE : El programa nunca finalizará porque cuando los empleados hayan entregado todos los informes, los managers quedarán durmiendo. Considero que está bien que el programa se quede así
* 					  en vez de tratar de controlar si se ha enviado el último informe que no se duerman porque en un caso práctico si un empleado vuelve a mandar un informe los hilos manager deben
* 				      seguir en ejecución
*/

class Manager implements Runnable {
	private Queue<String> informes = new LinkedList<>();
	Object supervisor;
	String nombreMag;

	// CAMBIO 6 (opcional)
	public Manager(Object supervisor, String nombreMag) {
		this.supervisor = supervisor;
		this.nombreMag = nombreMag;
	}

	//CAMBIO 1
	public synchronized void agregarInforme(String informe, String empleado) {

		informes.add(informe);
		System.out.println(empleado + " entregó a su manager un informe: " + informe);

	}

	public void run() {
		while (true) {
			// CAMBIO 5
			synchronized (this) {
				if (!informes.isEmpty()) {
					// CAMBIO 2
					synchronized (supervisor) {
						String informe = informes.poll();
						System.out.println("El manager " + nombreMag + " se reune con el supervisor para hablar de: " + informe);
						try {
							Thread.sleep(2000); // Simula el tiempo para leer un informe
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
					}
					
				} else {
					System.out.println(" no hay informes sobre qué hablar para el manager " + nombreMag );
					// CAMBIO 3
					synchronized (this) {
						try {
							wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					try {
						Thread.sleep(20); // Simula el tiempo que espera antes de mirar si hay informes
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}

class Empleado implements Runnable {
	private String nombre;
	private Manager miManager;

	public Empleado(String nombre, Manager manager) {
		this.nombre = nombre;
		this.miManager = manager;
	}

	@Override
	public void run() {
		// Simula el envío de informes por parte de los empleados
		for (int i = 1; i <= 50; i++) {
			String informe = nombre + "-Informe-" + i;
			miManager.agregarInforme(informe, nombre);
			try {
				Thread.sleep(1000); // Simula el tiempo entre envío de informes
				// CAMBIO 4
				synchronized (miManager) {
					miManager.notifyAll();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class Dos {
	public static void main(String[] args) {
		// un supervisor
		Object supervisor = new Object();

		// dos managers
		Manager manager1 = new Manager(supervisor, "Pepe");
		Manager manager2 = new Manager(supervisor, "Juan");

		// varios empleados
		Thread empleado1 = new Thread(new Empleado("empleado1", manager1));
		Thread empleado2 = new Thread(new Empleado("empleado2", manager1));
		Thread empleado3 = new Thread(new Empleado("empleado3", manager2));
		Thread empleado4 = new Thread(new Empleado("empleado4", manager2));

		// lanzo los threads
		empleado1.start();
		empleado2.start();
		empleado3.start();
		empleado4.start();
		new Thread(manager1).start();
		new Thread(manager2).start();
	}
}
