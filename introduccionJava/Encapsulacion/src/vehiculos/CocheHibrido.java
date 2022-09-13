package vehiculos;

public class CocheHibrido extends Vehiculo {
	private String motorElectrico;

	public CocheHibrido() {

	}

	public CocheHibrido(String color, String fabricante, String modelo, int velocidad, String motorElectrico) {
		super(color, fabricante, modelo, velocidad);
		this.motorElectrico = motorElectrico;
	}

	public String getMotorElectrico() {
		return motorElectrico;
	}

	public void setMotorElectrico(String motorElectrico) {
		this.motorElectrico = motorElectrico;
	}

}
