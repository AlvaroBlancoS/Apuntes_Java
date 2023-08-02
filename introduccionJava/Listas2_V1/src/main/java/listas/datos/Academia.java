package listas.datos;

import listas.model.Alumno;
import listas.model.Curso;
import listas.model.Profesor;
/**
 * 
 * @author Alvaro Blanco Sangines
 *
 */
public class Academia {
	private static boolean siExiste = false;
	private static Curso dam = new Curso("DAM");
	private static Curso daw = new Curso("DAW");
	private final static String[] frases = { "---Lista de alumnos de ", "---Lista de profesores de " };

	private void cursoDam() {
		dam.agregarAlumno("Alvaro", "Blanco", "alvaro@gmail.com");
		dam.agregarAlumno("Pepe", "Matinez Castillo", "pepito@gmail.com");
		dam.agregarAlumno("Juana", "Dia Orlando", "juanita@gmail.com");
		dam.agregarProfesor("Mariano", "Gonzalo Perez", "marianito@gmail.com", true);
		dam.agregarProfesor("Carla", "Rojo Soto", "carla@gmail.com", false);
		dam.agregarProfesor("dd", "Gonddzalo sdfs", "sdfsdf@gmail.com", false);
	}

	private static void listaAlumnosDam() {
		System.out.println(frases[0] + dam.getCurso());
		for (Alumno i : dam.getAlumnos()) {
			System.out.println(i.getNombre() + " " + i.getApellidos() + " " + i.getEmail());
			siExiste = true;
		}

		if (siExiste == false) {
			System.out.println("No hay ningún dato");
		}
		System.out.println("------");
	}

	private static void listaProfesoresDam() {
		System.out.println(frases[1] + dam.getCurso());
		for (Profesor i : dam.getProfesores()) {
			System.out.println(i.getNombre() + " " + i.getApellidos() + " " + i.getEmail() + " "
					+ (i.isTutor() == true ? "es tutor/a" : ""));
		}

		if (siExiste == false) {
			System.out.println("No hay ningún dato");
		}

		System.out.println("------");

	}

	private void cursoDaw() {
		daw.agregarAlumno("Juanito", "Topete", "topete@gmail.com");
		daw.agregarAlumno("Mariana", "Chimichurri Saez", "marianita@gmail.com");
		daw.agregarAlumno("Don Juan", "El Guapo", "guapito@gmail.com");
		daw.agregarProfesor("Gandalf", "Barbudo el Gris", "viejete@gmail.com", false);
		daw.agregarProfesor("Maria Juana", "Escobar Los Santos", "mariSantos@gmail.com", false);
		daw.agregarProfesor("Ratoncito", "Perez Ladroncillo de muelas", "rataInframundo@gmail.com", true);
	}

	private static void listaAlumnosDaw() {
		System.out.println(frases[0] + daw.getCurso());
		for (Alumno i : daw.getAlumnos()) {
			System.out.println(i.toString());
			siExiste = true;
		}

		if (siExiste == false) {
			System.out.println("No hay ningún dato");
		}
		System.out.println("------");
	}

	private static void listaProfesoresDaw() {
		System.out.println(frases[1] + daw.getCurso());
		for (Profesor i : daw.getProfesores()) {
			System.out.println(i.toString());
		}

		if (siExiste == false) {
			System.out.println("No hay ningún dato");
		}

		System.out.println("------");

	}

	public void leerTodo() {
		cursoDam();
		listaAlumnosDam();
		listaProfesoresDam();

		cursoDaw();
		listaAlumnosDaw();
		listaProfesoresDaw();
	}

}
