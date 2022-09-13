package ProbarDepuracion;

public class CodigoDepuracion {

	/*
	 * La depuración de código consiste en buscar programas durante la ejecución de
	 * nuestra programa
	 */

	public static void main(String[] args) {
		function();

	}

	public static void function() {
		function2();
	}

	public static void function2() {
		function3();
	}

	public static void function3() {
		function4();
	}

	public static void function4() {
		System.out.println("Vaya viaje que nos están dando");
		var i = 15;
		System.out.println(i/0);
	}

	/*
	 * int suma = 1; suma += 15; System.out.println(suma)
	 * 
	 * int valores[]= new int[5]; int posicion = 5;
	 * System.out.println(valores[posicion]);
	 */
}
