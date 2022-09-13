package vehiculos;

public class Vehiculo {
	private String color;
	private String fabricante;
	private String modelo;
	private int velocidad = 0;
	
	public Vehiculo() {

	}

	public Vehiculo(String color, String fabricante, String modelo, int velocidad) {
		this.color = color;
		this.fabricante = fabricante;
		this.modelo = modelo;
		this.velocidad = velocidad;
	}

	public void acelerar(int cantidad) {
		if (cantidad > 0 && cantidad <= 120) {
			this.velocidad += cantidad;
		}

	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	
}
