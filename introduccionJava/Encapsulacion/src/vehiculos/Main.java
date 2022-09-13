package vehiculos;

public class Main {
	Vehiculo cocheNormal1 = new CocheNormal();
	Vehiculo cocheHibrido2 = new CocheHibrido();

	public static void main(String[] args) {

	}
	
	
	public void registrarCocheNormal(String color, String fabricante, String modelo, int velocidad, String tipoMotor) {
		cocheNormal1.setColor(color);
		cocheNormal1.setFabricante(fabricante);
		cocheNormal1.setModelo(modelo);
		cocheNormal1.setVelocidad(velocidad);
		
	}
}
