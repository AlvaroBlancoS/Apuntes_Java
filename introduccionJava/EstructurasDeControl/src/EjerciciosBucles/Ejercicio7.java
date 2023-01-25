package EjerciciosBucles;

public class Ejercicio7 {

	public static void main(String[] args) {
		multipleDeCuatro(10, 200);
	}
	/**
	 * 
	 * @param inicio
	 * @param fin
	 * Hace de multiple de 4 de inicio a final
	 */
	static public void multipleDeCuatro(int inicio, int fin) {
		for (int i = inicio; i <= fin; i++) {
			if (i%4==0) {
				System.out.println(i);
			}
		}
	}

}
