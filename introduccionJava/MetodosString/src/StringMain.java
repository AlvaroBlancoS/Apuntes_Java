
public class StringMain {

	public static void main(String[] args) {
//		String mensaje = " Hola mundo ";
//		// Cuantos caracteres tienen
//		System.out.println("Numero de caracteres de " + mensaje + ": con espacios " + mensaje.length());
//		// Quitamos espacios
//		String mensajeSinEspacio = mensaje.trim();
//		System.out.println(
//				"Numero de caracteres de " + mensajeSinEspacio + ": sin espacios " + mensajeSinEspacio.length());
//
//		String enMayuscula = mensajeSinEspacio.toUpperCase();
//		System.out.println("En mayuscula: " + enMayuscula);
//		String enMinuscula = mensajeSinEspacio.toLowerCase();
//		System.out.println("En minuscula: " + enMinuscula);
//
//		System.out.println(mensajeSinEspacio.startsWith(mensajeSinEspacio));
//		System.out.println(mensajeSinEspacio.endsWith(mensajeSinEspacio));
//		System.out.println(mensajeSinEspacio.indexOf(33));
//		System.out.println(mensajeSinEspacio.substring(0, 4));

		/*
		 * startWith("") endsWith("") indexOf("") subString(1,5) equals compare To
		 */

		// System.out.println(quitarEspacios("- hola mundo "));
		String miCadena = "Hola mundo";
		System.out.println(reemplazar(miCadena, miCadena, "Hola a todos"));

//		System.out.println(cortarCadena(miCadena, 3, 10));

//		System.out.println(quitarEspacios("dddd                                             "));

		split("Hola-a-todos", "-");
	}

	public static String convertirMayuscula(String cadena) {

		return "---Todas en mayúsculas---\n" + cadena.toUpperCase();
	}

	public static String convertirMinuscula(String cadena) {

		return "---Todas en minúsculas---\n" + cadena.toLowerCase();
	}

	public static String quitarEspacios(String cadena) {

		return "---Quitar espacios---\n" + cadena.trim();
	}

	public static String reemplazar(String cadena, String seleccion, String cambio) {

		return "Cadena original " + "'" + cadena + "'" + " ha seleccionado por " + "'" + seleccion + "'"
				+ " para cambiar " + "'" + cambio + "'" + "\ny el resultado es " + cadena.replace(seleccion, cambio);
	}

	public static String numeroCaracteres(String cadena) {
		return cadena + " son: " + cadena.length() + " caracteres";
	}

	public static String cortarCadena(String cadena, int posicionInicial, int posicionFinal) {
		return "El original es " + cadena + " y ahora es: " + cadena.substring(posicionInicial, posicionFinal);
	}

	public static void split(String cadena, String simbolo) {
		for (String trozos : cadena.split(simbolo)) {
			System.out.println(trozos);
		}
	}
}
