package PrimerEjercicio;

public class Persona implements Comparable<Persona> {
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String dni;
	private int edad;

	public Persona() {

	}

	public Persona(String nombre, String apellido1, String apellido2, String dni, int edad) {
		this.nombre = nombre;
		primerApellido = apellido1;
		segundoApellido = apellido2;
		this.dni = dni;
		this.edad = edad;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public String getDni() {
		return dni;
	}

	public int getEdad() {
		return edad;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public int compareTo(Persona p) {
		return this.edad-p.edad;
	}

}
