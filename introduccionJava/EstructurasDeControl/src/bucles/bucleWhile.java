package bucles;

public class bucleWhile {

	/*
	 * Un bucle While es cuando cumple la condición y ejecuta los códigos. Cuando
	 * finaliza un código, vuelve a comprobar la condición y esto es lo que suele
	 * llamar iteración. Cuando deja de cumplir la condición, deja de ejecutar el
	 * código y sale del bucle
	 */

	public static void main(String[] args) {
//		primeraOpcion();
		// segundaOpcion();
		// primerBucleWhile();

//		if (esPrimo(5)) {
//			System.out.println("Es primo");
//		}else {
//			System.out.println("No es primo");
//		}

	}

	//
	public static void laSuma() {
		int x = 0;
		int y = 0;

		while (x < 5) {
			y += x;

			x++;

		}
		System.out.println(y);
	}



	public static void primerBucleWhile() {
		int count = 0;
		while (count < 10) {// Inicio
			count++;// Condicion
			if (count == 6) {
				// continue;//Salta el valor 6 y continua a la siguiente iteracion
				break;// rompe el flujo de ejecucion
			}
			System.out.println(count + " Hola mundo");
		}
	}

	public static void primeraOpcion() {
		int contador = 10;
		while (contador > 0) {
			System.out.println(contador);
			// Esta una opcion de descrementar
			// contador = contador - 1;
			// Pero esta es otra opcion y es más corto
			contador--;
		}
	}

	public static void segundaOpcion() {
		int contador = 1;
		while (contador <= 10) {
			System.out.println(contador);
			// Esta es la primera opcion
//			contador = contador + 1;
			// Y esta es la otra opción y es más corto
			contador++;
		}
	}

	public static void temperatura() {
		var temperatura = 15;
		while (temperatura != 15) {
			System.out.println(temperatura);
		}
	}

	public static boolean esPrimo(int numero) {
		int contador = 2;
		boolean primo = true;
		while ((primo) && (contador != numero)) {
			if (numero % contador == 0)
				primo = false;
			contador++;
		}
		return primo;
	}
	
	public static void verificarLaClave() {
		Scanner in = new Scanner(System.in);
		String clave = "Juan";
		String pass = null;

		while (clave.equals(pass) == false) {
			System.out.println("Introduzca la clave");
			pass = in.next();
			if (clave.equals(pass) == false) {
				System.err.println("Clave incorrecta");
			}
		}

		System.out.println("La clave correcta. Acceso permitido");
	}	

}
