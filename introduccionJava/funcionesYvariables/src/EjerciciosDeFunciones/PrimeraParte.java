package EjerciciosDeFunciones;

public class PrimeraParte {
	/*
	 * Crear una función con tres parámetros que sean números que se suman entre sí.
	 * Llamar a la función en el main y darle valores.
	 */
	public static void main(String[] args) {
		int resultado = suma(10, 20, 10);
		System.out.println(resultado);
	}

	public static int suma(int a, int b, int c) {
		return a + b + c;
	}

}
