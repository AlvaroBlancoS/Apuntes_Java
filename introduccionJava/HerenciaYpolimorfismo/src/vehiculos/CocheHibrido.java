package vehiculos;

public class CocheHibrido extends Vehiculo {

	String motorElectrico;

	public CocheHibrido(String color, String fabricante, String modelo, int velocidad, String motorElectrico) {
		super(color, fabricante, modelo, velocidad);
		this.motorElectrico= motorElectrico;
	}



	@Override
	public void acelerar(int cantidad) {
		// TODO Auto-generated method stub
		super.acelerar(cantidad*2);
	}



	@Override
	public String toString() {
		return "CocheHibrido [motorElectrico=" + motorElectrico + ", color=" + color + ", fabricante=" + fabricante
				+ ", modelo=" + modelo + ", velocidad=" + velocidad + "]";
	}
	
	
}
