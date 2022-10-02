package ejerciciosArrays;

public class Ejercicio6 {

	public static void main(String[] args) {
		String dni = "17392800Q";
		if (confirmarDNI(dni)) {
			System.out.println("Es valido");
		}else {
			System.out.println("No es valido");
		}

	}

	//DNI de los españoles
	public static boolean confirmarDNI(String document) {
		char[] letras = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
				'L', 'C', 'K', 'E' };
		int caracteres = 9;
		boolean isValido=true;
		boolean tieneLetra = false;
		char[] miLetra = document.toUpperCase().toCharArray();
		if (caracteres == document.length()) {
			// Averiguar si tiene letra
			for (int i = 0; i < letras.length; i++) {
				if (letras[i] == miLetra[8]) {
					tieneLetra = true;
				}
			}
			if (tieneLetra == true) {
				// Tiene letra
				//Calcular el resto
				int calcularDNI = Integer.valueOf(document.substring(0, document.length() - 1)) % 23;
				if (miLetra[8] == letras[calcularDNI]) {
					//Es un dni de verdad
					isValido = true;
				} else {
					isValido = false;
				}

			} else {
				isValido = false;
			}
		} else {
			isValido = false;
		}
		
		return isValido;
	}

}
