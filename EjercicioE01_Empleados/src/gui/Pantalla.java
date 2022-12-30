package gui;

import org.apache.logging.log4j.Level;

import datos.GestionEmpresa;
import utilidades.Agregar;

/**
 * 
 * @author Álvaro Blanco Sagninés
 *
 */
public class Pantalla {
	private static boolean sigo = true;

	public static void informarEmpresa() {
		do {
			sigo = elegirOpcion(Agregar.leerEntero(principal()));

		} while (sigo);
	}

	private static boolean elegirOpcion(int opcion) {
		String resp = null;
		String nombre = null;
		String apellidos = null;
		double salario = 0;
		String dep = null;
		String matricula = null;
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
			GestionEmpresa.visualizarEmpleados();
			break;
		case 2:
			GestionEmpresa.visualizarGerentes();
			break;
		case 3:
			GestionEmpresa.visualizarDirectores();
			break;
		case 4:
			opcionResp = Agregar.leerEntero(registro());
			if (opcionResp == 1) {
				nombre = Agregar.leerString(msg(1));
				apellidos = Agregar.leerString(msg(2));
				try {
					if (GestionEmpresa.isExistEmploye(nombre, apellidos) == true) {
						throw new ExceptionEqualEmploye("Ya existe y es un empleado");
					} else if (GestionEmpresa.isExistGerente(nombre, apellidos) == true) {
						throw new ExceptionEqualEmploye("Ya existe y es un gerente");
					} else if (GestionEmpresa.isExistDirector(nombre, apellidos) == true) {
						throw new ExceptionEqualEmploye("Ya existe y es un director");
					} else {
						salario = Agregar.leerDouble(msg(3));
						System.out.println(msg(4));
						int dia = Agregar.leerEntero(msfFecha(1));
						int mes = Agregar.leerEntero(msfFecha(2));
						int anio = Agregar.leerEntero(msfFecha(3));
						GestionEmpresa.incluirEmpleados(nombre, apellidos, salario, dia, mes, anio);
					}

				} catch (ExceptionEqualEmploye e) {
					e.printStackTrace();
				}

			} else if (opcionResp == 2) {
				nombre = Agregar.leerString(msg(1));
				apellidos = Agregar.leerString(msg(2));
				try {
					if (GestionEmpresa.isExistEmploye(nombre, apellidos) == true) {
						throw new ExceptionEqualEmploye("Ya existe y es un empleado");
					} else if (GestionEmpresa.isExistGerente(nombre, apellidos) == true) {
						throw new ExceptionEqualEmploye("Ya existe y es un gerente");
					} else if (GestionEmpresa.isExistDirector(nombre, apellidos) == true) {
						throw new ExceptionEqualEmploye("Ya existe y es un director");
					} else {
						salario = Agregar.leerDouble(msg(3));
						System.out.println(msg(4));
						int dia = Agregar.leerEntero(msfFecha(1));
						int mes = Agregar.leerEntero(msfFecha(2));
						int anio = Agregar.leerEntero(msfFecha(3));
						dep = Agregar.leerString("Introduzca un departamento");
						GestionEmpresa.incluirGerentes(nombre, apellidos, salario, dia, mes, anio, dep);
					}

				} catch (ExceptionEqualEmploye e) {
					e.printStackTrace();
				}

			} else if (opcionResp == 3) {
				nombre = Agregar.leerString(msg(1));
				apellidos = Agregar.leerString(msg(2));

				try {
					if (GestionEmpresa.isExistEmploye(nombre, apellidos) == true) {
						throw new ExceptionEqualEmploye("Ya existe y es un empleado");
					} else if (GestionEmpresa.isExistGerente(nombre, apellidos) == true) {
						throw new ExceptionEqualEmploye("Ya existe y es un gerente");
					} else if (GestionEmpresa.isExistDirector(nombre, apellidos) == true) {
						throw new ExceptionEqualEmploye("Ya existe y es un director");
					} else {
						salario = Agregar.leerDouble(msg(3));
						System.out.println(msg(4));
						int dia = Agregar.leerEntero(msfFecha(1));
						int mes = Agregar.leerEntero(msfFecha(2));
						int anio = Agregar.leerEntero(msfFecha(3));
						String respCoche = Agregar.leerString("Tiene coche o no?");
						if (respCoche.equalsIgnoreCase("s") || respCoche.equalsIgnoreCase("si")) {
							matricula = Agregar.leerString("Introduzca la matricula");
							GestionEmpresa.incluirDirectores(nombre, apellidos, salario, dia, mes, anio, true,
									matricula);
						} else if (respCoche.equalsIgnoreCase("n") || respCoche.equalsIgnoreCase("no")) {
							GestionEmpresa.incluirDirectores(nombre, apellidos, salario, dia, mes, anio, false, null);
						}

					}

				} catch (ExceptionEqualEmploye e) {
					e.printStackTrace();
				}

			} else {
				Agregar.mensajePantalla().log(Level.ERROR, "La opcion " + opcionResp + " es incorrecto");
			}
			break;

		case 5:
			int numeroPuesto = Agregar.leerEntero(borrado());
			nombre = Agregar.leerString("Introduzca un nombre");
			apellidos = Agregar.leerString("Introduzca apellidos");
			if (GestionEmpresa.borrarUnEmpleado(nombre, apellidos, numeroPuesto)) {
				System.out.println("Ha sido borrado con exito");
			} else {
				System.out.println("No existe");
			}
			break;
		default:
			Agregar.mensajePantalla().log(Level.ERROR, "La opcion " + opcion + " es incorrecto");
			break;
		}

		return sigo;
	}

	public static String principal() {
		String msg = "---Opciones---\n0- salir\n1- Ver empleados\n2- ver gerentes\n3- ver directores\n4- Registrar\n5- Borrar";
		return msg;
	}

	public static String registro() {
		return "---Opciones---\n1-Registrar un empleado\n2-Registrar un gerente\n3-Registrar un director";
	}

	public static String borrado() {
		return "--Opciones---\n1-Eliminar un empleado\n2-Eliminar un gerente\n3-Eliminar un director";
	}

	public static String msg(int opcion) {
		return "Introduzca " + (opcion == 1 ? "un nombre"
				: (opcion == 2) ? "apellidos" : (opcion == 3) ? "Un salario" : "la fecha de nacimiento");
	}

	public static String msfFecha(int opcion) {
		return (opcion == 1 ? "Dia =>" : (opcion == 2) ? "Mes =>" : "Anio =>");
	}

}
