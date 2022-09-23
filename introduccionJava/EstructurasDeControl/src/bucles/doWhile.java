package bucles;

import java.util.Scanner;

public class doWhile {
	/*
	 * Es muy similar a bucle while. Pero la difrencia es que ejecuta primero y
	 * después hace un condición para comprobar. Tenemos dos metódos: Método de
	 * comparar1() que es el bucle do while Método de comparar2() que es el bucle
	 * while
	 * 
	 */

	public static void main(String[] args) {
		compararConWhileYdoWhile2();
	}
	


	public static void menu() {
		Scanner in = new Scanner(System.in);
		int opcion;
		do {
			System.out.println("1-opcion 1\n2-opcion 2\n3-opcion 3\n0-Salir");
			opcion = in.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Has seleccionado opcion 1");
				break;
			case 2:
				System.out.println("Has seleccionado opcion 2");
				break;
			case 3:
				System.out.println("Has seleccionado opcion 3");
				break;
			default:
				System.out.println("Hasta pronto!!");
			}

		} while (opcion != 0);
	}

	public static void comparar1() {
		int contador = 2;
		System.out.println("---Estamos comparando un bucle do while---");
		do {
			System.out.println(contador);
			// Esta una opcion de descrementar
			// contador = contador - 1;
			// Pero esta es otra opcion y es más corto
			contador--;

		} while (contador > 2);
		System.out.println("Observamos que do while ha ejecutado primero y después la condición\n--------");
	}

	public static void compararConWhile() {
		int contador = 0;
		System.out.println("---Estamos comparando con bucle while---");
		while (contador < 2) {
			System.out.println(contador);
			// Esta una opcion de descrementar
			// contador = contador - 1;
			// Pero esta es otra opcion y es más corto
			contador++;
		}

	}

	public static void compararConDoWhile() {
		int contador = 2;
		System.out.println("---Estamos comparando con bucle do while");
		do {
			System.out.println(contador);
			contador++;

		} while (contador < 2);
	}
	
	public static void compararConWhileYdoWhile() {
		int x= 0;
		System.out.println("--- DO WHILE ---");
		do {
		  System.out.print(x);
		  x++;
		} while (x<10);
		System.out.println("\n--- WHILE ---");
		int y= 0;
		while (y<10){
		  System.out.print(y);
		  y++;
		}
		
		System.out.println("\nEl resultado es: X = "+x+" Y ="+y);
	}
	
	public static void compararConWhileYdoWhile2() {
		int x = 0;
		System.out.println("---WHILE---");
		do {
			System.out.print(x);
			x++;
		} while (x < 10);
		System.out.println("\n---DO WHILE---");
		/* Código Dos */
		int y = 0;
		while (y < 10) {
			System.out.print(y);
			y++;
		}
		
		
		System.out.println("\nX ="+x+" Y="+y);
		

	}

	// Es otra opción como "do while" porque no todos los lenguajes tienen esta
	// estructura
	public static void opcion2() {
		int contador = 2;
		System.out.println(contador);
		contador++;
		while (contador > 2) {
			System.out.println(contador);
			contador++;
		}
	}

}
