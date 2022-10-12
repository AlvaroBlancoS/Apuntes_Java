package HerenciaEmpresa;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Empleado {
	private GregorianCalendar calendario = new GregorianCalendar();
	private String nombre;
	private double sueldo;
	private Date altoContrato;

	public Empleado(String nombre, double sueldo, int anio, int mes, int dia) {
		this.nombre = nombre;
		this.sueldo = sueldo;
		calendario.set(anio, mes - 1, dia);
		altoContrato = calendario.getTime();
	}

	// ----Los metodos getters
	public String dameNombre() {
		return nombre;
	}

	public double dameSueldo() {
		return sueldo;
	}

	public Date dameAltoContrato() {
		return altoContrato;
	}

	// ----Los metodos setters
	public void subeSueldo(double porcentaje) {
		double aumento = this.sueldo * porcentaje / 100;
		this.sueldo += aumento;
	}

	public void setAltoContrato(Date altoContrato) {
		this.altoContrato = altoContrato;
	}

}

class Jefatura extends Empleado {

	public Jefatura(String nombre, double sueldo, int anio, int mes, int dia) {
		super(nombre, sueldo, anio, mes, dia);
	}

	private double incentivo;

	public void estableceIncentivo(double b) {
		incentivo = b;
	}

	// Este método está sobreescrito por clase padre empleado
	// Por lo tanto, la clase hija (jefatura) tiene la herencia de la clase
	// padre(empleado)
	@Override
	public double dameSueldo() {
		// Devuelve el sueldo de un empleado cualquiera con el método de la clase padre
		double sueldoJefe = super.dameSueldo();
		// Devuelve el sueldo más incentivo de la clase hija (jefatura)
		return sueldoJefe + incentivo;
	}

}