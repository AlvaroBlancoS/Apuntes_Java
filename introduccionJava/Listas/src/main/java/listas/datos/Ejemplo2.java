package listas.datos;

import java.util.ArrayList;
import java.util.List;

import listas.model.Agenda;

public class Ejemplo2 {
	/**
	 * 
	 * @return Con el metodo de arraylist he creado una lista de personas para
	 *         devolver el resultado.
	 */
	public ArrayList<Agenda> listaPersonas() {
		ArrayList<Agenda> personas = new ArrayList<Agenda>();
		personas.add(new Agenda("Paco", "Lucia", "soyPaquito@gmail.com"));
		personas.add(new Agenda("Paco", "Umbral", "elPaquito@gmail.com"));
		personas.add(new Agenda("Laura", "Gutierrez Martinez", "laurita@gmail.com"));
		personas.add(new Agenda("Patxi", "Arraez Lapierre", "elPatxiMolon@gmail.com"));
		return personas;
	}


}
