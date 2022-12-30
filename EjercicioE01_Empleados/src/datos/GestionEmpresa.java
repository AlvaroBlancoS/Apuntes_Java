package datos;

import java.util.ArrayList;
import java.util.List;
import model.Director;
import model.Empleado;
import model.Gerente;
import utilidades.Agregar;

/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */
public class GestionEmpresa {
	private static List<Empleado> empleados = listaEmpleados();
	private static List<Empleado> gerentes = listaGerentes();
	private static List<Empleado> directores = listaDirectores();
	private static boolean isExists;

	private static ArrayList<Empleado> listaEmpleados() {
		List<Empleado> empleados = null;
		empleados = new ArrayList<>();
		empleados.add(new Empleado("Alvaro", "Blanco Sangines", 1000, Agregar.fecha(22, 2, 1988)));
		empleados.add(new Empleado("Juan", "Garcia Gonzalez", 1000, Agregar.fecha(15, 10, 1990)));
		return (ArrayList<Empleado>) empleados;
	}

	private static ArrayList<Empleado> listaGerentes() {
		List<Empleado> empleados = null;
		empleados = new ArrayList<>();
		empleados.add(new Gerente("Diego", "Martinez Olasso", 1000, Agregar.fecha(12, 12, 1970), "Informatica"));
		empleados.add(new Gerente("Ana", "Bertin Rojo", 1050, Agregar.fecha(30, 1, 1985), "marketing"));
		return (ArrayList<Empleado>) empleados;
	}

	private static ArrayList<Empleado> listaDirectores() {
		List<Empleado> empleados = null;
		empleados = new ArrayList<>();
		empleados.add(new Director("Maria", "Lopez de la Vega", 1100, Agregar.fecha(12, 12, 1970), false, null));
		empleados.add(new Director("Alfonso", "Castrillo Omega", 1000, Agregar.fecha(30, 1, 1985), true, "12345TDF"));
		return (ArrayList<Empleado>) empleados;
	}

	public static void visualizarEmpleados() {
		isExists = false;
		System.out.println("\t\t\t---Los empleados---");
		for (Empleado empleado : empleados) {
			System.out.println(empleado);
			isExists = true;
		}

		if (isExists == false) {
			System.out.println("No hay ningún empleado");
		}

	}

	public static void visualizarGerentes() {
		isExists = false;
		System.out.println("\t\t\t-----los gerentes----");
		for (Empleado empleado : gerentes) {
			if (empleado instanceof Gerente) {
				Gerente verGerentes = (Gerente) empleado;
				System.out.println(verGerentes.toString());
				isExists = true;
			}
		
		}
		if (isExists == false) {
			System.out.println("No hay ningún gerente");
		}
	}

	public static void visualizarDirectores() {
		isExists = false;
		System.out.println("\t\t\t-----los directores----");
		for (Empleado empleado : directores) {
			if (empleado instanceof Director) {
				Director verDirectores = (Director) empleado;
				System.out.println(verDirectores.toString() + "\n----------");
				isExists = true;
			}
		}
		if (isExists == false) {
			System.out.println("No hay ningún director");
		}
	}

	public static void incluirEmpleados(String nombre, String apellidos, double salario, int dia, int mes, int anio) {
		empleados.add(new Empleado(nombre, apellidos, salario, Agregar.fecha(dia, mes, anio)));
	}

	public static void incluirGerentes(String nombre, String apellidos, double salario, int dia, int mes, int anio,
			String dep) {
		gerentes.add(new Gerente(nombre, apellidos, salario, Agregar.fecha(dia, mes, anio), dep));

	}

	public static void incluirDirectores(String nombre, String apellidos, double salario, int dia, int mes, int anio,
			boolean isCar, String matricula) {
		directores.add(new Director(nombre, apellidos, salario, Agregar.fecha(dia, mes, anio), isCar, matricula));
	}

	public static boolean borrarUnEmpleado(String nombre, String apellidos, int puesto) {

		if (puesto == 1) {// Eliminar empleados
			for (int i = 0; i < empleados.size(); i++) {
				if (empleados.get(i).getNombre().equals(nombre) && empleados.get(i).getApellidos().equals(apellidos)) {
					empleados.remove(i);
					return true;
				}
			}
		} else if (puesto == 2) {// Eliminar gerentes
			for (int i = 0; i < gerentes.size(); i++) {
				if (gerentes.get(i).getNombre().equals(nombre) && gerentes.get(i).getApellidos().equals(apellidos)) {
					gerentes.remove(i);
					return true;
				}
			}
		} else if (puesto == 3) {// Eliminar directores
			for (int i = 0; i < directores.size(); i++) {
				if (directores.get(i).getNombre().equals(nombre)
						&& directores.get(i).getApellidos().equals(apellidos)) {
					directores.remove(i);
					return true;
				}
			}
		}

		return false;

	}

	public static boolean isExistEmploye(String nombre, String apellidos) {
		for (Empleado empleado : empleados) {
			if (empleado.getNombre().equals(nombre) && empleado.getApellidos().equals(apellidos)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isExistGerente(String nombre, String apellidos) {
		for (Empleado empleado : gerentes) {
			if (empleado instanceof Gerente) {
				Gerente verGerentes = (Gerente) empleado;
				if (verGerentes.getNombre().equals(nombre) && verGerentes.getApellidos().equals(apellidos)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isExistDirector(String nombre, String apellidos) {
		for (Empleado empleado : directores) {
			if (empleado instanceof Director) {
				Director verDirectores = (Director) empleado;
				if (verDirectores.getNombre().equals(nombre) && verDirectores.getApellidos().equals(apellidos)) {
					return true;
				}
			}
		}
		return false;
	}

}
