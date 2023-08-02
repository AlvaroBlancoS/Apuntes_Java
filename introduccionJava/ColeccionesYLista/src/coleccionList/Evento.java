package coleccionList;

import java.util.ArrayList;
import java.util.List;

public class Evento {

	public static void main(String[] args) {
		List<String> listaAsistentes = new ArrayList<>();
		listaAsistentes.add("Pedro");
		listaAsistentes.add("Maria");
		listaAsistentes.add("Antonio");
		listaAsistentes.add("Maria");
		//Los 4 elementos se dan de alta a pesar de que se repite "Maria"
		System.out.println("Numero elementos: "+listaAsistentes.size());
		//Mostraría en pantalla "Numero elementos: 4"
		
		for (String string : listaAsistentes) {
			System.out.println(string);
		}
	}

}
