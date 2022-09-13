package EjerciciosDeHerencia;

public class Main {
	/*
	 * Crea una clase Persona con las siguientes variables -La edad, el nombre y el
	 * teléfono.
	 * 
	 * Una vez creada la clase, crea una nueva clase Cliente que herede de persona,
	 * esta nueva clase tendrá la variable crédito solo para esa clase. Crea ahora
	 * un objeto de la clase Cliente que debe tener como propiedades la edad, el
	 * nombre y el crédito, tienes que darles valor y mostrarla por pantalla
	 * 
	 * Una vez hecho esto, haz lo mismo con la clase Trabajador que herede de
	 * Persona, con una variable salario que solo tenga la clase Trabajador.
	 * 
	 */

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.nombre = "Paco";
		cliente.telefono = "000321475";
		cliente.edad = 43;
		cliente.credito = "33377770000";
		Trabajador trabajador = new Trabajador();
		trabajador.nombre = "Don Jaime";
		trabajador.edad = 65;
		trabajador.telefono = "987000145";
		trabajador.salario = 12034.13;
		System.out.println("---CLIENTE---\n" + "Nombre:\t" + cliente.nombre + "\nEdad:\t" + cliente.edad + "\ntelf:\t"
				+ cliente.telefono + "\nCredito:\t" + cliente.credito+"\n");
		System.out.println("---TRABAJADOR---\n" + "Nombre:\t" + trabajador.nombre + "\nEdad:\t" + trabajador.edad
				+ "\ntelf:\t"+ trabajador.telefono + "\nSalario:\t" + trabajador.salario);

	}

}

class Persona {
	int edad;
	String nombre;
	String telefono;
}

class Cliente extends Persona {
	String credito;
}

class Trabajador extends Persona {
	double salario;
}
