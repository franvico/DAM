package examRA1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Scanner;

public class MyManagerExam {

	public static void main(String[] args) throws IOException {
		String op = "";
		Scanner scan = new Scanner(System.in);

		// inserir tu nombre y apellidos en la siguiente variable
		String autor = "Jose Francisco Vico Lopez";

		// 3 colecciones de datos
		int[][] datos = { { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
				{ 2, 2, 424, 234, 42, 423, 423, 4234, 234, 234, 234, 234, 234, 234, 234, 234, 234, 234, 23, 423, 423,
						423, 423, 423, 23, 423, 4, 234, 234, 234, 23, 423, 42, 35, 23, 54, 6543, 6, 576, 456, 34, 6534,
						5324, 67, 454, 754, 73, 453, 453, 476, 45, 745, 6 },
				{ 11, 22, 33, 44, 55, 66, 77, 88, 99, 111, 222, 333, 444, 555, 666, 777, 888, 999 } };
		boolean sigue = true;
		while (sigue) {
			System.out.println("Bienvenido a MyManager de " + autor);
			System.out.println("Indica la operacion (escribe \"menu\" para ver el menu)");
			System.out.print("MyManager>");
			op = scan.next();
			if (op.equals("menu")) {
				System.out.println("operaciones permitidas:");
				System.out.println("menu  -> abre este menu");
				System.out.println("input   -> recibe datos y crea MyProcessors");
				System.out.println("list  -> indica la lista de los MyProcessors activos");
				System.out.println("reset  -> elimina todos los MyWriter activos");
				System.out.println("connect -> lee lo que está escribiendo el MyProcessor con un determinado identificador");
				System.out.println("exit -> sale del programa");

			}

			if (op.equals("input")) {
				
				/*
				 * implementa la funcionalidad create: Esta funcion solicita al usuario un
				 * numero para saber con cuál coleccion de datos va a trabajar (Las 3
				 * colecciones de datos disponibles estan en la variable datos) En base a
				 * cuantos numeros hay en la colecion seleccionada , el programa crea
				 * MyProcessors. Habrá un MyProcessor por cada 5 numeros que se tienen que
				 * procesar. Por ejemplo, si el usuario ha elegido la primera coleccion, habrá 2
				 * MyProcessors. My manager luego repartirá los numeros por procesar entre los
				 * MyProcessors. Nota: si se vuelve a ejecutar la operación input, los
				 * MyProcessor existente deberán desaparecer y se crearán nuevos.
				 */
				
				System.out.println("Elige el conjunto de datos con el que trabajar: 1, 2 o 3");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
				String num = br.readLine();
				int conjunto[] = datos[Integer.parseInt(num)-1];
				
				int procesos = conjunto.length / 5;
				
				String conjuntoString = "";
				for(int i = 0; i < conjunto.length; i++) {
					conjuntoString += conjunto[i] + ";";
				}
				
				int start = 0;
				int end = 4;
				for(int i = 1; i <= procesos; i++, start+=5, end+=5) {
					MyManagerExam.lanzarMyProcessor(conjuntoString.split(";"));					
				}
				
				
			}

			if (op.equals("list")) {
				/*
				 * implementa la funcionalidad list: Esta funcionalidad escribe una lista de los
				 * MyProcessor que se están actualmente ejecutando
				 */
			}

			if (op.equals("reset")) {
				/*
				 * implementa la funcionalidad reset: esta funcionalidad devuelve el programa al
				 * mismo estado en el que estaba al princio de su ejecución es decir que no
				 * deberá haber ningún MyProcessor ejecutandose
				 */
			}

			if (op.equals("connect")) {
				/*
				 * implementa la funcionalidad connect: Esta funcionalidad solicita al usuario
				 * un número de identificador e imprime en pantalla todo lo que ha escrito el
				 * MyProcessor que tiene dicho identificador
				 */
			}
			if (op.equals("exit")) {
				/*
				 * implementa la funcionalidad exit: limpia los recursos y sale del programa
				 */
				sigue = false;
			}
		}
		System.out.println("Gracias por haber usado MyManager de " + autor);
	}
	
	public static void lanzarMyProcessor(String[] subConjunto) {
		
			// Aqui lanzar el proceso con los subconjuntos
			
			System.out.println(subConjunto + " ");
		
	}

}
