package interfazComparable;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
//			Persona p1 = new Persona(20, "Pedro");
//			Persona p2 = new Persona(10,"Maria");
		ArrayList<Persona> listaPersonasPrueba = new ArrayList<>();
		listaPersonasPrueba.add(new Persona(20, "Pedro"));
		listaPersonasPrueba.add(new Persona(10, "Maria"));
		listaPersonasPrueba.add(new Persona(50, "Ramon"));
		listaPersonasPrueba.add(new Persona(30, "Sofia"));

		// Muestra la pantalla que no está ordenado por edad
		for (Persona persona : listaPersonasPrueba) {
			System.out.println(persona.toString());
		}
		System.out.println("-----------------------");
		// Muestra la pantalla que si está ordenado por edad
		Collections.sort(listaPersonasPrueba);

		for (Persona persona : listaPersonasPrueba) {
			System.out.println(persona.toString());
		}
	}

}
