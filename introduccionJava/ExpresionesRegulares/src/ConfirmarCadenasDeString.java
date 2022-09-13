
public class ConfirmarCadenasDeString {

	public static void main(String[] args) {
		// System.out.println(confirmarNumeroOcadena("2"));
		System.out.println(reemplazar("ejemplo", "kq", "in"));
	}

	public static String confirmarNumeroOcadena(String cadena) {
		String mensaje = null;
		if (cadena.toCharArray().length > 1) {
			if (cadena.matches("^[1-9][0-9]*$")) {
				mensaje = "son numeros";
			} else {
				mensaje = "Es una palabra";
			}
		} else {
			if (cadena.matches("^[1-9][0-9]*$")) {
				mensaje = "Es un numero";
			} else {
				mensaje = "Es una letra";
			}

		}
		return mensaje;
	}

	// Operacion ternario y expresiones regulares
	public static String reemplazar(String cadenaOriginal, String seleccion, String cambio) {
		String primeraCadena = "La cadena " + cadenaOriginal + " ha sido seleccionado "
				+ ((seleccion.toCharArray().length > 1)
						? (seleccion.matches("^[1-9][0-9]*$") ? " los numeros que son " + "'" + seleccion + "'"
								: " una cadena que es " + "'" + seleccion + "'")
						: (seleccion.matches("^[1-9][0-9]*$")) ? " un numero que es " + "'" + seleccion + "'"
								: "una letra que es " + "'" + seleccion + "'");
		
		String segundaCadena = "\nY vamos a sustituir por " + ((cambio.toCharArray().length > 1)
				? (cambio.matches("^[1-9][0-9]*$") ? " los numeros " + "'" + cambio + "'"
						: "una cadena " + "'" + cambio + "'")
				: (cambio.matches("^[1-9][0-9]*$") ? " un numero " + "'" + cambio + "'"
						: " una letra " + "'" + cambio + "'") )+ " y el resultado es "
						+ cadenaOriginal.replace(seleccion, cambio);
		
		//Confirmar si estas letras coinciden
			
		
		String resultado = "";
		return primeraCadena + segundaCadena;
	}

}
