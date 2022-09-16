package funciones;

public class Operadores {

	public static void main(String[] args) {
		// Aritméticos
		int numero1 = 10;
		int numero2 = 20;
		int resultadoSuma = numero1 + numero2;

		System.out.println(resultadoSuma);

		int resultadoResta = numero1 - numero2;
		System.out.println(resultadoResta);

		/*
		 * Comparacion > mayor que < menor que >= mayor igual que <= menor igual que ==
		 * igual que
		 */

		boolean resultado1 = numero1 > numero2;// false
		System.out.println(resultado1);
		boolean resultado2 = numero1 < numero2;// true
		System.out.println(resultado2);

		/*
		 * Logicos and && or ||
		 * 
		 */

		boolean resultado3 = numero1 > 5 && numero1 < 30;// true
		System.out.println(resultado3);

		int edad = 17;

		boolean carneJoven = edad > 15 && edad <= 26;//true
		
	}

}
