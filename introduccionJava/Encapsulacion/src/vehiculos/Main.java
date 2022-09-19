package vehiculos;

import java.util.Scanner;

public class Main {
	static Vehiculo cocheNormal1 = new CocheNormal();
	static Vehiculo cocheHibrido2 = new CocheHibrido();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int opcion = 0;
		String color, fabricante, modelo;
		int velocidad;
		do {
			System.out.println("1-Registrar coche normal\n2-Registrar coche hibrido\n0-Salir");
			opcion = in.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Color de coche");
				color = in.next();
				System.out.println("fabricante de coche");
				fabricante = in.next();
				System.out.println("modelo de coche");
				modelo = in.next();
				System.out.println("velocidad de coche");
				velocidad = in.nextInt();
				registrarCocheNormal(color, fabricante, modelo, velocidad);
				System.out.println(mostrarCocheNormal());
				break;
			case 2:
				System.out.println("Color de coche");
				color = in.next();
				System.out.println("fabricante de coche");
				fabricante = in.next();
				System.out.println("modelo de coche");
				modelo = in.next();
				System.out.println("velocidad de coche");
				velocidad = in.nextInt();
				registrarCocheHibrido(color, fabricante, modelo, velocidad);
				System.out.println(mostrarCocheHibrido());

				break;

			default:
				System.out.println("Hasta pronto!!!");
			}
		} while (opcion != 0);
	}

	private static void registrarCocheNormal(String color, String fabricante, String modelo, int velocidad) {
		cocheNormal1.setColor(color);
		cocheNormal1.setFabricante(fabricante);
		cocheNormal1.setModelo(modelo);
		cocheNormal1.setVelocidad(velocidad);

	}

	private static String mostrarCocheNormal() {

		return "Modelo: " + cocheNormal1.getModelo() + " Fabricado en: " + cocheNormal1.getFabricante()
				+ "\nEl color es: " + cocheNormal1.getColor() + "y la velocidad de coche es: "
				+ cocheNormal1.getVelocidad();
	}

	private static void registrarCocheHibrido(String color, String fabricante, String modelo, int velocidad) {
		cocheHibrido2.setColor(color);
		cocheHibrido2.setFabricante(fabricante);
		cocheHibrido2.setModelo(modelo);
		cocheHibrido2.setVelocidad(velocidad);

	}

	private static String mostrarCocheHibrido() {

		return "Modelo: " + cocheHibrido2.getModelo() + " Fabricado en: " + cocheHibrido2.getFabricante()
				+ "\nEl color es: " + cocheHibrido2.getColor() + "y la velocidad de coche es: "
				+ cocheHibrido2.getVelocidad();
	}
}

