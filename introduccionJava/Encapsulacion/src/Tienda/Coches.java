package Tienda;

import javax.swing.JOptionPane;

public class Coches {

	public static void main(String[] args) {
		Coche miCoche = new Coche();

		miCoche.establece_color(JOptionPane.showInputDialog("Introduce color"));

		System.out.println(miCoche.dime_datos_generales());

		System.out.println(miCoche.dime_color());
		
		// Si tiene asientos de cuero
		miCoche.configura_asientos(JOptionPane.showInputDialog("¿Tiene asiento de cuero?"));

		// averiguar si tiene asientos de cuero
		System.out.println(miCoche.dime_asientos());

		miCoche.configura_climatizador(JOptionPane.showInputDialog("¿Tiene climatizador?"));

		System.out.println(miCoche.dime_climatizador());

		// Es un metodo que contiene getter + setter
		System.out.println(miCoche.dime_peso_cohe());

		System.out.println("El precio final del coche es: " + miCoche.precio_coche());
	}

}

class Coche {
	private String color;
	private int ruedas;
	private int largo;
	private int ancho;
	private int motor;
	private int peso_plataforma;
	private int peso_total;
	private boolean asientos_cuero, climatizador;

	public Coche() {
		ruedas = 4;
		largo = 200;
		ancho = 1600;
		motor = 1600;
		peso_plataforma = 500;

	}

	public String dime_datos_generales() {

		return "La plataforma del vehiculo tiene " + ruedas + " ruedas " + ". Mide " + largo / 100
				+ " metros con un ancho de " + ancho + " cm\ny un peso de plataforma de " + peso_plataforma + " kg";
	}

	public void establece_color(String color_coche) {
		color = color_coche;
	}

	public String dime_color() {
		return "El color del coche es " + color;
	}

	public void configura_asientos(String asientos_cuero) {
		if (asientos_cuero.equalsIgnoreCase("si")) {
			this.asientos_cuero = true;
		} else {
			this.asientos_cuero = false;
		}
	}

	public String dime_asientos() {
		if (asientos_cuero == true) {
			return "El coche tiene asientos de cuero";
		} else {
			return "El coche tiene asientos de serie";
		}
	}

	public void configura_climatizador(String climatizador) {
		if (climatizador.equalsIgnoreCase("si")) {
			this.climatizador = true;
		} else {
			this.climatizador = false;
		}
	}

	public String dime_climatizador() {
		if (climatizador == true) {
			return "El coche incorpora climatizador";
		} else {
			return "El coche lleva aire acondicionado";
		}
	}

	// No es recomendable para programar
	public String dime_peso_cohe() {// Getter + setter
		int peso_carroceria = 500;

		this.peso_total = this.peso_plataforma + peso_carroceria;

		if (asientos_cuero == true) {
			this.peso_total = this.peso_total + 50;
		}
		if (climatizador == true) {
			this.peso_total = this.peso_total + 20;
		}

		return "El peso del coche es : " + peso_total;
	}

	public int precio_coche() {
		int precio_final = 10000;
		if (asientos_cuero == true) {
			precio_final += 2000;
		}
		if (climatizador == true) {
			precio_final += 1500;
		}

		return precio_final;
	}

}
