package coleccionSet;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class CuentaCorriente {

	public static void main(String[] args) {
		Set<String>titulares = new LinkedHashSet<String>();
		System.out.println(titulares.add("Laura"));
		System.out.println(titulares.add("Miguel"));
		//Al no existir "Laura" y "Miguel", se insertan y devuelven true
		System.out.println(titulares.add("Laura"));
		System.out.println(titulares.add("Miguel"));
		//Al existir "Laura" y "Miguel", no se insertan y devuelven false
		System.out.println("Nnumero elementos: "+titulares.size());
		//Mostrará en pantalla "Numero elementos: 2"
	}	

}
