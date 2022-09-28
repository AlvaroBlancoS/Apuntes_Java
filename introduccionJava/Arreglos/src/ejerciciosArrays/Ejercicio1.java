package ejerciciosArrays;

public class Ejercicio1 {

	public static void main(String[] args) {
		String cadena = "Hola Mundo";
		contarCarateres(cadena);
	}

	public static void contarCarateres(String cadena) {
		// Para quitar espacios con el metodo replaceAll
		cadena = cadena.replaceAll("\\s", "");
		char[] charArray = cadena.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			System.out.println("Letra " + (i + 1) + ": " + charArray[i]);
		}
		System.out.println("Total letras : " + cadena.length());
	}

}
