package condicionales;

public class Condicionales {

	public static void main(String[] args) {
//		opcion1("primavera");
//		opcion2("verano");
//		opcion3("InviErno");
//		opcion4("verano", 20);
		// condicionesLogicas(50,10,20,1000);
		if (paroImpar(8)) {
			System.out.println("Es par");
		}else {
			System.out.println("Es impar");
		}
	}

	public static boolean paroImpar(int numero) {
		if (numero % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void condicionesLogicas(int num1, int num2, int num3, int num4) {
//		if (num1<num2 && num3<num4) {
//			System.out.println("Verdadero");
//		}else {
//			System.out.println("falso");
//		}

		if (num1 < num2 && num3 < num4) {
			System.out.println("Verdadero");
		} else if (num3 < num4) {
			System.out.println("else if");
		} else {
			System.out.println();
		}
	}

	public static void primerasCondiciones(int num1) {
		// Creo una condicion
		if (num1 >= 18) {// Abro la llave

			/* Estoy dentro de un bloque de instrucciones si el valor devuelve un true */
			System.out.println("Eres mayor de edad");
			// Creo otra condicion
		} else if (num1 < 0) {// Abro la llave de nuevo
			/*
			 * Estoy dentro de un bloque de instrucciones que duelve un true si la primera
			 * condicion devuelve un false
			 */
			System.err.println("Aún no has nacido");

		} else {// Abro otra vez la llave
			// Estoy el ultimo bloque si todas las condiciones han devuelto un false
			System.out.println("Eres menor de edad");
		}
		// Estoy fuera del bloque de instrucciones.

	}

	public static void operadoresTernarios(int num1) {
		// Operador ternario
		// expr_lógica ? valor_si_true : valor_si_false
		System.out.println((num1 >= 18) ? "Es mayor de edad" : "Es menor de edad");

		System.out.println((num1 >= 18) ? "Eres mayor de edad" : (num1 < 0) ? "Aún no has nacido" : "Es menor de edad");
	}

	public static void opcion1(String estacion) {
		System.out.println("----Función de opción 1---");
		if (estacion == "primavera") {
			System.out.println("Estamos en la primavera");
		} else if (estacion == "verano") {
			System.out.println("Estamos en verano");
		} else if (estacion == "otoño") {
			System.out.println("Estamos en otoño");
		} else {
			System.out.println("Estamos en invierno");
		}
	}

	public static void opcion2(String estacion) {
		System.out.println("----Función de opción 2---");
		if (estacion.equals("primavera")) {
			System.out.println("Estamos en la primavera");
		} else if (estacion.equals("verano")) {
			System.out.println("Estamos en verano");
		} else if (estacion.equals("otoño")) {
			System.out.println("Estamos en otoño");
		} else {
			System.out.println("Estamos en invierno");
		}
	}

	public static void opcion3(String estacion) {
		System.out.println("----Función de opción 3---");
		if (estacion.equalsIgnoreCase("primavera")) {
			System.out.println("Estamos en la primavera");
		} else if (estacion.equalsIgnoreCase("verano")) {
			System.out.println("Estamos en verano");
		} else if (estacion.equalsIgnoreCase("otoño")) {
			System.out.println("Estamos en otoño");
		} else {
			System.out.println("Estamos en invierno");
		}

	}

	public static void opcion4(String estacion, int temperatura) {
		System.out.println("----Función de opción 4---");
		if (estacion.equalsIgnoreCase("primavera") && temperatura < 25) {
			System.out.println("Estamos en la primavera");
		} else if (estacion.equalsIgnoreCase("verano") && temperatura > 25) {
			System.out.println("Estamos en verano");
		} else if (estacion.equalsIgnoreCase("otoño") && temperatura < 25) {
			System.out.println("Estamos en otoño");
		} else if (temperatura < 20) {
			System.out.println("Estamos en invierno");
		} else {
			System.out.println("Nos inventamos un estación");
		}

	}

}
