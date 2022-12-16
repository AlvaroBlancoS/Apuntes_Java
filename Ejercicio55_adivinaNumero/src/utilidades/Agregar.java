package utilidades;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import control.Test;

public class Agregar {

	@SuppressWarnings("resource")
	public static int leerEntero() {
		return new Scanner(System.in).nextInt();
	}

	public static int leerEntero(String msg) {
		System.out.println(msg);
		return leerEntero();
	}

	public static Logger mensajeTest() {

		return LogManager.getLogger(Test.class);
	}

}
