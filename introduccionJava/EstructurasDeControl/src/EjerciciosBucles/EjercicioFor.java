package EjerciciosBucles;

public class EjercicioFor {
	/*
	 * Para el bucle For, crea una variable numeroFor, esta variable tendrá como
	 * valor 0 y su condición será que la variable sea igual o menor que 3, se irá
	 * incrementando en 1 su valor cada vez que se ejecute y deberá mostrarse por
	 * pantalla.
	 */
	public static void main(String[] args) {

	}

	// Hay muchas opciones para el bucle for y pongo dos ejemplos
	public static void ejemplo1() {
		System.out.println("----------ejemplo1----------");
		int numeroFor = 0;
		for (; numeroFor <= 3; numeroFor = numeroFor + 1) {
			System.out.println(numeroFor);
		}

	}

	public static void ejemplo2() {
		System.out.println("----------ejemplo2----------");
		for (int numeroFor = 0; numeroFor <= 3; numeroFor++) {
			System.out.println(numeroFor);
		}

	}

	public static void calculaNumeroPI() {
		double y = 4;
		boolean flag = false;

		for (int x = 3; x < 10000000; x = x + 2) {
			if (flag) {
				y += ((double) 4 / x);
				flag = false;
			} else {
				y -= ((double) 4 / x);
				flag = true;
			}
		}

		System.out.println(y);
	}

}
