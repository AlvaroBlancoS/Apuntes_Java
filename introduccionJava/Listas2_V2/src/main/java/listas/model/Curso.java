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

	/**
	 * 
	 * 
	 * @param curso
	 */
	public Curso(String curso, List<Profesor> profesores, List<Alumno> alumnos) {
		this.curso = curso;
		this.profesores = profesores;
		this.alumnos = alumnos;
	}

	public Curso() {

	}

	public void agregarAlumno(Alumno alumno) {
		alumnos.add(alumno);
	}

	public void agregarProfesor(Profesor profesor) {
		profesores.add(profesor);
	}

}
