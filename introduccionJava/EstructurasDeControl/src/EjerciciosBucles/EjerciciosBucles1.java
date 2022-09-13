package EjerciciosBucles;

import java.util.Scanner;

public class EjerciciosBucles1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		primerEjercicio();

	}

	// EJERCICIOS DE BUCLE FOR
	public static void paresEimpares() {
		System.out.println("---Numeros pares---");
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				System.out.println(i);
			}
		}
		System.out.println("---Numeros impares---");
		for (int i = 0; i < 10; i++) {
			if (i % 2 != 0) {
				System.out.println(i);
			}
		}

	}

	public static void contarNumerosPrimos() {
		System.out.println("---Numeros primos---");
		for (int i = 0; i < 100; i++) {
			if (esPrimo(i)) {
				System.out.println(i);
			}
		}
	}

	public static boolean esPrimo(int numero) {
		// El 0, 1 y 4 no son primos
		if (numero == 0 || numero == 1 || numero == 4) {
			return false;
		}
		for (int x = 2; x < numero / 2; x++) {
			// Si es divisible por cualquiera de estos números, no
			// es primo
			if (numero % x == 0)
				return false;
		}
		// Si no se pudo dividir por ninguno de los de arriba, sí es primo
		return true;
	}

	public static void ascii(int enMayusculas, int enMinusculas) {
		System.out.println("----Abecedario en mayusculas----");
		for (int i = enMayusculas; i <= 90; i++) {
			String convertirChar = Character.toString(i);
			System.out.println(i + "=> " + convertirChar);
		}
		System.out.println("----Abecedario en minusculas----");
		for (int i = enMinusculas; i <= 122; i++) {
			String convertirChar = Character.toString(i);
			System.out.println(i + "=> " + convertirChar);
		}
	}

	public static void prueba() {
		Scanner sc = new Scanner(System.in);

		int a = (int) ((Math.random() * 100) + 1); // Número aleatorio de 1 a 100.
		int b; // Número introducido por el usuario.
		int i; // Contador para bucle for.

		System.out.println("El juego consiste en averiguar un número secreto de 100 posibles.");

		System.out.print("Tienes 5 intentos. Escribe un número entre 1 y 100: ");
		b = sc.nextInt();

		for (i = 0; i <= 4; i++) {

			// Primero se evalúa si se ha acertado.

			if (a == b) {
				System.out.println("¡Has acertado!");
				break;
			}

			// Luego da la partida por perdida si ya has gastado 5 intentos.

			else if (i == 4) {
				System.out.println("Lo siento: ¡has perdido!. El número era el: " + a);
				break;
			}

			// Si no es ninguno de los casos anteriores, comparamos números.

			else if (a > b)
				System.out.println("El número secreto es MAYOR que " + b);
			else if (a < b)
				System.out.println("El número secreto es MENOR que " + b);

			// Por último un bucle if que sirve para dar una pista y avisarte de la última
			// oportunidad.

			if (i <= 1) {
				System.out.print("Otro intento: ");
				b = sc.nextInt();
			} else if (i == 2) {
				int x = (int) (a / 10);
				System.out.println("Una pista, la primera cifra es " + x);
				System.out.print("Inténtalo de nuevo: ");
				b = sc.nextInt();
			} else if (i >= 3) {
				System.out.print("Oh, oh... ÚLTIMO intento: ");
				b = sc.nextInt();
			}
		}
	}

	/*
	 * Para el bucle For, crea una variable numeroFor, esta variable tendrá como
	 * valor 0 y su condición será que la variable sea igual o menor que 3, se irá
	 * incrementando en 1 su valor cada vez que se ejecute y deberá mostrarse por
	 * pantalla.
	 */

	// Hay muchas opciones para el bucle for y pongo dos ejemplos
	public static void ejemplo1() {
		System.out.println("----------ejemplo1----------");
		int numeroFor = 0;
		for (; numeroFor <= 3; numeroFor = numeroFor + 1) {
			System.out.println(numeroFor);
		}

	}

	public static void ejemplo2() {
		System.out.println("----------ejemplo2----------");
		for (int numeroFor = 0; numeroFor <= 3; numeroFor++) {
			System.out.println(numeroFor);
		}

	}

	// EJERCICIOS DE BUCLE WHILE
	public static void primerEjercicio() {
		int contador = 0;
		int i = 0;
		while (i < 10) {
			if (i % 2 == 0) {
				contador++;
			}
			i++;
		}
		System.out.println(contador);
	}
}
