package segundoEjemplo;

import java.util.ArrayList;
import java.util.List;

public class Datos {
	public static void main(String[] args) {
		for (Persona ver : lista()) {
			System.out.println(ver.toString());
		}
	}

	public static List<Persona> lista() {
		ArrayList<Persona> personas = new ArrayList<>();
		long autoIncrement = 1;
		personas.add(new Persona(autoIncrement++,"Alvarito", "Blanco Sangines", 18));
		personas.add(new Persona(autoIncrement++,"Violeta", "Chuquimarca cuenca", 18));
		return personas;
	}

}
