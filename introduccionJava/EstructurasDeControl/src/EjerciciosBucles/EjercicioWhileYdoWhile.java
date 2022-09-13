package EjerciciosBucles;

public class EjercicioWhileYdoWhile {

	public static void main(String[] args) {
		ejercicioWhile();
		ejercicioDoWhile();

	}

	/*
	 * Crea un bucle While, este bucle tendrá que tener como condición que la
	 * variable numeroWhile sea inferior a 3, el bloque de código que tendrá el
	 * bucle deberá: Incrementar el valor de la variable en uno cada vez que se
	 * ejecute. Mostrarlo por pantalla cada vez que se ejecute.
	 */
	public static void ejercicioWhile() {
		System.out.println("-----------Ejercicio While-----------");
		int numeroWhile = 0;
		while (numeroWhile < 3) {
			System.out.println(numeroWhile);
			numeroWhile = numeroWhile + 1;
			//numeroWhile++;

		}

	}

	/*
	 * Para el bucle Do While, deberás crear la misma estructura que en el while,
	 * pero solo se debe ejecutar una vez
	 */
	public static void ejercicioDoWhile() {
		System.out.println("-----------Ejercicio do while-----------");
		int numeroWhile = 0;
		do {
			System.out.println(numeroWhile);
			numeroWhile = numeroWhile + 1;
		} while (numeroWhile < 3);
	}

}
