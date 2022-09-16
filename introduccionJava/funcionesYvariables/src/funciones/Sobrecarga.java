package funciones;

public class Sobrecarga {
	
	/*
	 * Sobrecarga permite duplicar un metodo siempre y cuando tenga diferente numero/tipo parametros.
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int suma(int numero1, int numero2) {
		return numero1+numero2;
	}
	
	public static int suma(int numero1, int numero2, int numero3) {
		return numero1+numero2;
	}
	public static double suma(double numero1, double numero2) {
		return numero1+numero2;
	}

}
