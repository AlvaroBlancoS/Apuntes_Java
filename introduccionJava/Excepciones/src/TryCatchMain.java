import java.util.Scanner;

public class TryCatchMain {

	public static void main(String[] args) {
//		try {
//			leerNombres();
//		} catch (NameFormatException e) {
//			e.printStackTrace();
//		}
	//	segundoEjemplo("2","0");
		String prueba = "12345678s";
		System.out.println(prueba.substring(8,9));
		if (isNumeric(prueba.substring(8,9))) {
			System.out.println("Es numero");
		}else {
			System.out.println("No es numero");
		}
	}

	private static void leerNombres() throws NameFormatException {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce un nombre");
		while (teclado.hasNext()) {
			System.out.println("Introduce un nombre");
			String nombre = teclado.nextLine();
			if (nombre.length() < 8) {
				teclado.close();
				throw new NameFormatException("El noombre debe contener minimo 8 caracteres");
			}
		}
	}

	public static void primerEjemplo() {
		try {
			int result = 5 / 0;
			System.out.println(result);
		} catch (ArithmeticException ar) {
			ar.printStackTrace();
			System.err.println("No se puede dividir entre 0");
		} finally {
			System.out.println("Cierra recursos");
		}
	}

	public static void segundoEjemplo(String a, String b) {
		String respuesta;
		int numerador, denominador, cociente;
		try {
			numerador = Integer.parseInt(a);
			denominador = Integer.parseInt(b);
			cociente = numerador / denominador;
			respuesta = String.valueOf(cociente);
		} catch (NumberFormatException ex) {
			respuesta = "Se han introducido caracteres no numéricos";
		} catch (ArithmeticException ex) {
			respuesta = "División entre cero";
		}
		System.out.println(respuesta);
	}
	
	private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}

}
