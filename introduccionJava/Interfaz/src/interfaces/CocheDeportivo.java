package interfaces;

import vehiculos.CocheHibrido;
import vehiculos.Vehiculo;

public class CocheDeportivo implements CocheService {

	@Override
	public Vehiculo crearCoche() {
		System.out.println("Creando coche de carreras...");
		return new CocheHibrido();
	}

	@Override
	public void destruirCoche(Vehiculo coche) {
		System.out.println("Destruyendo coche de carreras..");
	}

}
