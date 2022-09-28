package ejerciciosArrays;

public class Ejercicio2 {
	
	public static void main(String[] args) {
		String cadena = "Hola mundo";
		char caracter = 'M';
		System.out.println(contarUnCaracterSiRepite(cadena, caracter));
	}
	public static int contarUnCaracterSiRepite(String cadena, char c) {
		int contador = 0;
		char[] charArray = cadena.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			//System.out.println("Letra " + (i + 1) + ": " + charArray[i]);
			if (charArray[i] == c) {
				contador++;
			}
		}
		return contador;
	}
	
}
