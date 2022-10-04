package ejercicios;

public class Ejercicio3 {

	public static void main(String[] args) {
		String texto = "Hola mundo";
		System.out.println(reverse(texto.toLowerCase()));

	}

	public static String reverse(String texto) {

		return new StringBuilder(texto).reverse().toString();
	}

}
