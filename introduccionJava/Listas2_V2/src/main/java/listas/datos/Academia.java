package listas.datos;

import java.util.ArrayList;
import java.util.List;

import listas.model.Alumno;
import listas.model.Curso;
import listas.model.Profesor;
import listas.util.Agregar;

/**
 * 
 * @author Alvaro Blanco Sangines
 *
 */
public class Academia {

	private static List<Curso> cursos = listaCursos();
	private static boolean siExiste = false;
	private static List<Alumno> aDam = alumnosDam();
	private static List<Alumno> aDaw = alumnosDaw();
	private static List<Profesor> pDam = profesoresDam();
	private static List<Profesor> pDaw = profesoresDaw();

	private static ArrayList<Curso> listaCursos() {
		List<Curso> c = new ArrayList<Curso>();
		c.add(new Curso("DAM", profesoresDam(), alumnosDam()));
		c.add(new Curso("DAW", profesoresDaw(), alumnosDaw()));
		return (ArrayList<Curso>) c;
	}

	private static ArrayList<Alumno> alumnosDam() {
		ArrayList<Alumno> a = new ArrayList<Alumno>();
		a.add(new Alumno("Alvaro", "Blanco", "alvarito@gmail.com"));
		a.add(new Alumno("Pepita", "Perez de la Pere", "alvarito@gmail.com"));
		a.add(new Alumno("Angustio", "Lloron de la tristeza", "queangustiopordios@gmail.com"));
		a.add(new Alumno("Majete", "Simpatico Iniciativa", "bieninicio@hotmail.com"));
		return a;
	}

	private static ArrayList<Profesor> profesoresDam() {
		ArrayList<Profesor> p = new ArrayList<Profesor>();
		p.add(new Profesor("Antonio", "Santos Puerta", "abrelapuerta@gmail.com", "Progamacion", false));
		p.add(new Profesor("Dolores remedios", "Esperanza de las Angustias", "haymedueleenelalama@gmail.com",
				"orientacion laboral", true));
		p.add(new Profesor("Senior pizzero", "Al lado de la Esquina", "bienricoybarato@hotmail.com",
				"Lenguaje y marcas", false));
		return p;
	}

	private static ArrayList<Alumno> alumnosDaw() {
		ArrayList<Alumno> a = new ArrayList<Alumno>();
		a.add(new Alumno("Ana", "Bolena Diablilla", "bolenita@gmail.com"));
		a.add(new Alumno("Ramon", "ramirez", "rr@gmail.com"));
		a.add(new Alumno("Carla", "Karina Saez", "carlita@gmail.com"));
		a.add(new Alumno("Rene", "Dupree de la Serna", "renedupre@hotmail.com"));
		return a;
	}

	private static ArrayList<Profesor> profesoresDaw() {
		ArrayList<Profesor> p = new ArrayList<Profesor>();
		p.add(new Profesor("Mariana", "Gallego San Sebastian", "marianita@gmail.com", "Base de datos", false));
		p.add(new Profesor("Orlando", "Jordan Lesnie", "orlandito@gmail.com", "orientacion laboral", true));
		p.add(new Profesor("El tio", "De Kebad", "mascaloriasperobarato@hotmail.com", "Lenguaje y marcas", true));
		return p;
	}

	/**
	 * Es una opcion para ver alumnos y profesores en un curso pero no estoy
	 * convencido, mi intencion es ver toda informacion en cada curso
	 * 
	 * @param op
	 */
	public void verListaAlumnosYprofesoresDAM(String cursoNombre, int op) {
		boolean damAlumno = false;
		boolean damProfesor = false;
		boolean dawAlumno = false;
		boolean dawProfesor = false;
		switch (op) {
		case 1:
			for (Alumno a : aDam) {
				System.out.println(a.toString());
				damAlumno = true;

			}
			if (damAlumno == false) {
				System.out.println("No hay ningún alumno de DAM");
			}

			for (Profesor p : pDam) {
				System.out.println(p.toString());
				damProfesor = true;

			}

			if (damProfesor == false) {
				System.out.println("No hay ningún profesor de DAM");
			}
		case 2:
			for (Alumno a : aDaw) {
				System.out.println(a.toString());
				dawAlumno = true;

			}
			if (dawAlumno == false) {
				System.out.println("No hay ningún alumno de DAW");
			}
			break;
		case 3:
			for (Profesor p : pDaw) {
				System.out.println(p.toString());
				dawProfesor = true;

			}

			if (dawProfesor == false) {
				System.out.println("No hay ningún profesor de DAW");
			}
			break;

		default:
			break;
		}

	}

	/**
	 * Este metodo si me convence mucho Cada curso aparece toda informacion de los
	 * alumnos y profesores. Ademas veo que el codigo es corto y limpio
	 * 
	 * @param nombreCurso
	 */
	public void listaAlumnosYProfesores(String nombreCurso) {
		for (Curso c : cursos) {
			if (c.getCurso().equals(nombreCurso)) {
				System.out.println("----Alumnos de " + c.getCurso() + "----");
				for (int i = 0; i < c.getAlumnos().size(); i++) {
					System.out.println(c.getAlumnos().get(i).toString());
				}
				System.out.println("----Profesores de " + c.getCurso() + "----");
				for (int i = 0; i < c.getProfesores().size(); i++) {
					System.out.println(c.getProfesores().get(i).toString());
				}

				siExiste = true;
			}
		}

		if (siExiste == false) {
			System.err.println("No hay dato");
		}
	}

	/**
	 * Ver como se registra un alumno o profesor en un curso existente.
	 * 
	 * @param nombreCurso
	 */
	public void registrarAlumnoProfesor(String nombreCurso) {
		int op = 0;
		do {
			if (cursoEncontrado(nombreCurso) == false) {
				System.err.println(nombreCurso + " no se encuentra");
				return;
			}
			op = Agregar.leerEntero("1-Registrar alumno\n2-Registrar profesor\n0-Salir");
			switch (op) {
			case 1:
				for (Curso c : cursos) {
					// Esto es una opcion de utilizar los parametros de contructor
					if (c.getCurso().equals(nombreCurso)) {
						Alumno nuevoAlumno = new Alumno(Agregar.leerString("Introduzca nombre"),
								Agregar.leerString("Introduzca apellidos"), Agregar.leerString("Introduzca email"));
						c.agregarAlumno(nuevoAlumno);
						break;
					}
				}
				listaAlumnosYProfesores(nombreCurso);
				break;
			case 2:
				for (Curso c : cursos) {
					if (c.getCurso().equals(nombreCurso)) {
						// Sin utilizar constructor, podemos usar los metodos de setters que es otra
						// opcion
						Profesor nuevoProfesor = new Profesor();
						nuevoProfesor.setNombre(Agregar.leerString("Introduzca nombre"));
						nuevoProfesor.setApellidos(Agregar.leerString("Introduzca apellidos"));
						nuevoProfesor.setAsignatura(Agregar.leerString("Introduzca asignatura"));
						nuevoProfesor.setEmail(Agregar.leerString("Introduzca el email"));
						nuevoProfesor.setTutor(tutor(Agregar.leerString(
								"Es tutor/a? simplemente escribe la letra 's' o lo contrario un clic en intro")));
						c.agregarProfesor(nuevoProfesor);
						break;
					}

				}

				listaAlumnosYProfesores(nombreCurso);

				break;

			default:
				break;
			}

		} while (op != 0);
	}

	/**
	 * No es lo mismo que registrar y que modificar
	 */
	public void modificarAlumnosYprofesores(String nombreCurso) {
		int op = 0;
		if (cursoEncontrado(nombreCurso) == false) {
			System.err.println(nombreCurso + " no existe");
			return;
		}
		do {
			op = Agregar.leerEntero("1-Modificar alumno\n2-Modificar profesor\n0-Salir");

			switch (op) {
			case 1:
				for (Curso c : cursos) {
					// Aparece una lista de los alumnos con indices
					if (c.getCurso().equals(nombreCurso)) {
						System.out.println("Lista de alumnos de curso " + nombreCurso);
						for (int i = 0; i < c.getAlumnos().size(); i++) {
							System.out.println((i + 1) + ". " + c.getAlumnos().get(i).getNombre() + ", "
									+ c.getAlumnos().get(i).getApellidos() + ", " + c.getAlumnos().get(i).getEmail());
						}
						// Y el usuario podra ingresar un numero del alumno para modificar
						int indiceAlumno = Agregar.leerEntero("Ingrese el numero del alumno a modificar") - 1;

						// Esto es el momento que se podrá modificar
						if (indiceAlumno >= 0 && indiceAlumno < c.getAlumnos().size()) {
							Alumno alumnoSeleccionado = c.getAlumnos().get(indiceAlumno);
							// Realiza las modificaciones necesarias en el alumno
							alumnoSeleccionado.setNombre(Agregar.leerString("Nuevo nombre"));
							alumnoSeleccionado.setApellidos(Agregar.leerString("Nuevos apellidos"));
							alumnoSeleccionado.setEmail(Agregar.leerString("Nuevo email"));

							System.out.println("Alumno modificado correctamente.");

						} else {
							System.err.println("Numero de alumno invalido");
						}
					}
				}

				break;
			case 2:
				for (Curso c : cursos) {
					if (c.getCurso().equals(nombreCurso)) {
						System.out.println("Lista de profesores de curso " + nombreCurso);
						for (int i = 0; i < c.getProfesores().size(); i++) {
							System.out.println((i + 1) + ". " + c.getProfesores().get(i).toString());
						}
						int indiceProfesor = Agregar.leerEntero("Ingrese el numero del alumno a modificar") - 1;

						if (indiceProfesor >= 0 && indiceProfesor < c.getProfesores().size()) {
							Profesor profesorSeleccionado = c.getProfesores().get(indiceProfesor);
							// Realiza las modificaciones necesarias en el alumno
							profesorSeleccionado.setNombre(Agregar.leerString("Nuevo nombre"));
							profesorSeleccionado.setApellidos(Agregar.leerString("Nuevos apellidos"));
							profesorSeleccionado.setEmail(Agregar.leerString("Nuevo email"));
							profesorSeleccionado.setTutor(tutor(Agregar.leerString(
									"Es tutor/a? simplemente escribe la letra 's' o lo contrario un clic en intro")));
							System.out.println("Profesor modificado correctamente.");

						} else {
							System.err.println("Numero de profesor invalido");
						}

					}
				}

				break;
			case 0:
				System.out.println("Hasta pronto!!");
				break;

			default:
				break;
			}

		} while (op != 0);
	}

	/**
	 * Basicamente visualiza los cursos que existen con indice para que el usuario
	 * pueda elegir un numero y devolver el nombre del curso
	 * 
	 * @return
	 */
	public String verCursos() {
		String nombreCurso = null;

		for (int i = 0; i < cursos.size(); i++) {
			System.out.println((i + 1) + " " + cursos.get(i).getCurso());
		}

		int op = Agregar.leerEntero("Ingrese un numero de curso") - 1;
		if (op >= 0 && op < cursos.size()) {
			nombreCurso = cursos.get(op).getCurso();
		} else {
			System.err.println("Numero de curso invalido");
		}

		return nombreCurso;
	}

	/**
	 * 
	 * @param nombreCurso
	 * @return
	 */
	private boolean cursoEncontrado(String nombreCurso) {
		for (Curso c : cursos) {
			if (c.getCurso().equals(nombreCurso)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 
	 * @param resp
	 * @return
	 */
	private boolean tutor(String resp) {
		return (resp.equalsIgnoreCase("s") ? true : false);
	}

}
