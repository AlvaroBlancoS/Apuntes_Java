package EjerciciosHerencia_Polimorfismo;

import java.util.Scanner;

public class Main {
	Figura of;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int opcion = 0;
		int numero = 0, numero2 = 0;
		do {
			System.out.println("1- Circulo\n2- Cuadrado\n3- Rectangulo\n0- salir");
			opcion = in.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Escribe un numero");
				numero = in.nextInt();
				System.out.println(circulo(numero));
				break;
			case 2:
				System.out.println("Escribe un numero");
				numero = in.nextInt();
				System.out.println(cuadrado(numero));
				break;
			case 3:
				System.out.println("Escribe un numero");
				numero = in.nextInt();
				System.out.println("Escribe otro numero");
				numero2 = in.nextInt();
				System.out.println(rectangulo(numero, numero2));
				break;
			case 0:
				System.out.println("Hasta pronto!!!");
				break;
			default:
				System.err.println(opcion + " no existe");
			}

		} while (opcion != 0);

	}

	private static String circulo(double numero) {
		Figura of = new Circulo(numero);
		double resultadoArea = of.areaCirculoYcuadrado(numero);
		double resultadoPerimetro = of.perimetroCirculoYcuadrado(numero);
		return "Area " + resultadoArea + "\nPerimetro: " + resultadoPerimetro;
	}

	private static String cuadrado(double numero) {
		Figura of = new Cuadrado();
		double resultadoArea = of.areaCirculoYcuadrado(numero);
		double resultadoPerimetro = of.perimetroCirculoYcuadrado(numero);
		return "Area " + resultadoArea + "\nPerimetro: " + resultadoPerimetro;
	}

	private static String rectangulo(double numero, double numero2) {
		Figura of = new Rectangulo();
		double resultadoArea = of.areaRectangulo(numero, numero2);
		double resultadoPerimetro = of.areaRectangulo(numero, numero2);
		return "Area " + resultadoArea + "\nPerimetro: " + resultadoPerimetro;
	}

}
