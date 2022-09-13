package funciones;

public class TiposVariables {
	// Estos son los atributos o variables globales
	static int numeroEntero=0;
	String nombre;
	static final String ejemplo = "no se puede cambiar";

	public static void main(String[] args) {
		// Estos son variables locales.
		String nombre;
		//Hemos utilizado la variable estático y hemos
		numeroEntero =1;
		
		System.out.println(ejemplo);
	}

	public void ejemplo() {
		int numeroEntero;
		String nombre;
	}

}
