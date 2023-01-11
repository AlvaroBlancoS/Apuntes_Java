package datos;

import java.time.LocalDate;
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
		empleados.add(new Empleado("87086071K", "Alvaro", "Blanco Sangines", 1000, Agregar.fecha(22, 2, 1988)));
		empleados.add(new Empleado("61292855V", "Juan", "Garcia Gonzalez", 1000, Agregar.fecha(15, 10, 1990)));
		return (ArrayList<Empleado>) empleados;
	}

	private static ArrayList<Empleado> listaGerentes() {
		List<Empleado> empleados = null;
		empleados = new ArrayList<>();
		empleados.add(
				new Gerente("54276505R", "Diego", "Martinez Olasso", 1000, Agregar.fecha(12, 12, 1970), "Informatica"));
		empleados.add(new Gerente("69957734Z", "Ana", "Bertin Rojo", 1050, Agregar.fecha(30, 1, 1985), "marketing"));
		return (ArrayList<Empleado>) empleados;
	}

	private static ArrayList<Empleado> listaDirectores() {
		List<Empleado> empleados = null;
		empleados = new ArrayList<>();
		empleados.add(
				new Director("39378063Q", "Maria", "Lopez de la Vega", 1100, Agregar.fecha(12, 12, 1970), false, null));
		empleados.add(new Director("40636097L", "Alfonso", "Castrillo Omega", 1000, Agregar.fecha(30, 1, 1985), true,
				"12345TDF"));
		return (ArrayList<Empleado>) empleados;
	}

	public void visualizarEmpleados() {
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

	public void visualizarGerentes() {
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

	public void visualizarDirectores() {
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

	public void incluirEmpleados(Empleado em) {
		empleados.add(em);
	}

	public void incluirGerentes(Gerente g) {
		gerentes.add(g);

	}

	public void incluirDirectores(Director d) {
		directores.add(d);
	}

	/**
	 * 
	 * @param dni
	 * @param puesto
	 * @return se puede borrar el dni de un puesto de trabajo
	 */
	public boolean borrarUnEmpleado(String dni, int puesto) {

		if (puesto == 1) {// Eliminar empleados
			for (int i = 0; i < empleados.size(); i++) {
				if (empleados.get(i).getDni().equals(dni)) {
					empleados.remove(i);
					return true;
				}
			}
		} else if (puesto == 2) {// Eliminar gerentes
			for (int i = 0; i < gerentes.size(); i++) {
				if (gerentes.get(i).getDni().equals(dni)) {
					gerentes.remove(i);
					return true;
				}
			}
		} else if (puesto == 3) {// Eliminar directores
			for (int i = 0; i < directores.size(); i++) {
				if (directores.get(i).getDni().equals(dni)) {
					directores.remove(i);
					return true;
				}
			}
		}

		return false;

	}

	/**
	 * 
	 * @param dni
	 * @param dep
	 * @return simplemente cambia el departamento de un gerente
	 */
	public boolean modificarDepartamento(String dni, String dep) {
		for (int i = 0; i < gerentes.size(); i++) {
			if (gerentes.get(i).getDni().equals(dni)) {
				if (gerentes.get(i) instanceof Gerente) {
					Gerente modificar = (Gerente) gerentes.get(i);
					modificar.setEncargoDep(dep);
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 
	 * @param dni
	 * @param puestoOriginal
	 * @param dep
	 * @return puede ser que el empleado se hace gerente o que el director se hace
	 *         gerente
	 */
	public Gerente serGerente(String dni, int puestoOriginal, String dep) {
		String nombre = null, apellidos = null;
		double salario = 0;
		LocalDate fecha_nacimiento = null;
		Gerente g = null;

		switch (puestoOriginal) {
		case 1:// Empleado a gerente
			for (int i = 0; i < empleados.size(); i++) {
				if (empleados.get(i).getDni().equals(dni)) {
					nombre = empleados.get(i).getNombre();
					apellidos = empleados.get(i).getApellidos();
					salario = empleados.get(i).getSalario();
					fecha_nacimiento = empleados.get(i).getFecha_nacimiento();
					empleados.remove(i);
				}
			}
			g = new Gerente(dni, nombre, apellidos, salario, fecha_nacimiento, dep);

			break;
		case 2:// director a gerente
			for (int i = 0; i < directores.size(); i++) {
				if (directores.get(i).getDni().equals(dni)) {
					if (directores.get(i) instanceof Director) {
						Director obtenerDatos = (Director) directores.get(i);
						nombre = obtenerDatos.getNombre();
						apellidos = obtenerDatos.getApellidos();
						salario = obtenerDatos.getSalario();
						fecha_nacimiento = obtenerDatos.getFecha_nacimiento();
					}
					directores.remove(i);
				}
			}
			g = new Gerente(dni, nombre, apellidos, salario, fecha_nacimiento, dep);
			break;

		default:
			break;
		}
		return g;
	}

	/**
	 * 
	 * @param dni
	 * @param puestoOriginal
	 * @param isCar
	 * @param matricula
	 * @return Puede ser que el empleado se hace director o puede que el gerente se
	 *         hace director
	 */
	public Director serDirector(String dni, int puestoOriginal, boolean isCar, String matricula) {
		String nombre = null, apellidos = null;
		double salario = 0;
		LocalDate fecha_nacimiento = null;
		Director d = null;

		switch (puestoOriginal) {
		case 3:// Empleado se hace director
			for (int i = 0; i < empleados.size(); i++) {
				if (empleados.get(i).getDni().equals(dni)) {
					nombre = empleados.get(i).getNombre();
					apellidos = empleados.get(i).getApellidos();
					salario = empleados.get(i).getSalario();
					fecha_nacimiento = empleados.get(i).getFecha_nacimiento();
					empleados.remove(i);
				}
			}
			d = new Director(dni, nombre, apellidos, salario, fecha_nacimiento, isCar, matricula);

			break;
		case 4:// Gerente se hace director
			for (int i = 0; i < gerentes.size(); i++) {
				if (gerentes.get(i).getDni().equals(dni)) {
					if (gerentes.get(i) instanceof Gerente) {
						Gerente obtenerDatos = (Gerente) gerentes.get(i);
						nombre = obtenerDatos.getNombre();
						apellidos = obtenerDatos.getApellidos();
						salario = obtenerDatos.getSalario();
						fecha_nacimiento = obtenerDatos.getFecha_nacimiento();
					}
					gerentes.remove(i);
				}
			}

			d = new Director(dni, nombre, apellidos, salario, fecha_nacimiento, isCar, matricula);
			break;

		default:
			break;
		}
		return d;
	}

	/**
	 * 
	 * @param dni
	 * @param puestoOriginal
	 * @return puede ser que el director se hace empleado o puede ser que el gerente
	 *         se hace empleado
	 */
	public Empleado serEmpleado(String dni, int puestoOriginal) {
		String nombre = null, apellidos = null;
		double salario = 0;
		LocalDate fecha_nacimiento = null;
		Empleado e = null;
		switch (puestoOriginal) {
		case 5: // Director a empleado
			for (int i = 0; i < directores.size(); i++) {
				if (directores.get(i).getDni().equals(dni)) {
					if (directores.get(i) instanceof Director) {
						Director obtenerDatos = (Director) directores.get(i);
						nombre = obtenerDatos.getNombre();
						apellidos = obtenerDatos.getApellidos();
						salario = obtenerDatos.getSalario();
						fecha_nacimiento = obtenerDatos.getFecha_nacimiento();
					}
					directores.remove(i);
				}
			}
			e = new Empleado(dni, nombre, apellidos, salario, fecha_nacimiento);

			break;
		case 6: // Gerente a empleado
			for (int i = 0; i < gerentes.size(); i++) {
				if (gerentes.get(i).getDni().equals(dni)) {
					if (gerentes.get(i) instanceof Gerente) {
						Gerente obtenerDatos = (Gerente) gerentes.get(i);
						nombre = obtenerDatos.getNombre();
						apellidos = obtenerDatos.getApellidos();
						salario = obtenerDatos.getSalario();
						fecha_nacimiento = obtenerDatos.getFecha_nacimiento();
					}
					gerentes.remove(i);
				}
			}

			e = new Empleado(dni, nombre, apellidos, salario, fecha_nacimiento);
			break;

		default:
			break;
		}

		return e;
	}

	/**
	 * 
	 * @param dni
	 * @return Averigua si el dni del empleado existe
	 */
	public boolean isExistEmploye(String dni) {
		for (Empleado empleado : empleados) {
			if (empleado.getDni().equals(dni)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param dni
	 * @return Averigua si el dni del gerente existe
	 */
	public boolean isExistGerente(String dni) {
		for (Empleado empleado : gerentes) {
			if (empleado instanceof Gerente) {
				Gerente verGerentes = (Gerente) empleado;
				if (verGerentes.getDni().equals(dni)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param dni
	 * @return Averigua si el dni del director existe
	 */
	public boolean isExistDirector(String dni) {
		for (Empleado empleado : directores) {
			if (empleado instanceof Director) {
				Director verDirectores = (Director) empleado;
				if (verDirectores.getDni().equals(dni)) {
					return true;
				}
			}
		}
		return false;
	}

}
