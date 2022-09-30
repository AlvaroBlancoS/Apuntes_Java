package ejerciciosArrays;

import java.util.Vector;

public class Ejercicio8 {
	public static void main(String[] args) {
		Vector<Integer> vector = new Vector<Integer>();
		vector.add(1);
		vector.add(2);
		vector.add(3);
		vector.add(4);
		vector.add(5);

		for (int i = 0; i < vector.size(); i++) {
			if (vector.get(i)%2==0) {
				vector.remove(i);
				continue;
			}
			System.out.println("Número de posicion: " + i + " y el resultado es: " + vector.get(i));
		}
	}
}
