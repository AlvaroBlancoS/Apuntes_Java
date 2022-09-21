package interfaces;

import vehiculos.CocheNormal;
import vehiculos.Vehiculo;

public class CocheClassic implements CocheService {

	@Override
	public Vehiculo crearCoche() {
		System.out.println("Creando coche clasico");
		return new CocheNormal();
	}

	@Override
	public void destruirCoche(Vehiculo coche) {
		System.out.println("Destruyendo coche clasico...");
	}

}
