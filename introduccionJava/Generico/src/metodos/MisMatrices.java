package metodos;

public class MisMatrices {

	public static void main(String[] args) {
		// Creamos un array con nombres
		String nombres[] = { "Pablo", "Rebeca", "Ana" };
		// Creamos un array con números
		Integer numeros[] = { 7, 9, 2, 6 };
		// Utilizamos el mismo método con un array numérico y con otro array de texto
		System.out.println(MisMatrices.getElementos(nombres));
		System.out.println(MisMatrices.getElementos(numeros));

	}

	public static <T> String getElementos(T[] a) {
		return "El array tiene " + a.length + " elementos.";

	}
}
