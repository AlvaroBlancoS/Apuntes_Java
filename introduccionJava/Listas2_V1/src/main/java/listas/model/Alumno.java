package listas.model;
/**
 * 
 * @author Alvaro Blanco Sangines
 *
 */
public class Alumno extends Personas {
	public Alumno(String nombre, String apellidos, String email) {
		super(nombre, apellidos, email);
	}

	@Override
	public String toString() {
		return this.getNombre()+" "+this.getApellidos()+" "+this.getEmail();
	}
}
