package EjerciciosDeEncapsulacion;

public class Main {
	/*
	 * -Crear un objeto persona de la Clase Persona en el Main 
	 * -Utilizar los gets y sets para darle valores a estas
	 * propiedades de la calse Persona.
	 * -Mostrar por consola.
	 */
	public static void main(String[] args) {
		//----Este es el ejercicio que me han pedido
		Persona persona = new Persona();
		persona.setNombre("Pepito");
		persona.setEdad(18);
		persona.setTelefono("928302100");

		System.out.println("Se llama " + persona.getNombre() + " y tiene " + persona.getEdad() + " añitos\n"
				+ "Su número de teléfono es: " + persona.getTelefono());
		
		System.out.println("----------------------------------------------------");
		
		//-----Pero me gusta más con el constructor porque cada vez son menos códigos
		Persona persona1 = new Persona("Juanita","632147898",19);
		System.out.println("Se llama " + persona1.getNombre() + " y tiene " + persona1.getEdad() + " añitos\n"
				+ "Su número de teléfono es: " + persona1.getTelefono());
		
		
		
	}

}
/*
 *- Crear clase persona 
 *- Crear variables las privadas edad, nombre y teléfono de
 * la clase Persona
 * 
 */

class Persona {
	private int edad;
	private String nombre;
	private String telefono;

	public Persona() {

	}
	
	public Persona(String nombre, String telefono, int edad) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.edad = edad;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
