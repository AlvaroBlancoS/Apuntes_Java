package ejemplos;

import java.util.Scanner;

public class ElScannerV2 {

	public static void main(String[] args) {

		String nombre = leerString("Como te llamas?");
		int edad = leerEntero("Cuantos anios tienes?");
		if (edad <= 17) {
			System.out.println("Hola " + nombre + ", aun eres jovencito");
		} else if (edad >= 18) {
			System.out.println("Hola sr/a. " + nombre);
		}

	}

	/**
	 * 
	 * @return Devuelve el scanner de número entero
	 */
	@SuppressWarnings("resource")
	public static int leerEntero() {
		return new Scanner(System.in).nextInt();
	}

	/**
	 * 
	 * @param msg
	 * @return devuelve el método de leerEntero()
	 */
	public static int leerEntero(String msg) {
		System.out.println(msg);
		return leerEntero();
	}

	/**
	 * 
	 * @return Devuelve el scanner de String
	 */
	@SuppressWarnings("resource")
	private static String leerString() {
		return new Scanner(System.in).next();
	}

	/**
	 * 
	 * @param msg
	 * @return Devuelve el método de leerString()
	 */
	private static String leerString(String msg) {
		System.out.println(msg);
		return leerString();
	}
}
