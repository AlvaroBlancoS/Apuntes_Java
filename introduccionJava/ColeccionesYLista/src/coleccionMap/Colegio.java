package coleccionMap;

import java.util.HashMap;
import java.util.Map;

public class Colegio {

	public static void main(String[] args) {
		Map <Integer,String> listaAlumnos = new HashMap <>();		
		listaAlumnos.put(1,"Juan");
		listaAlumnos.put(2,"Juan");
		listaAlumnos.put(3,"Laura");
		String valor = listaAlumnos.get(2);
		System.out.println("Alumno asociado a 2 es " +valor);
		//Mostraría en pantalla “Alumno asociado a 2 es Juan”

		
	}

}
