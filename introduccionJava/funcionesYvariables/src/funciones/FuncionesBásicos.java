package funciones;

import java.util.Scanner;

public class FuncionesBásicos {

	/*
	 * Una función nos evita tener que repetir el código.Dentro de la función puede
	 * cualquier construcción de lenguaje y con cualquier sintaxis de lenguaje
	 * 
	 * Es un prototipo que consiste en cómo se llama la función. Qué valores va a
	 * aceptar la función y qué tipo de datos va a retornar la función si retorna
	 * algo.
	 */

	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int num1 = sc.nextInt();
//		int num2 = sc.nextInt();
//		int resultado = 0;
//
//		resultado = suma(num1, num2);
//		// resultado = otraSuma(num1, String.valueOf(num2));
//
//		System.out.println(suma(num1, num2));
//		System.out.println(resultado);
//		
		// sumaVoid(num1, num2);
		
		
	holaMundo();
	holaMundo("Alvaro");
	}

	private static void holaMundo() {
		System.out.println("Hola Mundo");
	}

	private static void holaMundo(String nombre) {
		System.out.println("Hola "+nombre);
	}

	// Devuelve el número entero
	// Los parámetros son variables que solamente existen dentro de esta función
	public static int suma(int a, int b) {
		return a + b;
	}

	public static int otraSuma(int a, String b) {

		// return a + Integer.valueOf(b);
		return a + Integer.parseInt(b);
	}
	/*
	 * En caso que queremos crear una función que no devuelve nada, en vez de
	 * utilizar los datos primtivos, usamos un void que es un vacío o nulo
	 */

	public static void sumaVoid(int a, int b) {
//		int resultado;
//		resultado = a +b;
//		System.out.println(resultado);
		a += b;
		System.out.println(a);

	}

	public void calculadora(int a, int b) {
		System.out.println(a + b);
		System.out.println(a - b);
		System.out.println(a * b);
		System.out.println(a / b);
	}

	// Siempre es muy recomendable que una función tiene que ser muy útil y no muy
	// largas
}
