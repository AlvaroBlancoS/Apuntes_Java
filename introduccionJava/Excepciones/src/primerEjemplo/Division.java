package primerEjemplo;

public class Division {

	public static void main(String[] args) {
			divide(10,1);
	}

	public static void divide(int a, int b) throws ExceptionDividirUno {
		if (b == 1) { // si b=1 lance la excepción
			throw new ExceptionDividirUno("No voy a dividir entre uno");
		} else {
			// si b es distinto de cero, que divida a/b
			System.out.println(a / b);
		}

	}

}
