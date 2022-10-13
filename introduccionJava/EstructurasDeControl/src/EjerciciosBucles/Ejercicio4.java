package EjerciciosBucles;

import java.util.Scanner;

public class Ejercicio4 {
	
	/*
	 * Crear un do while y la condición si escribe un
	 * numero negativo, entonces este bucle seguirá ejecutando,
	 * pero si es positivo, sale del bucle.
	 * Deberá funcionar con Scanner.
	 */

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean incorrecto = true;
		int numero;
		do {
			System.out.println("Introduce un numero");
			numero = in.nextInt();
			if (numero>0) {
				System.out.println("El numero es correcto");
				incorrecto=false;
			}else {
				System.out.println("El numero es incorrecto");
			}
			
		} while (incorrecto);
	}

}
