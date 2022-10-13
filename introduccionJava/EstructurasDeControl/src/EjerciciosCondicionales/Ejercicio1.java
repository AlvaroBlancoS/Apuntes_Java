package EjerciciosCondicionales;

public class Ejercicio1 {
	
	/*
	 * Crear una función booleano para averiguar 
	 * si el numero es múltiple de cinco
	 */

	public static void main(String[] args) {
			if (isMultipleFive(23)==true) {
				System.out.println("Es un número de múltiple de cinco");
			}else {
				System.out.println("No es un número de múltiple de cinco");
			}
	}

	public static boolean isMultipleFive(int num) {
		if (num % 5 == 0) {
			return true;
		}

		return false;
	}

}
