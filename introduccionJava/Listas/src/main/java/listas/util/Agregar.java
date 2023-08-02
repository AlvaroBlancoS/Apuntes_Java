package listas.util;

import java.util.Scanner;

public class Agregar {
	@SuppressWarnings("resource")
	public static int leerEntero() {
		return new Scanner(System.in).nextInt();
	}

	public static int leerEntero(String msg) {
		System.out.println(msg);
		return leerEntero();
	}

	@SuppressWarnings("resource")
	public static String leerString() {
		return new Scanner(System.in).next();
	}

	public static String leerString(String msg) {
		System.out.println(msg);
		return leerString();
	}
}
