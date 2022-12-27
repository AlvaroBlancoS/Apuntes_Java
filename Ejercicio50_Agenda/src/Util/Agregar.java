package Util;

import java.util.Scanner;

import model.TipoContacto;

/**
 * 
 * @author Álvaro Blanco
 *
 */
public class Agregar {

	@SuppressWarnings("resource")
	public static String leerString() {
		return new Scanner(System.in).nextLine();
	}

	public static String leerString(String msg) {
		System.out.println(msg);
		return leerString();
	}

	@SuppressWarnings("resource")
	public static int leerEntero() {
		return new Scanner(System.in).nextInt();
	}

	public static int leerEntero(String msg) {
		System.out.println(msg);
		return leerEntero();
	}

	public static TipoContacto contactoAmistad() {
		return TipoContacto.CONTACTO_AMISTAD;
	}

	public static TipoContacto contactoFamiliar() {
		return TipoContacto.CONTACTO_FAMILIAR;
	}
}
