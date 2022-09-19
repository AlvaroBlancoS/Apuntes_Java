package HerenciaVehiculos2;

public class Vehiculo {
	protected String fabricante;
	protected String modelo;
	protected double cc;
	protected int year;
	protected boolean sport;
	protected int speed;
	protected Motor motor;

	public Vehiculo() {

	}

	public Vehiculo(String fabricante, String modelo, double cc, int year, boolean sport, int speed, Motor motor) {
		super();
		this.fabricante = fabricante;
		this.modelo = modelo;
		this.cc = cc;
		this.year = year;
		this.sport = sport;
		this.speed = speed;
		this.motor = motor;
	}

	public void acelerar(int cantidad) {
		if (cantidad > 0 && cantidad <= 120) {
			this.speed += cantidad;
		}

	}

}
