package model;

import java.time.LocalDate;

import services.AltoRango;
/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */
public class Gerente extends Empleado implements AltoRango {
	private String encargoDep;
	private final double INCENTIVO_GERENTE = 1.05d;

	public Gerente(String nombre, String apellidos, double salario, LocalDate fecha_nacimiento, String encargadoDep) {
		super(nombre, apellidos, salario, fecha_nacimiento);
		this.encargoDep = encargadoDep;
	}

	public Gerente() {
	}

	public String getEncargoDep() {
		return encargoDep;
	}

	public void setEncargoDep(String encargoDep) {
		this.encargoDep = encargoDep;
	}

	public double getINCENTIVO_GERENTE() {
		return INCENTIVO_GERENTE;
	}

	@Override
	public Double calcularIncentivo() {
		double salarioEmplado = this.getSalario() + (this.getSalario() * INCENTIVO_GERENTE / 100);
		this.setSalario(salarioEmplado);
		return this.getSalario();
	}

	@Override
	public String toString() {
		return super.toString() + " , encargo de departamento: " + this.encargoDep
				+ ". Como gerente tiene incentivo de " + this.INCENTIVO_GERENTE + " y su salario actual es "
				+ calcularIncentivo();
	}

}
