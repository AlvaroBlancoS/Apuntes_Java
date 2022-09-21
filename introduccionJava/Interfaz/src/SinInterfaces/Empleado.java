package SinInterfaces;

public class Empleado {

	private String nombre;
	private int edad;
	private double salario;
	private boolean alta;

	public Empleado() {

	}

	public Empleado(String nombre, int edad, double salario, boolean alta) {
		this.nombre = nombre;
		this.edad = edad;
		this.salario = salario;
		this.alta = alta;
	}

	@Override
	public String toString() {
		String resultado = null;
		if (alta == true) {
			resultado = "\nNombe: " + nombre + "\nEdad: " + edad + "\nSalario: " + salario + "\n--Esta el alta--\n";
		} else {
			resultado = "Nombe: " + nombre + "\nEdad: " + edad + "\nSalario: " + salario + "\n--Esta el alta--\n";
		}

		return resultado;
	}
}
