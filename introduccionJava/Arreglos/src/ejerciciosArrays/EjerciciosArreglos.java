package ejerciciosArrays;

public class EjerciciosArreglos {

	public static void main(String[] args) {

//		primerEjercicio(6, -2, 73, 18, 45, 2, 51);
//		segundoEjercicio(5);
//		tercerEjemplo();
//		confirmarDNI("5350410");

	}

	// Saber cuantas veces se repite cada numero de un arreglo y cual es el numero de veces que aparece el numero mayor
	public static void tercerEjemplo() {
		int[] calificaciones = { 2, 1, 2, 4, 4, 5, 5, 3 };
		int[] contador = new int[calificaciones.length];

		for (int i = 0; i < calificaciones.length; i++) {
			contador[calificaciones[i]] += 1;
		}
		int max = 0;
		for (int i = 0; i < contador.length; i++) {
			System.out.println("Calificacion " + i + ": " + contador[i] + " veces");

			if (contador[i] > max) {
				max = contador[i];
			}

		}

		System.out.println(max);
	}

	public static void primerEjercicio(int num1, int num2, int num3, int num4, int num5, int num6, int num7) {
		int nums[] = { num1, num2, num3, num4, num5, num6, num7 };
		int menor = 0;
		int mayor = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > mayor) {
				mayor = nums[i];
			}

			if (nums[i] < menor) {
				menor = nums[i];
			}

		}

		System.out.println("El numero mayor es " + mayor + "\nEl numero menor es " + menor);

	}

	/*
	 * Construye un programa que genere 100 números aleatorios mediante el uso de la
	 * función Math.random y que posteriormente ofrezca al usuario la posibilidad
	 * de: -Conocer el mayor de los números -Conocer el menor de los números
	 * -Obtener la suma de todos los números -Sustituir el valor de un elemento por
	 * otro número introducido por teclado
	 */
	public static void segundoEjercicio(int numeros) {
		int nums[] = new int[numeros];
		int almacenar[] = new int[numeros];
		int numeroMenor = 0, numeroMayor = 0;
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			int aleatorio = (int) (Math.random() * 100 + 1);
			nums[i] = aleatorio;

//			System.out.println(nums[i]);

			if (nums[i] > numeroMayor) {
				numeroMayor = nums[i];

			}

//			else if (numeros<nums [i]){
//				numeroMenor = nums [i];
//			}

//			sum += nums[i];

		}
		int p = 0;
		while (p < numeros) {
			System.out.println(nums[p]);
			numeroMenor = nums[0];
			if (numeroMenor > nums[p]) {
				numeroMenor = nums[p];
			} else if (numeroMenor > nums[p] - 1) {
				numeroMenor = nums[p];
			}
			p++;
		}

//		System.out.println("Primera posicion---" + nums[0]);

		System.out.println("\nResultado es---> " + numeroMenor);

//		int media = sum / nums.length;
//		System.out.println("El numero menor es: " + numeroMenor + " y el numero mayor es: " + numeroMayor
//				+ "\nLa suma es: " + sum + " y la media es: " + media);
	}

	/*
	 * Implementa un programa que pregunte por un DNI y valida si se trata de un DNI
	 * Valido. El calculo de la letra correspondiente a los digitos
	 */
	public static void confirmarDNI(String document) {
		char[] resto = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22 };
		char[] letra = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
				'L', 'C', 'K', 'E' };
		boolean siTieneLetra = false;
		// document.substring(0, 9).toUpperCase();
		// System.out.println(document.substring(8));

//		if (document.length() == 9) {// Confirmar si la cadena String contiene 9 caracteres
//			// Confirmar si tiene una letra o no y ademas se convierte en mayuscula
//			// automaticamente
//
//			if (isNumeric(document.substring(8, 9)) == false) {
//				siTieneLetra = true;
//			}
//			for (int i = 0; i < letra.length; i++) {
//				String confirmarLetra = letra[i] + "";
//				if (document.substring(8).toUpperCase().equals(confirmarLetra)) {
//					siTieneLetra = true;
//				}
//			}
//
//			if (siTieneLetra == true) {
//				System.out.println("Perfecto");
//			} else {
//				System.out.println("Falta una letra");
//			}
//
//		}

		if (document.length() > 9) {
			if (document.length() > 8) {
				int maximoCaracter = document.length() - 1;
				System.out.println(maximoCaracter);
				// Averiguar tiene una letra o no.
				for (int i = 0; i < letra.length; i++) {
					String confirmarLetra = letra[i] + "";
					if (document.substring(document.length() - 1).toUpperCase().equals(confirmarLetra)) {
						siTieneLetra = true;
					}
				}

				if (siTieneLetra == true) {
					System.out.println("Tiene mas de 8 digitos ");

				} else {
					System.out.println("Tiene mas de 8 digitos y falta una letra");
				}
			}

		} else if (document.length() < 9) {

			if (document.length() < 8) {
				int minimoCarater = document.length() + 1;
				for (int i = 0; i < letra.length; i++) {
					String confirmarLetra = letra[i] + "";
					if (document.substring(document.length() - 1).toUpperCase().equals(confirmarLetra)) {
						siTieneLetra = true;
					}
				}

				if (siTieneLetra == true) {
					System.out.println("Tiene menos de 8 digitos ");

				} else {
					System.out.println("Tiene menos de 8 digitos y falta una letra");
				}
			}

		} else {
			System.out.println("Perfecto");
		}

	}

	public static boolean segundoEjercicio(String document) {
		boolean esValido = false;
		int i = 1;
		int caracterASCII = 0;
		char letra = ' ';
		int miNie = 0;
		int resto = 0;
		char[] asignacionLetra = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
				'V', 'H', 'L', 'C', 'K', 'E' };
		if (document.length() == 9 && Character.isLetter(document.charAt(8))) {
			do {
				caracterASCII = document.codePointAt(i);
				esValido = (caracterASCII > 47 && caracterASCII < 58);
				i++;
			} while (i < document.length() - 1 && esValido);
		}

		if (esValido) {
			letra = Character.toUpperCase(document.charAt(8));
			miNie = Integer.parseInt(document.substring(1, 8));
			resto = miNie % 23;
			esValido = (letra == asignacionLetra[resto]);
		}

		return esValido;
	}

}
