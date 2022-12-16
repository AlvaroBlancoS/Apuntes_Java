package control;

import utilidades.ExcepcionRango;

import org.apache.logging.log4j.Level;

import utilidades.Agregar;

/**
 * 
 * @author Álvaro Blancos Sanginés
 *
 */

public class Test {

	/**
	 * He probado con bucle do while
	 */
	public static void numeroRangoDoWhile() {
		int num = 0, intentos = 0;
		int aleatorio = ((int) (Math.random() * 100) + 1);
		int premio = aleatorio;
		boolean seguir = true;
		do {
			intentos++;
			num = Agregar.leerEntero("Introduzca un numero de 1 a 100");
			try {
				if ((premio > num) || (premio < num)) {
					seguir = true;
					throw new ExcepcionRango(num);
				}
				if ((premio == num)) {
					seguir = false;
				}

			} catch (ExcepcionRango e) {
				// Esto es un mensaje de error con Logger
//				Agregar.mensajeTest().error("ERROR. MENSAJE: "+e.toString());
				Agregar.mensajeTest().log(Level.ERROR, "ERROR. MENSAJE: " + e.toString());
			}
		} while (seguir);

		if (seguir == false) {
//			Agregar.mensajeTest().info("Correcto!!");
			Agregar.mensajeTest().log(Level.WARN, "Correcto!!");
			System.out.println("Has conseguido y "
					+ (intentos == 1 ? "es 1 intento. Tienes un premio gordo!!" : "son " + intentos + " intentos"));
		}

	}

	/**
	 * He probado con for.
	 */
	public static void numeroRangoFor() {
		int num = 0, intentos = 0;
		int aleatorio = ((int) (Math.random() * 100) + 1);
		int premio = aleatorio;
		for (int i = 1; i <= Integer.MAX_VALUE; i++) {
			intentos = i;
			num = Agregar.leerEntero("Introduzca un numero de 1 a 100");

			try {

				if ((premio > num) || (premio < num)) {
					throw new ExcepcionRango(num);

				}
				if (premio == num) {
//					System.out.println("Es correcto\nNumero de intentos: " + intentos);
					System.out.println("Has conseguido y " + (intentos == 1 ? "es 1 intento. Tienes un premio gordo!!."
							: "son " + intentos + " intentos"));
					break;
				}

			} catch (ExcepcionRango e) {
				Agregar.mensajeTest().log(Level.ERROR, "ERROR. MENSAJE: " + e.toString());
			}

		}

	}

}
