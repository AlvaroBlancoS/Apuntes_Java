package interfazComparator;

import java.util.Comparator;

public class OrdenarPorEdad implements Comparator <Persona>{

	//Ordeanr por edad
	@Override
	public int compare(Persona p1, Persona p2) {
		return p2.getEdad()-p1.getEdad();
	}
	
}
