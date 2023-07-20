package tercerEjemplo;

import java.util.Arrays;
import java.util.Collections;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class Validaciones {

	/**
	 * 
	 * @param dni
	 * @return comprobar si el dni es válido o no
	 */

	public static void main(String[] args) {
		if (comprobarDNI("53504100x")) {
			System.out.println("falso");
		} else {
			System.out.println("verdadero");
		}

	}

	static public boolean comprobarDNI(String dni) {

		/**
		 * Se crea una lista de caracteres llamada "listCharDNI" que contiene los
		 * caracteres correspondientes a los dígitos de control válidos para un número
		 * de DNI en España.
		 */
		List<Character> listCharDNI = Collections.unmodifiableList(Arrays.asList('T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F',
				'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'));
		/**
		 * Se crea un objeto de tipo StringBuilder llamado "num" que se utilizará para
		 * construir una cadena de dígitos numéricos del DNI.
		 */
		StringBuilder num = new StringBuilder();

		/**
		 * Si la longitud del DNI es igual a cero, se agrega un "0" al inicio del DNI.
		 */
		if (dni.length() == 0) {
			dni = "0" + dni;
		}

		/**
		 * Si la longitud del DNI no es igual a 9 o el último carácter no es una letra,
		 * se retorna falso, lo que indica que el DNI no es válido.
		 */

		if (dni.length() != 9 || !Character.isLetter(dni.charAt(8))) {
			return false;
		}
		/**
		 * A continuación, se realiza un bucle para recorrer los primeros 8 caracteres
		 * del DNI. Si alguno de estos caracteres no es un dígito, se retorna falso.
		 */
		for (int i = 0; i < 8; i++) {
			if (!Character.isDigit(dni.charAt(i))) {
				return false;
			}
			/**
			 * A continuación, se realiza un bucle para recorrer los primeros 8 caracteres
			 * del DNI. Si alguno de estos caracteres no es un dígito, se retorna falso.
			 */
			num.append(dni.charAt(i));
		}
		/**
		 * Finalmente, se compara el último carácter del DNI (convertido a mayúscula)
		 * con el carácter correspondiente en la lista "listCharDNI", utilizando el
		 * valor numérico de la cadena "num" (convertido a entero y obtenido mediante
		 * parseInt()). Si son iguales, se retorna verdadero, lo que indica que el DNI
		 * es válido. De lo contrario, se retorna falso.
		 */
		return ((Character.toUpperCase(dni.charAt(8))) == listCharDNI.get(Integer.parseInt(num.toString()) % 23));

	}

	/**
	 * 
	 * @param nie
	 * @return comprobar si el nie es válido o no
	 */
	public boolean comprobarNIE(String nie) {
		if (nie.length() == 9) {
			String nieModificado;
			char primeraLetra = nie.charAt(0);
			if (Character.toUpperCase(primeraLetra) == 'X')
				nieModificado = "0" + nie.substring(1);
			else if (Character.toUpperCase(primeraLetra) == 'Y')
				nieModificado = "1" + nie.substring(1);
			else if (Character.toUpperCase(primeraLetra) == 'Z')
				nieModificado = "2" + nie.substring(1);
			else
				return false;
			return comprobarDNI(nieModificado);
		}
		return false;
	}

}
