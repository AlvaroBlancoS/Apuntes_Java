package util;

import java.util.Scanner;
/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */
public class Agregar {
	
	@SuppressWarnings("resource")
	public static int leerEntero(){
		return new Scanner(System.in).nextInt();
	}
	
	public static int leerEntero(String msg) {
		System.out.println(msg);
		return leerEntero();
	}

}
