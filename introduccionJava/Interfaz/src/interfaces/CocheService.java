package interfaces;
import vehiculos.Vehiculo;

public interface CocheService {
	
	public Vehiculo crearCoche();
	
	public void destruirCoche(Vehiculo coche);
}
