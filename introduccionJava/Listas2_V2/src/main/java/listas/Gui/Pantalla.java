package listas.Gui;

import java.util.InputMismatchException;

import listas.datos.Academia;
import listas.util.Agregar;

/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */
public class Pantalla {
	private static boolean sigo = true;
	private static Academia ac = new Academia();

	public static void informarCurso() {
		do {
			try {
				sigo = elegirOpcion(Agregar.leerEntero(menu()));
			} catch (InputMismatchException e) {
				System.err.println("ERROR: " + e.getLocalizedMessage());
			}

		} while (sigo);
	}

	private static boolean elegirOpcion(int opcion) {
		switch (opcion) {
		case 0:
			sigo = continuar(Agregar.leerString("Deseas continuar? si/no"));
			break;
		case 1:
			ac.listaAlumnosYProfesores(ac.verCursos());
			break;
		case 2:
			ac.modificarAlumnosYprofesores(ac.verCursos());
			break;
		case 3:
			ac.registrarAlumnoProfesor(ac.verCursos());
			break;

		default:
			System.err.println("La opcion " + opcion + " no existe");
			break;
		}

		return sigo;
	}

	private static String menu() {
		return "---Opciones---\n0-Salir\n1-Visualizar todo\n2-Modificar dato\n3-Registrar un dato";
	}

	private static boolean continuar(String resp) {
		boolean continuando = false;
		if (resp.equalsIgnoreCase("s") || resp.equalsIgnoreCase("si")) {
			continuando = false;
			System.out.println("Hasta pronto!!!");
		} else if (resp.equalsIgnoreCase("n") || resp.equalsIgnoreCase("no")) {
			continuando = true;
		} else {
			continuando = true;
			System.err.println("Respuesta equivocada");
		}

		return continuando;
	}
}
