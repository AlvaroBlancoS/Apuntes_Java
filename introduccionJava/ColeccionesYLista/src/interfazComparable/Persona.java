package interfazComparable;

public class Persona implements Comparable <Persona> {
	private int edad;
	private String nombre;

	public Persona(int edad, String nombre) {
		this.edad = edad;
		this.nombre = nombre;
	}
	//Ordenar por edad
	@Override
	public int compareTo(Persona o) {
		return this.edad-o.edad;
	}
	@Override
	public String toString() {
		return "Persona [edad=" + edad + ", nombre=" + nombre + "]";
	}
	
	
}
