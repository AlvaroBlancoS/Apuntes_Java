package Utilidad;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import primerEjemplo.Calculadora;

public class Agregar {

	private static final Logger logCalculadora = LogManager.getLogger(Calculadora.class);

	public static void info(String msg) {
		logCalculadora.info(msg);
	}

	public static void warnning(String msg) {
		 logCalculadora.warn(msg);
	}

	public static  void error(String msg) {
		logCalculadora.error(msg);
	}
	
	
	@SuppressWarnings("resource")
	public static int leerEntero() {
		return new Scanner(System.in).nextInt();
	}
	
	public static int leerEntero(String msg) {
		System.out.println(msg);
		return leerEntero();
	}
}
