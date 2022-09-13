package EjerciciosDeFunciones;

public class SegundaParte {
	/*
	 * Crear una clase coche.
	 * 
	 * Dentro de la clase coche, una variable numérica que almacene el número de
	 * puertas que tiene.
	 * 
	 * Una función que incremente el número de puertas que tiene el coche.
	 * 
	 * Crear un objeto miCoche en el main y añadirle una puerta.
	 * 
	 * Mostrar el número de puertas que tiene el objeto.
	 */

	public static void main(String[] args) {
		Coche miCoche = new Coche();
		System.out.println("---Número de puertas---");
		System.out.println(miCoche.incrementarPuertas());
		System.out.println(miCoche.incrementarPuertas());
		System.out.println(miCoche.incrementarPuertas());
		System.out.println(miCoche.incrementarPuertas());
		System.out.println(miCoche.incrementarPuertas());

		// He adelantado demasiado pero no pude evitar, disculpas
//		int num = 5;
//		System.out.println("---Número de puertas---");
//		for (int i = 0; miCoche.incrementarPuertas() < num; i++) {
//				System.out.println(i + 1);
//		}

	}

}

class Coche {
	public int puertas;

	// Tomé la decisión de utilizar una función con retorno de int en vez de
	// utilizar void
	public int incrementarPuertas() {
		return puertas++;
	}
	
	public void otroIncrementoDePuertas() {
		System.out.println(puertas++);
	}

}
