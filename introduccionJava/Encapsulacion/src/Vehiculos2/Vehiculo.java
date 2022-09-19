package Vehiculos2;

public class Vehiculo {
	String fabricante;
	String modelo;
	double cc;
	int year;
	boolean sport;
	int speed;
	Motor motor;
	
	
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
	
}
