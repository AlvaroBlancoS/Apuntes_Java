package Vehiculos2;

import HerenciaVehiculos2.Vehiculo;

public class Main {
	public static void main(String[] args) {

		// Esto es una herencia
		Motocicleta kawasakiNinja = new Motocicleta();
		Coche bmw = new Coche();
		Camion mercedes = new Camion();

		// Esto es polimorfismo
		Vehiculo vehiculo;

		vehiculo = new Motocicleta();

		vehiculo.acelerar(50);

		vehiculo = new Coche();
		
		vehiculo.acelerar(50);
		
		vehiculo = new Camion();

		vehiculo.acelerar(50);

	}
}
