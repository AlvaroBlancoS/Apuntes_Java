package model;

import java.time.LocalDate;

import services.AltoRango;

/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */
public class Director extends Empleado implements AltoRango {

	private String matriculaCoche = null;
	private boolean isCoche = false;
	private final double INCREMENTO = 100.0;
	private final double INCENTIVO_DIRECTOR = 1.10d;

	public Director(String dni, String nombre,String apellidos, double salario, LocalDate fecha_nacimiento, boolean isCoche, String matricula) {
		super(dni, nombre, apellidos, salario, fecha_nacimiento);
		this.isCoche = isCoche;
		this.matriculaCoche = matricula;
	}

	public Director() {
		super();
	}

	public boolean isCoche() {
		return isCoche;
	}

	public void setCoche(boolean isCoche) {
		this.isCoche = isCoche;
	}

	public String getMatriculaCoche() {
		return matriculaCoche;
	}

	public void setMatriculaCoche(String matriculaCoche) {
		this.matriculaCoche = matriculaCoche;
	}

	public double getIncremento() {
		return INCREMENTO;
	}

	public double getINCENTIVO_DIRECTOR() {
		return INCENTIVO_DIRECTOR;
	}

	@Override
	public Double calcularIncentivo() {
		double salarioEmplado = this.getSalario() + (this.getSalario() * INCENTIVO_DIRECTOR / 100) + this.INCREMENTO;
		this.setSalario(salarioEmplado);
		return this.getSalario();
	}

	@Override
	public String toString() {
		return super.toString() + "\nComo director/a tiene un incentivo de " + this.INCENTIVO_DIRECTOR
				+ " y un incremento de " + this.INCREMENTO + ". Por lo tanto, su salario actual es "
				+ calcularIncentivo()
				+ (this.isCoche() == true
						? ".\nAdemas, tiene un coche de la empresa y la matricula es " + this.matriculaCoche
						: ".");
	}

}
