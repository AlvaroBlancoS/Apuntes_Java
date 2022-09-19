package Vehiculos2;

public class Main {

	public static void main(String[] args) {
		Motor motorGTI = new Motor("GTI",190,459.0,6);
		Vehiculo toyotaPrius = new Vehiculo("Ford","Mondeo",2.1,2010,false, 0, motorGTI);
	}

}
