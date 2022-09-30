package ejemplos;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ElScanner {

	public static void main(String[] args) {

		segundoEjemplo();

	}

	public static void primerEjemplo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Mete dos numeros: ");
		int a = scanner.nextInt();// Bloquea ejecución del programa hasta que el usuario introduzca un dato
		int b = scanner.nextInt();
		System.out.println("Estos son numeros enteros: " + a + " " + b);
	}

	public static void segundoEjemplo() {
		int a = 0;
		int b = 0;
		boolean ok = false;
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Mete dos numeros");
			try {

				a = scanner.nextInt();
				b = scanner.nextInt();
				ok = true;
			} catch (InputMismatchException e) {
				System.err.println("Numeros invalidos");
			} finally {
				scanner.close();
			}

		} while (!ok);

		System.out.println("Has introducido " + a + " y " + b);
	}

}
