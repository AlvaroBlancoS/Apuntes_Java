package ejerciciosArrays;

public class Ejercicio3 {

	public static void main(String[] args) {
		verLosnumeros();
	}

	public static void verLosnumeros() {
		int[] numeros = { 2, 1, 2, 4, 4, 5, 5, 3 };
		int[] contador = new int[numeros.length];
		int max = 0, min = 0;
		min = max = numeros[0];
		int numeroVecesMayor = 0, numeroVecesMenor = 0;
		for (int i = 0; i < numeros.length; i++) {
			// Almacenamos
			contador[numeros[i]] += 1;

			if (numeros[i] > max) {
				max = numeros[i];
			}

			if (numeros[i] < min) {
				min = numeros[i];
			}
		}

		for (int i = 0; i < numeros.length; i++) {
			if (numeros[i] == max) {
				numeroVecesMayor++;
			}
			if (numeros[i] == min) {
				numeroVecesMenor++;
			}
		}

		// int repetir = 0;
		for (int i = 0; i < contador.length; i++) {
			System.out.println(i + ": " + contador[i] + " veces");
//			if (contador[i] > repetir) {
//				repetir = contador[i];
//			}

		}
		System.out.println("--------\nEl numero minimo es " + min + " y repite "
				+ (numeroVecesMenor == 1 ? numeroVecesMenor + " vez" : numeroVecesMenor + " veces"));
		System.out.println("El numero maximo es " + max + " y repite "
				+ (numeroVecesMayor == 1 ? numeroVecesMayor + " vez" : numeroVecesMayor + " veces"));
	}

}
