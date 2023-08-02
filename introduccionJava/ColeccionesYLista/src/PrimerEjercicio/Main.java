package PrimerEjercicio;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		ArrayList<Persona> listaPersonas = new ArrayList<>();
		listaPersonas.add(new Persona("Alex", "Muñoz", "Velasco", "12.345.678-L", 25));
		listaPersonas.add(new Persona("Ana", "Gil", "Gil", "12.345.678-L", 55));
		listaPersonas.add(new Persona("Erik", "Muñoz", "Velasco", "12.345.678-L", 34));
		listaPersonas.add(new Persona("Sara", "Sanz", "Perez", "12.345.678-L", 48));

		Collections.sort(listaPersonas);

		for (Persona persona : listaPersonas) {
			System.out.println(persona.getNombre() + " " + persona.getPrimerApellido() + " "
					+ persona.getSegundoApellido() + " " + persona.getDni() + " " + persona.getEdad());
		}
	}

}
