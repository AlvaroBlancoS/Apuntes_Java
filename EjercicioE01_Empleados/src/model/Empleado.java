package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */
public class Empleado {
	private String nombre;
	private String apellidos;
	private double salario;
	private LocalDate fecha_nacimiento;

	public Empleado(String nombre, String apellidos, double salario, LocalDate fecha_nacimiento) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.salario = salario;
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public Empleado() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(fecha_nacimiento, nombre, salario);
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + ", apellidos: " + apellidos + ", salario: " + salario + ", fecha de nacimiento: "
				+ fecha_nacimiento;
	}

}
