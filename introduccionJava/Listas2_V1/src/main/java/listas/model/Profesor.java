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

	public Profesor(String nombre, String apellidos, String email, boolean tutor) {
		super(nombre, apellidos, email);
		this.tutor = tutor;
	}

	@Override
	public String toString() {
		return this.getNombre() + " " + this.getApellidos() + " " + this.getEmail() + " "
				+ (this.isTutor() == true ? "es tutor/a" : "");
	}

}
