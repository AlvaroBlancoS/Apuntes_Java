package util;

import java.util.Scanner;

/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */
public class Agregar {
	/**
	 * 
	 * @return En vez de utilizar un scanner básico, también se puede crear un
	 *         método para la cadena de String que devuelve el scanner.
	 */
	@SuppressWarnings("resource")
	public static String leerString() {
		return new Scanner(System.in).next();
	}

	/**
	 * 
	 * @param msg
	 * @return sin embargo, también necesita un mensaje utilzando salida por pantalla
	 *         y que devuelva el método leerString() sobre si mismo
	 */
	public static String leerString(String msg) {
		System.out.println(msg);
		return leerString();
	}

}
