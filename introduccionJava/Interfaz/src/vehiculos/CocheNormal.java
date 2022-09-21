package vehiculos;

public class CocheNormal extends Vehiculo {
	private int anio;
	private String lugarRestauracion;

	public CocheNormal(String color, String fabricante, String modelo, int velocidad, int anio,
			String lugarRestauracion) {
		super(color, fabricante, modelo, velocidad);
		this.anio = anio;
		this.lugarRestauracion = lugarRestauracion;
	}
	public CocheNormal() {
		
	}

	@Override
	public void acelerar(int cantidad) {
		super.acelerar(cantidad);
	}
	
	

}
