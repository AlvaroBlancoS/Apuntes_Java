package primerEjemplo;

import Utilidad.Agregar;

public class Calculadora {

	public static void main(String[] args) {
		primeraPrueba();
	}

	public static void primeraPrueba() {
		if (suma(Agregar.leerEntero("Escribe el primer numero"),
				Agregar.leerEntero("Escribe el segundo número")) == 5) {
			
			Agregar.warnning("Es un cinco");
		} else {
			Agregar.error("No es un cinco");
			
		}

	}

	public static int suma(int a, int b) {

		return a + b;
	}

	public static int resta(int a, int b) {

		return a - b;
	}

}
