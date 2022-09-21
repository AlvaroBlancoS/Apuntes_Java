package vehiculos;



public class Vehiculo {

	String color;
	String fabricante;
	String modelo;
	int velocidad = 0;

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
	
	
}
