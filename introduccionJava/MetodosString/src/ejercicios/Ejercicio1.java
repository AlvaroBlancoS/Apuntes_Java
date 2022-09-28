package ejercicios;

public class Ejercicio1 {

	public static void main(String[] args) {
		traducirPalabra("camión", 2);
	}

	public static void traducirPalabra(String pal, int idioma) {
		// Sustituimos las vocales con acentos por vocales sin acento, eliminamos
		// espacios y puntos. Y todas son minusculas
		String averiguar = pal.replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u")
				.replace(" ", "").replace(".", "").replace(",", "");
		// Seleccionamos la ultima letra de String
		String ultimoCaracter = averiguar.substring(averiguar.length() - 1);
		// Esa ultima letra almacenamos un array de char
		char[] ultimaLetra = ultimoCaracter.toCharArray();

		switch (idioma) {
		case 1:// En Alemán
			if (isVocal(ultimaLetra[ultimaLetra.length - 1])) {
				String todomenoslaultimaletra = averiguar.substring(0, averiguar.length() - 1);
				System.out.println(todomenoslaultimaletra + "ujem");
			} else {
				String todomenosDosultimasletras = averiguar.substring(0, averiguar.length() - 2);
				System.out.println(todomenosDosultimasletras + "ujem");
			}

			break;
		case 2:// En Búlgaro
			if (isVocal(ultimaLetra[ultimaLetra.length - 1])) {
				System.out.println(averiguar + "kov");
			} else {
				String todomenoslaultimaletra = averiguar.substring(0, averiguar.length() - 1);
				System.out.println(todomenoslaultimaletra + "kov");
			}

			break;

		default:
			System.err.println("ERROR!!!");
			break;
		}

	}

	public static boolean isVocal(char vocal) {
		if (vocal == 'a' || vocal == 'e' || vocal == 'i' || vocal == 'o' || vocal == 'u') {
			return true;
		} else {
			return false;
		}
	}

}
