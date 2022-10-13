package EjerciciosBucles;

import java.util.Scanner;

public class Ejercicio2 {

	/*
	 * Crear un metodo de adivinar numero aleatorio con bucle while Crear otro
	 * método y la función es lo mismo que el anterior pero debe crear los intentos
	 */

	public static void main(String[] args) {
		adivinaNumero();
	}

	public static void adivinaNumero() {
		int aleatorio = (int) (Math.random() * 100);
		Scanner in = new Scanner(System.in);
		int numero = 0;

		while (numero != aleatorio) {
			System.out.println("Introduzca un numero, por favor");
			numero = in.nextInt();

			if (aleatorio < aleatorio) {
				System.out.println("Mas bajo");
			} else if (aleatorio > aleatorio) {
				System.out.println("Mas alto");
			}
		}

		System.out.println("Correcto!!");
	}

	public static void adivinaNumero_conIntentos() {
		int aleatorio = (int) (Math.random() * 100);
		Scanner in = new Scanner(System.in);
		int numero = 0;
		int intentos = 0;

		while (numero != aleatorio) {
			intentos++;
			System.out.println("Introduzca un numero, por favor");
			numero = in.nextInt();

			if (aleatorio < aleatorio) {
				System.out.println("Mas bajo");
			} else if (aleatorio > aleatorio) {
				System.out.println("Mas alto");
			}
		}

		System.out.println("Correcto!!. Lo has conseguido en "+ intentos +" intentos");
	}

}
