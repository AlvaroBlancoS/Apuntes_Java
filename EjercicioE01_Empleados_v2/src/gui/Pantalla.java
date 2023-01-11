package gui;

import org.apache.logging.log4j.Level;

import services.EmpleadosServiceImpl;
import utilidades.Agregar;

/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */
public class Pantalla {
	private static boolean sigo = true;
	private static EmpleadosServiceImpl em = new EmpleadosServiceImpl();

	public static void informarEmpresa() {
		do {
			sigo = elegirOpcion(Agregar.leerEntero(principal()));

		} while (sigo);
	}

	private static boolean elegirOpcion(int opcion) {
		String resp = null;
		int opcionResp = 0;
		switch (opcion) {
		case 0:
			resp = Agregar.leerString("Deseas continuar? s/n");
			if (resp.equalsIgnoreCase("s") || resp.equalsIgnoreCase("si")) {
				sigo = false;
				System.out.println("Hasta pronto!!!");
			} else if (resp.equalsIgnoreCase("n") || resp.equalsIgnoreCase("no")) {

			} else {
				Agregar.mensajePantalla().log(Level.ERROR, "Error!!");
			}
			break;
		case 1:
			opcionResp = Agregar.leerEntero(MenuEmpleados());
			em.visualizar(opcionResp);

			break;
		case 2:
			opcionResp = Agregar.leerEntero(registro());
			if (opcionResp == 1) {
				em.registrarEmpleado();
			} else if (opcionResp == 2) {
				em.registrarGerente();
			} else if (opcionResp == 3) {
				em.registrarDirector();
			} else {
				Agregar.mensajePantalla().log(Level.ERROR, "La opcion " + opcionResp + " es incorrecto");
			}
			break;

		case 3:
			em.borrar();
			break;
		case 4:
			em.cambiarDepartamento();
			break;
		case 5:
			opcionResp = Agregar.leerEntero(msgPuesto());
			if (opcionResp == 1) {
				em.empleadoAgerente();
			} else if (opcionResp == 2) {
				em.directorAgerente();
			} else if (opcionResp == 3) {
				em.empleadoAdirector();
			} else if (opcionResp == 4) {
				em.gerenteAdirector();
			} else if (opcionResp == 5) {
				em.directorAEmpleado();
			} else if (opcionResp == 6) {
				em.gerenteAEmpleado();
			}else {
				Agregar.mensajePantalla().log(Level.ERROR, "La opcion " + opcionResp + " es incorrecto");
			}

			break;
		default:
			Agregar.mensajePantalla().log(Level.ERROR, "La opcion " + opcion + " es incorrecto");
			break;
		}

		return sigo;
	}

	public static String principal() {
		String msg = "---Opciones---\n0- salir\n1- Visualizar\n2- Registrar\n3- Borrar\n4- Modificar departamento\n5- Cambiar puesto de trabajo";
		return msg;
	}

	public static String MenuEmpleados() {
		String msg = "1- Ver empleados\n2- ver gerentes\n3- ver directores";
		return msg;
	}

	public static String registro() {
		return "---Opciones---\n1-Registrar un empleado\n2-Registrar un gerente\n3-Registrar un director";
	}

	private static String msgPuesto() {
		return "1- De empleado a gerente\n2- De director a gerente\n3- De empleado a director\n4- De gerente a director\n5- De director a empleado\n6- De gerente a empleado";
	}

}
