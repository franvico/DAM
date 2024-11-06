package ejercicio1_ProcessBuilder;

import java.util.Scanner;

public class Ejercicio1_ProcessBuilder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		System.out.println("HOLA");
		
		System.out.println(suma(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
		
//		Scanner scan = new Scanner(System.in);
//		
//		int x, y;
//		
//		do{
//			System.out.println("Escribe el primer número, debe ser positivo");
//			x = scan.nextInt();
//		}while(x < 0);
//		
//		do{
//			System.out.println("Escribe el segundo número, debe ser mayor al primero");
//			y = scan.nextInt();
//		}while(y <= x);
//		
//		System.out.println("La suma de los número entre esos dos valores es: " + suma(x,y));
	}
	
	static int suma(int x, int y) {
		
		int suma = 0;
		
		for(int i = x; i <= y; i++) {
			suma+= i; 
		}
		
		return suma;
	}

}
