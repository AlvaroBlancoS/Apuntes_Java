package empresas;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Empleados {
	public static void main(String[] args) {
		Empleado empleado1 = new Empleado("Paco", 85000, 1990, 12, 17);
		Empleado empleado2 = new Empleado("Ana Lopez", 95000, 1995, 06, 02);
		Empleado empleado3 = new Empleado("Maria Martin", 105000, 2002, 03, 15);
		Empleado[] misEmpleados = { empleado1, empleado2, empleado3 };
		ArrayList<Empleado> leerEmpleados = new ArrayList<>();

		for (int i = 0; i < misEmpleados.length; i++) {
			misEmpleados[i].subeSueldo(5);
			leerEmpleados.add(misEmpleados[i]);

		}

		for (Empleado e : leerEmpleados) {
			System.out.println("Nombre " + e.dameNombre() + " Sueldo: " + e.dameSueldo() + " Fecha de alta: "
					+ e.dameAltoContrato());

		}

	}

}

class Empleado {
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
