package ColeccionQueue;

import java.util.LinkedList;
import java.util.Queue;

public class Carniceria {

	public static void main(String[] args) {
		Queue <String> colaPersonas = new LinkedList<String>();
		colaPersonas.add("Juan");
		colaPersonas.add("Ana");
		colaPersonas.add("Luis");
		System.out.println("Numero elementos "+colaPersonas.size());
		//Mostraria en pantalla "Numero elementos: 3"
	}

}
