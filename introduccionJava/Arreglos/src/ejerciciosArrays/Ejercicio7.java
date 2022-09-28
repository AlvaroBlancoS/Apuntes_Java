package ejerciciosArrays;

public class Ejercicio7 {

	public static void main(String[] args) {
		String cadena = "Radar";
		String minuscula = cadena.toLowerCase();
		char[] letras = new char[minuscula.length()];
		for (int i = 0; i < minuscula.length(); i++) {
			if (Character.isLetter(minuscula.charAt(i))) {
				letras[i] = minuscula.charAt(i);
			}
		}
		
		if (isPalindromoChar(letras)) {
			System.out.println(cadena+" es palindromo");
		}else {
			System.out.println(cadena+" no es palindromo");
		}
	}

	static public boolean isPalindromoChar(char[] palabra) {
		// Recorrer letras desde 0 posición hasta la mitad
		for (int i = 0; i <= palabra.length / 2; i++) {
			// y desde la mitad hasta la última posición.
			if (palabra[i] != palabra[palabra.length - 1 - i]) {
				return false;
			}
		}
		return true;

	}

}
