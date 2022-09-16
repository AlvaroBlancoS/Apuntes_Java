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

		// Opcion 1: funcion sin parametros y sin tipo de retorno
//		menu();
//		holaMundo();
		// opcion 2: funcion sin parametros y con tipo de retorno
//		System.out.println(menu2());

		// Opcion 3: funcion con parametros y sin tipo de retorno
		holaMundo("Alvaro");
		// sumaVoid(num1, num2);

		// opcion 4: funcion con parametros y con tipo de retorno
		obtenerSaludo("Alvaro", "Blanco");
		
//		resultado = suma(num1, num2);
//		// resultado = otraSuma(num1, String.valueOf(num2));
//		System.out.println(suma(num1, num2));
//		System.out.println(resultado);
//		

	}
	// Opcion 1: funcion sin parametros y sin tipo de retorno
	/*
	 * void indica que no devuelve nada
	 */

	static void menu() {
		System.out.println("Bienvenidos al E-commerce de zapatillas:");
		System.out.println("1- ver zapatillas");
		System.out.println("2- comprar zapatillas");
		System.out.println("3- salir");
	}

	private static void holaMundo() {
		System.out.println("Hola Mundo");
	}

	private static void holaMundo(String nombre) {
		System.out.println("Hola " + nombre);
	}

	// opcion 2: funcion sin parametros y con tipo de retorno

	static String menu2() {

		return "Bienvenidos al E-commerce de zapatillas:\n1- Ver zapatillas\n2- comprar zapatillas\3- salir";
	}

	static double getPrice() {

		return 100.99;
	}

	// opcion 4: funcion con parametros y con tipo de retorno

	static String obtenerSaludo(String nombre, String apellidos) {
		return "Hola " + nombre + " " + apellidos;
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

