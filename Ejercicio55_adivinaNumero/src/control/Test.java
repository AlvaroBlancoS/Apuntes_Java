package control;

import utilidades.ExcepcionRango;

import org.apache.logging.log4j.Level;

import utilidades.Agregar;

/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */

public class Test {
	/**
	 * He probado con for
	 */
	public static void adivinaElNumeroFor() {
		int num = 0, intentos = 0;
		int aleatorio = ((int) (Math.random() * 100) + 1);
		int premio = aleatorio;
		String mensaje = null;
		for (int i = 1; i <= Integer.MAX_VALUE; i++) {
			try {
				intentos = i;
				num = Agregar.leerEntero("adivina un numero de 1 a 100");
				if (num == premio) {
					break;
				}

				if (premio > num) {
					mensaje = "Tiene que ser mayor";
					throw new ExcepcionRango(num);
				}

				if (premio < num) {
					mensaje = "Tiene que ser menor";
					throw new ExcepcionRango(num);
				}

			} catch (ExcepcionRango e) {
				Agregar.mensajeTest().log(Level.ERROR, e.mensajeERROR(mensaje));

			}

		}

		System.out.println("Has conseguido y "
				+ (intentos == 1 ? "es 1 intento. Tienes un premio gordo!!" : "son " + intentos + " intentos"));
	}

	/**
	 * He probado con do while
	 */
	public static void adivinaElNumeroDoWhile() {
		int num = 0, intentos = 0;
		int aleatorio = ((int) (Math.random() * 100) + 1);
		int premio = aleatorio;
		boolean seguir = true;
		String mensaje = null;
		do {
			try {
				intentos++;
				num = Agregar.leerEntero("adivina un numero de 1 a 100");
				if (num == premio) {
					seguir = false;
				}

				if (premio > num) {
					mensaje = "Tiene que ser mayor";
					throw new ExcepcionRango(num);
				}

				if (premio < num) {
					mensaje = "Tiene que ser menor";
					throw new ExcepcionRango(num);
				}

			} catch (ExcepcionRango e) {
				Agregar.mensajeTest().log(Level.ERROR, e.mensajeERROR(mensaje));

			}

		} while (seguir);

		if (seguir == false) {
			System.out.println("Has conseguido y "
					+ (intentos == 1 ? "es 1 intento. Tienes un premio gordo!!" : "son " + intentos + " intentos"));
		}

	}

}
