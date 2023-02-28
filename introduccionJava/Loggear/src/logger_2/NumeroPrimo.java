package logger_2;

import util.Agregar;

/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */
public class NumeroPrimo {
	public static void main(String[] args) {
		MiLogger2.crearLog(NumeroPrimo.class.toString(), true);
		if (esPrimo(Agregar.leerEntero("Introduzca un número"))) {
			MiLogger2.logInfo("Es primo");
		} else {
			MiLogger2.logError("No es primo");
		}
	}

	/**
	 * 
	 * @param n
	 * @return devuelve true si es número primo
	 */
	private static boolean esPrimo(int n) {
		int contador = 2;
		boolean primo = true;
		while ((primo) && (contador != n)) {
			if (n % contador == 0)
				primo = false;
			contador++;
		}
		return primo;
	}
}
