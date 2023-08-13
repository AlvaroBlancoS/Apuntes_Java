package listas.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Alvaro Blanco Sangines
 *
 */
public class Profesor extends Personas {
	@Getter
	@Setter
	private boolean tutor = false;
	@Getter
	@Setter
	private String asignatura;

	public Profesor(String nombre, String apellidos, String email, String asignatura, boolean tutor) {
		super(nombre, apellidos, email);
		this.asignatura = asignatura;
		this.tutor = tutor;
	}
	
	public Profesor() {}

	@Override
	public String toString() {
		return this.getNombre() + " " + this.getApellidos() + " " + this.getEmail() + " "+this.getAsignatura()
				+ (this.isTutor() == true ? "es tutor/a" : "");
	}

}
