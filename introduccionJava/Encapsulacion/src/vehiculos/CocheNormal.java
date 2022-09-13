package vehiculos;

public class CocheNormal extends Vehiculo {
	
	private String tipoMotor;

	public CocheNormal() {
		
	}

	public CocheNormal(String color, String fabricante, String modelo, int velocidad, String tipoMotor) {
		super(color, fabricante, modelo, velocidad);
		this.tipoMotor = tipoMotor;
	}

	public String getTipoMotor() {
		return tipoMotor;
	}

	public void setTipoMotor(String tipoMotor) {
		this.tipoMotor = tipoMotor;
	}

	@Override
	public void acelerar(int cantidad) {
		super.acelerar(cantidad*2);
	}
	
	
	
	

}
