package Vehiculos2;

import HerenciaVehiculos2.Motor;
import HerenciaVehiculos2.Vehiculo;

public class Main {
	public static void main(String[] args) {
		// Esto es una clase
		Motor motorGTI = new Motor("GTI", 190, 459.0, 6);
		// Puedes usar la herencia
		Motocicleta kawasakiNinja = new Motocicleta();
		Coche bmw = new Coche();
		Camion mercedes = new Camion();

		// O polimorfismo
		Vehiculo vehiculo;

		vehiculo = new Motocicleta("Ford", "Mondeo", 2.1, 2010, false, 0, motorGTI, false);

		vehiculo.acelerar(50);

		vehiculo = new Coche();

		vehiculo.acelerar(50);

		vehiculo = new Camion();

		vehiculo.acelerar(50);

	}
}
