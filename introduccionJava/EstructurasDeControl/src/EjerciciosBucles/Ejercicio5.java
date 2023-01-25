package EjerciciosBucles;

public class Ejercicio5 {
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @param frase
	 * @return poner una frase al revés.
	 */
	public static String fraseAlRevesWhile(String frase) {
		String fraseReves = "";
		int limite = frase.length() - 1;

		while (limite >= 0) {
			fraseReves = fraseReves + frase.charAt(limite);
			limite--;
		}

		return fraseReves;

	}

	/**
	 * 
	 * @param frase
	 * @return Este metodo ocurre lo mismo que el anterior (fraseAlRevesWhile) pero
	 *         con for
	 */
	public static String fraseAlRevesFor(String frase) {
		String fraseReves = "";
		for (int i = frase.length() - 1; i >= 0; i--) {
			fraseReves = fraseReves + frase.charAt(i);
		}
		return fraseReves;

	}

	/**
	 * 
	 * @param frase
	 * @return poner una frase al revés, pero si una letra es igual que x, entonces
	 *         interrumpe con break
	 */
	public static String fraseAlReves2While(String frase) {
		String fraseReves = "";
		int limite = frase.length() - 1;

		while (limite >= 0) {
			if (frase.charAt(limite) == 'X' || frase.charAt(limite) == 'x') {
				break;
			}
			fraseReves = fraseReves + frase.charAt(limite);
			limite--;
		}

		return fraseReves;

	}

	/**
	 * 
	 * @param frase
	 * @return Ocurre lo miso que el anterior (fraseAlReves2While) pero con bucle
	 *         for
	 */
	public static String fraseAlReves2For(String frase) {
		String fraseReves = "";
		for (int i = frase.length() - 1; i >= 0; i--) {
			if (frase.charAt(i) == 'X' || frase.charAt(i) == 'x') {
				break;
			}
			fraseReves = fraseReves + frase.charAt(i);
		}

		return fraseReves;

	}

}
