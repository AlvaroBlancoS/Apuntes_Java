package ejerciciosArrays;

public class Ejercicio6 {

	public static void main(String[] args) {

	}
	
	public static void confirmarDNI(String document) {
		char[] resto = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22 };
		char[] letra = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
				'L', 'C', 'K', 'E' };
		boolean siTieneLetra = false;
		// document.substring(0, 9).toUpperCase();
		// System.out.println(document.substring(8));

//		if (document.length() == 9) {// Confirmar si la cadena String contiene 9 caracteres
//			// Confirmar si tiene una letra o no y ademas se convierte en mayuscula
//			// automaticamente
//
//			if (isNumeric(document.substring(8, 9)) == false) {
//				siTieneLetra = true;
//			}
//			for (int i = 0; i < letra.length; i++) {
//				String confirmarLetra = letra[i] + "";
//				if (document.substring(8).toUpperCase().equals(confirmarLetra)) {
//					siTieneLetra = true;
//				}
//			}
//
//			if (siTieneLetra == true) {
//				System.out.println("Perfecto");
//			} else {
//				System.out.println("Falta una letra");
//			}
//
//		}

		if (document.length() > 9) {
			if (document.length() > 8) {
				int maximoCaracter = document.length() - 1;
				System.out.println(maximoCaracter);
				// Averiguar tiene una letra o no.
				for (int i = 0; i < letra.length; i++) {
					String confirmarLetra = letra[i] + "";
					if (document.substring(document.length() - 1).toUpperCase().equals(confirmarLetra)) {
						siTieneLetra = true;
					}
				}

				if (siTieneLetra == true) {
					System.out.println("Tiene mas de 8 digitos ");

				} else {
					System.out.println("Tiene mas de 8 digitos y falta una letra");
				}
			}

		} else if (document.length() < 9) {

			if (document.length() < 8) {
				int minimoCarater = document.length() + 1;
				for (int i = 0; i < letra.length; i++) {
					String confirmarLetra = letra[i] + "";
					if (document.substring(document.length() - 1).toUpperCase().equals(confirmarLetra)) {
						siTieneLetra = true;
					}
				}

				if (siTieneLetra == true) {
					System.out.println("Tiene menos de 8 digitos ");

				} else {
					System.out.println("Tiene menos de 8 digitos y falta una letra");
				}
			}

		} else {
			System.out.println("Perfecto");
		}

	}

	public static boolean segundoEjercicio(String document) {
		boolean esValido = false;
		int i = 1;
		int caracterASCII = 0;
		char letra = ' ';
		int miNie = 0;
		int resto = 0;
		char[] asignacionLetra = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
				'V', 'H', 'L', 'C', 'K', 'E' };
		if (document.length() == 9 && Character.isLetter(document.charAt(8))) {
			do {
				caracterASCII = document.codePointAt(i);
				esValido = (caracterASCII > 47 && caracterASCII < 58);
				i++;
			} while (i < document.length() - 1 && esValido);
		}

		if (esValido) {
			letra = Character.toUpperCase(document.charAt(8));
			miNie = Integer.parseInt(document.substring(1, 8));
			resto = miNie % 23;
			esValido = (letra == asignacionLetra[resto]);
		}

		return esValido;
	}

}