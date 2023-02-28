package logger_1;

import java.util.logging.Level;
import java.util.logging.Logger;

import util.Agregar;
/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */
public class NumeroPar {
	// Creamos el objeto de Logger para instanciar la clase MiLogger que acabamos de
	// crear el método crearLog() con dos parámetros
	private static Logger miLog = MiLogger.crearLog(NumeroPar.class.toString(), true);

	public static void main(String[] args) {
		int numero = Agregar.leerEntero("Introduzca un número");
		miLog.log(Level.INFO, "El usuario ha introducido el numero " + numero);
		if (EsPar(numero) == true) {
			miLog.log(Level.WARNING, "el " + numero + " es par");
		} else {
			miLog.log(Level.SEVERE, "el " + numero + " no es par");
		}
	}
	/**
	 * 
	 * @param n
	 * @return Método sencillo si el número es par o impar
	 */
	private static boolean EsPar(int n) {
		if (n % 2 == 0) {
			return true;
		}

		return false;
	}

}
