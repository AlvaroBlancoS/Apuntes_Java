
public class ArreglosDimensiones {

	public static void main(String[] args) {
		elArrayTridimensional();
	}

	public static void elArray() {
		int valores[] = new int[5];
		valores[0] = 10;// 0<5
		valores[1] = 20;// 1<5
		valores[2] = 30;// 2<5
		valores[3] = 40;// 3<5
		valores[4] = 50;// 4<5
						// 5<5 ESTA NO!!!

		for (int i = 0; i < valores.length; i++) {
			System.out.println(valores[i]);
		}
	}

	public static void elArrayBidimensional() {
		int numeros[][] = { { 10, 20, 30, 40, 50 }, { 60, 70, 80, 90, 100 } };

		for (int i = 0; i < numeros.length; i++) {
			for (int j = 0; j < numeros[i].length; j++) {
				System.out.println(numeros[i][j]);
			}
		}
	}

	/// No funciona correctamente
	public static void elArrayTridimensional() {
		int numeros[][] = { { 10, 20, 30, 40, 50 }, { 60, 70, 80, 90, 100 }, { 110, 120, 130, 140, 150 } };

		for (int i = 0; i < numeros.length; i++) {
			for (int j = 0; j < numeros[i].length; j++) {
				for (int k = 0; k < numeros[j].length; k++) {
					System.out.println(numeros[i][j]);
				}
			}
		}
	}

}
