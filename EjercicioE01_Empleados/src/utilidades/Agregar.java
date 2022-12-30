package utilidades;

import java.time.LocalDate;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gui.Pantalla;
/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */
public class Agregar {

	private static LocalDate fecha = LocalDate.now();

	public static LocalDate fecha(int dia, int mes, int anio) {
		fecha = LocalDate.of(anio, mes, dia);
		return fecha;

	}

	@SuppressWarnings("resource")
	public static int leerEntero() {
		return new Scanner(System.in).nextInt();
	}

	public static int leerEntero(String msg) {
		System.out.println(msg);
		return leerEntero();
	}

	@SuppressWarnings("resource")
	public static double leerDouble() {
		return new Scanner(System.in).nextDouble();
	}

	public static double leerDouble(String msg) {
		System.out.println(msg);
		return leerDouble();
	}

	@SuppressWarnings("resource")
	public static String leerString() {
		return new Scanner(System.in).nextLine();
	}

	public static String leerString(String msg) {
		System.out.println(msg);
		return leerString();
	}

	public static Logger mensajePantalla() {
		return LogManager.getLogger(Pantalla.class);
	}

}
