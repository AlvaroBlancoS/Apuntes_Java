package interfaces;

public class DemoIFGen {
	
	final static int num = 4;

	public static void main(String[] args) {
		Integer x[] = {1,2,3};
		MiClase<Integer> ob = new MiClase<Integer>(x);
		if (ob.contenido(num)) {
			System.out.println(num+" esta en el array");
		} else {
			System.out.println(num+" no esta en el array");
		}
	}

}

class MiClase<T> implements Contenedor<T> {
	private T[] array;// Array del tipo genérico

	//Simplemente para almacenar un array
	MiClase(T[] o) {
		array = o;
	}

	// Implementación del método contenido() de la interfaz Contenedor
	@Override
	public boolean contenido(T obj) {
		for (T x : array) {
			if (x.equals(obj)) {
				return true;
			}
		}
		return false;
	}

}

interface Contenedor<T> {
	public abstract boolean contenido(T obj);
}
