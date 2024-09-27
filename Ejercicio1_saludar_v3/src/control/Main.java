package control;

import util.Agregar;

/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */
public class Main {

	public static void main(String[] args) {
		String nombre = Agregar.leerString("Como te llamas?");
		System.out.println("Hola " + nombre);
	}

}
