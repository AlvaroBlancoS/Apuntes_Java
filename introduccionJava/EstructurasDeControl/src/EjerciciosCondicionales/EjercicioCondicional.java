package EjerciciosCondicionales;

public class EjercicioCondicional {
	/*
	 * Usando un if, crear una condición que compare si la variable numeroIf es
	 * positivo, negativo, o 0. Pista: Los números inferiores a 0 son negativos y
	 * los superiores, positivos.
	 */
	public static void main(String[] args) {
		int numeroIf = 1;
		if (numeroIf < 0) {
			System.out.println("Es un número negativo");
		} else if (numeroIf > 0) {
			System.out.println("Es un número positivo");
		} else {
			System.out.println("Es un cero");
		}
	}

}
