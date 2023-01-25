package EjerciciosBucles;

public class Ejercicio6 {

	public static void main(String[] args) {
		System.out.println(ocultarNombreWhile("Violeta"));
	}

	/**
	 * 
	 * @param nombre
	 * @return oculta el nombre con for
	 */
	static public String ocultarNombreFor(String nombre) {
		int limite = nombre.length();
		String ocultar = "";
		for (int i = 0; i < limite; i++) {
			ocultar = ocultar + "*";
		}
		return ocultar;
	}
	/**
	 * 
	 * @param nombre
	 * @return oculta el nombre con while
	 */
	static public String ocultarNombreWhile(String nombre) {
		int limite = nombre.length();
		String ocultar = "";
		int i = 0;
		while (i < limite) {
			ocultar = ocultar + "*";
			i++;
		}

		return ocultar;
	}

}
