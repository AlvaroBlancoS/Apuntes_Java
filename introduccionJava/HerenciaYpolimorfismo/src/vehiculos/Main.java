package vehiculos;


public class Main {
	public static void main(String[] args) {
//		sinUtilizarConstructor();
		// utilizarConstructor();
		cochesHibridosYnoHibridos();
//		cochesHibridosYnoHibridos2();
	}

	public static void sinUtilizarConstructor() {
		Vehiculo coche1 = new Vehiculo();
		coche1.color = "Rojo";
		coche1.fabricante = "BMW";
		coche1.modelo = "XZ";
		coche1.velocidad = 0;
		System.out.println("---- Coches normales sin constructor ---");
		System.out.println("El coche es :" + coche1.fabricante + " " + coche1.modelo + " y el color es: " + coche1.color
				+ ". La velocidad actual es " + coche1.velocidad);
		coche1.acelerar(50);
		System.out.println("El coche" + " " + coche1.fabricante + " " + coche1.modelo
				+ " ha acelerado y la velocidad es " + coche1.velocidad + "\n");
	}

	public static void utilizarConstructor() {
		System.out.println("---- Coches normales con constructor ---");
		Vehiculo coche2 = new Vehiculo("blanco", "Toyota", "Airus", 0);
		System.out.println("El coche es :" + coche2.fabricante + " " + coche2.modelo + " y el color es: " + coche2.color
				+ ". La velocidad actual es " + coche2.velocidad);
		coche2.acelerar(100);
		System.out.println("El coche" + " " + coche2.fabricante + " " + coche2.modelo
				+ " ha acelerado y la velocidad es " + coche2.velocidad);

	}

	public static void cochesHibridosYnoHibridos() {
		System.out.println("\n---- Coches híbridos y no híbridos----");
		// No polimorfismo
		Vehiculo c = new Vehiculo("blanco", "Toyota", "Airus", 0);
		System.out.println("El coche es :" + c.fabricante + " " + c.modelo + " y el color es: " + c.color
				+ ". La velocidad actual es " + c.velocidad);
		c.acelerar(100);
		System.out.println("El coche" + " " + c.fabricante + " " + c.modelo + " ha acelerado y la velocidad es "
				+ c.velocidad + "\n");

		CocheHibrido h1 = new CocheHibrido("plateado", "Tesla", "X", 0, "Motor con placa solar");
		System.out.println("El coche es :" + h1.fabricante + " " + h1.modelo + ", el color es: " + h1.color
				+ " y el tipo de motor es " + h1.motorElectrico + ". La velocidad actual es " + h1.velocidad);
		h1.acelerar(50);
		System.out.println("El coche" + " " + h1.fabricante + " " + h1.modelo + " ha acelerado y la velocidad es "
				+ h1.velocidad + "\n");

		// Polimorfismo
		Vehiculo h2 = new CocheHibrido("negro", "Audi", "xa", 0, "Motor con agua");
		System.out.println("\n" + h2);

		Vehiculo[] confirmarVehiculo = { c, h1, h2 };

		for (int i = 0; i < confirmarVehiculo.length; i++) {
			if (esHibrido(confirmarVehiculo[i]) == true) {
				System.out.println(
						confirmarVehiculo[i].fabricante + " " + confirmarVehiculo[i].modelo + " es un coche hibrido");
			} else {
				System.out.println(
						confirmarVehiculo[i].fabricante + " " + confirmarVehiculo[i].modelo + " no es un coche hibrido");
			}
		}

	}

	public static boolean esHibrido(Vehiculo h) {
		if (h instanceof CocheHibrido) {
			return true;
		}
		return false;
	}
}
