package listas.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Alvaro Blanco Sangines
 *
 */
public class Curso {
	@Getter
	@Setter
	private String curso;
	@Getter
	@Setter
	private List<Alumno> alumnos = new ArrayList<Alumno>();
	@Getter
	@Setter
	private List<Profesor> profesores = new ArrayList<Profesor>();

	public Curso(String curso) {
		this.curso = curso;
	}

	public Curso() {
	}

	public void agregarAlumno(String nombre, String apellidos, String email) {
		alumnos.add(new Alumno(nombre, apellidos, email));
	}

	public void agregarProfesor(String nombre, String apellidos, String email, boolean tutor) {
		profesores.add(new Profesor(nombre, apellidos, email, tutor));
	}
}
