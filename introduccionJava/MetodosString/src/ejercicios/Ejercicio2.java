package ejercicios;

import java.util.Scanner;

public class Ejercicio2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Escribe un texto");
		String texto = in.next();
		if (isPalindromoString(texto.toLowerCase())) {
			System.out.println(texto+" es palíndromo");
		}else {
			System.out.print(texto+" no es palíndromo");
		}

	}
	
	static public boolean isPalindromoString(String cadena) {
		// Sustituimos las vocales con acentos por vocales sin acento, eliminamos
		// espacios y puntos. Y todas son minusculas
		String remplazo = cadena.toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o")
				.replace("ú", "u");

		// invertimos la cadena original
		String invertir = new StringBuilder(remplazo).reverse().toString();
		// Comprobamos si la cadena invertida y la original son iguales
		if (invertir.equals(remplazo)) {
			return true;
		} else {
			return false;
		}
	}

}
