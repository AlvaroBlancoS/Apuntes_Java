package interfazComparator;

import java.util.ArrayList;
import java.util.Collections;

public class ListaPersonas {

	public static void main(String[] args) {
		ArrayList<Persona> listaPersonas = new ArrayList<>();
		listaPersonas.add(new Persona(20, "Pedro"));
		listaPersonas.add(new Persona(10, "Maria"));
		listaPersonas.add(new Persona(50, "Ramon"));
		listaPersonas.add(new Persona(30, "Sofia"));

		Collections.sort(listaPersonas, new OrdenarPorEdad());
		for (Persona persona : listaPersonas) {
			System.out.println(persona.getEdad() + " " + persona.getNombre());
		}

	}

}
