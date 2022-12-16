package service;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import util.Agregar;
import util.ExceptionDNI;

/**
 * 
 * @author Alvaro Blanco Sanginés
 *
 */
public class RegistroCivil {
	static char[] letras = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
			'H', 'L', 'C', 'K', 'E' };

	/**
	 * Simplemente es calcular y validar un número de DNI.
	 */
	public static void calcularYValidarDNI() {
		int numero = Agregar.leerEntero("introduzca el numero de DNI");
		System.out.println(
				(validarDNI(calcularNif(numero)) == true) ? calcularNif(numero) + " es valido" : "No es valido");
	}

	/**
	 * Sin embargo, este método se trata de validar los números de dni. Hasta cuando
	 * el usuario introduzca una f, el bucle se para y aparecerá el resultado
	 */
	public static void calcularYValidarDNI2() {
		String numero = null;
		// 23456789 23412034
		ArrayList<String> almacenarSoloNumeros = new ArrayList<>();
		ArrayList<String> almacenarCadenas = new ArrayList<>();
		do {
			numero = Agregar.leerString("Introduzca los numeros de DNI");
			if (!numero.equalsIgnoreCase("f")) {
				boolean isNumeric = numero.matches("[+-]?\\d*(\\.\\d+)?");
				if (isNumeric) {
					almacenarSoloNumeros.add(numero);
				} else {
					almacenarCadenas.add(numero);
				}

			}

		} while (!numero.equalsIgnoreCase("f"));
		int convertirNumero = 0;
		for (int i = 0; i < almacenarSoloNumeros.size(); i++) {
			try {
				convertirNumero = Integer.parseInt(almacenarSoloNumeros.get(i));
				if (validarDNI(calcularNif(convertirNumero)) == true) {
					System.out.println(calcularNif(convertirNumero).subSequence(0, 8) + " es valido y la letra es "
							+ calcularNif(convertirNumero).subSequence(8, 9));
				} else {
//					System.out.println(convertirNumero + " no es valido");
					throw new ExceptionDNI(convertirNumero);
				}

			} catch (ExceptionDNI e) {
				Agregar.mensajeRegistroCivil().log(Level.FATAL, e.toString());
			}

		}

		for (String resp : almacenarCadenas) {
			System.err.println(resp + " es una cadena, debes introducir solo numero");
		}
	}

	/**
	 * 
	 * @param numero
	 * @return simplemente devuelve el dni si el número es correcto.
	 */
	private static String calcularNif(int numero) {
		char letra = letras[numero % 23];
		return "" + numero + letra;
	}

	/**
	 * 
	 * @param document
	 * @return Si el dni es válido, entonces le devuelve un true. Si no es válido,
	 *         entonces le devuelve un false.
	 */
	private static boolean validarDNI(String document) {
		int caracteres = 9;
		boolean isValido = true;
		boolean tieneLetra = false;
		char[] miLetra = document.toUpperCase().toCharArray();
		if (caracteres == document.length()) {
			// Averiguar si tiene letra
			for (int i = 0; i < letras.length; i++) {
				if (letras[i] == miLetra[8]) {
					tieneLetra = true;
				}
			}
			if (tieneLetra == true) {
				// Tiene letra
				// Calcular el resto
				int calcularDNI = Integer.valueOf(document.substring(0, document.length() - 1)) % 23;
				if (miLetra[8] == letras[calcularDNI]) {
					// Es un dni de verdad
					isValido = true;
				} else {
					isValido = false;
				}

			} else {
				isValido = false;
			}
		} else {
			isValido = false;
		}

		return isValido;
	}
}
