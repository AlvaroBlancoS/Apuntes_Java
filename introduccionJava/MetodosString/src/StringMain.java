
public class StringMain {

	public static void main(String[] args) {
		String miCadena = "Hola mundo";
//		System.out.println(miCadena.indexOf("p"));
		System.out.println(verLaPosicionDeCaracter(miCadena, "o"));
//		verPosicionesDeCaracteres(miCadena, "o");

	}

	// indexOf() Busca la primera vez que aparece la posicion
	public static String verLaPosicionDeCaracter(String cadena, String buscar) {
		String result = "";
		if (cadena.indexOf(buscar) < 0) {
			result = "No se encuentra este caracter: " + buscar;
		} else {
			result = "La posición es " + cadena.indexOf(buscar);

		}
		return result;
	}

	//

	// startWith()
	public static void buscandoUnaPalabraDeInicio(String cadena) {
		boolean resultado = cadena.startsWith("Hola");
		if (resultado) {
			System.out.println("Empieza la palabra por lo que estoy buscando");
		} else {
			System.out.println("No empieza la palabra por lo que no estoy buscando");
		}
	}

	// endsWith()
	public static void buscandoUnaPalabraDeFinal(String cadena) {
		boolean resultado = cadena.endsWith("mundo");
		if (resultado) {
			System.out.println("Finaliza la palabra por lo que estoy buscando");
		} else {
			System.out.println("No finaliza la palabra por lo que no estoy buscando");
		}
	}

	// Con bucle FOR
	public static void recorrerTodalaCadena(String cadena) {
		for (int i = 0; i < cadena.length(); i++) {
			System.out.println(cadena.charAt(i));
		}

	}

	// charAt()
	public static char verSoloCaracter(String cadena, int posicion) {
		char caracter = cadena.charAt(posicion);
		return caracter;
	}

	// length()
	public static int longitudCadena(String cadena) {
		return cadena.length();
	}

	public static String numeroCaracteres(String cadena) {
		return cadena + " son: " + cadena.length() + " caracteres";
	}

	// toUperrCase();
	public static String convertirMayuscula(String cadena) {

		return "---Todas en mayúsculas---\n" + cadena.toUpperCase();
	}

	// toLowerCase()
	public static String convertirMinuscula(String cadena) {

		return "---Todas en minúsculas---\n" + cadena.toLowerCase();
	}

	// trim()
	public static String quitarEspacios(String cadena) {

		return "---Quitar espacios---\n" + cadena.trim();
	}

	// replace()
	public static String reemplazar(String cadena, String seleccion, String cambio) {

		return "Cadena original " + "'" + cadena + "'" + " ha seleccionado por " + "'" + seleccion + "'"
				+ " para cambiar " + "'" + cambio + "'" + "\ny el resultado es " + cadena.replace(seleccion, cambio);
	}

	// substring()
	public static String cortarCadena(String cadena, int posicionInicial, int posicionFinal) {
		return "El original es " + cadena + " y ahora es: " + cadena.substring(posicionInicial, posicionFinal);
	}

	// Split()
	public static void seleccionarTrozos(String cadena, String simbolo) {
		for (String trozos : cadena.split(simbolo)) {
			System.out.println(trozos);
		}
	}
}
