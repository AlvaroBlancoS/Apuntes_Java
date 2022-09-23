package bucles;


public class BucleFor {
	/*
	 * Es similar los bucles anteriores y las sentencias IF Este bucle tiene tres
	 * partes: -La declaración y visualización -La comparación -y La acción Estas
	 * tres partes pueden ser obligatorias o no. ejemplo:
	 * "PARA"(inicialización;comparación;acción)
	 * 
	 */

	public static void main(String[] args) {

	}

	public static void decremento() {
		for (int i = 10; i > 0;) {
			i--;
			System.out.println(i + " Hola mundo");
		}
	}

	public static void tablaDeMultiplicar(int numero) {
		System.out.println("Tabla de multiplicacion de " + numero);
		for (int i = 0; i <= 10; i++) {
			System.out.println(i + "*" + numero + "=" + (numero * i));
		}
	}

	public static void tablaDeDividir(double numero) {
		System.out.println("Tabla de división de " + numero);
		for (double i = 1; i <= 10; i++) {
			System.out.println(i + "/" + numero + "=" + (i / numero));
		}
	}

	public static void tablaDeSumar(int numero) {
		System.out.println("Tabla de sumar de " + numero);
		for (int i = 0; i <= 10; i++) {
			System.out.println(i + "+" + numero + "=" + (i + numero));
		}
	}

	public static void tablaDeRestar(int numero) {
		System.out.println("Tabla de restar de " + numero);
		for (int i = 0; i <= 10; i++) {
			System.out.println(i + "-" + numero + "=" + (i - numero));
		}
	}

	public static void primerBucleFor() {
		for (int i = 0; i < 10; i++) {
			if (i == 6) {
				continue;// Salta el valor 6 y continua a la siguiente iteracion
				// break;//rompe el flujo de ejecucion
			}
			System.out.println(i + " Hola Mundo");
		}
	}

	public static void primeraOpcion() {
		/*
		 * 1-Parte de iniciación de la declaración de la variable int contador=1;
		 * 2-Parte de condición contador >=contador 3- parte de accion contador++
		 * contador = contador +1
		 */
//		for (int contador = 1; contador <=10; contador++) {
//			System.out.println(contador);
//			
//		}

		for (int contador = 1; contador <= 10; contador = contador + 1) {
			System.out.println(contador);

		}

	}

	public static void temperatura() {
		// Podeis observar que while es similar que for
		var temperatura = 14;
//		while (temperatura !=15) {
//			System.out.println(temperatura);
//		}
		/*
		 * pero no hemos tocado el bloque de iniciación de la declaración ni tampoco de
		 * acción Solo estamos utilizando el bloque de la condición.
		 */

		for (; temperatura != 15;) {
			System.out.println(temperatura);
		}
	}

	public static void bucleInfinito() {
		for (double y = 0.1; y != 1.0; y += 0.1)
			System.out.println(y);
	}

}
