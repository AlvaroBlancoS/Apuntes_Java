package CuartoEjemplo;

public class ProbarNumeros {

	/**
	 * 
	 * @param arr
	 * @return devuelve el numero mayor
	 */
	public int elMayor(int[] arr) {
		if (arr.length == 0) {
			return 0;
		}

		int mayor = arr[0];
		for (int j = 0; j < arr.length; j++) {
			if (arr[j] > mayor) {
				mayor = arr[j];
			}
		}
		return mayor;
	}
	/**
	 * 
	 * @param arr
	 * @return devuelve un número menor
	 */
	public int elMenor(int[] arr) {
		if (arr.length == 0) {
			return 0;
		}

		int menor = arr[0];
		for (int j = 0; j < arr.length; j++) {
			if (arr[j] < menor) {
				menor = arr[j];
			}
		}
		return menor;
	}
	
	
}
