package EjerciciosHerencia_Polimorfismo;

public class Circulo extends Figura {

	private double numero;

	public Circulo(double numero) {
		this.numero = numero;
	}

	public double getNumero() {
		return numero;
	}

	@Override
	public double areaCirculoYcuadrado(double numero) {
		this.numero = numero;
		double area = 3.14 * Math.pow(numero, 2);
		return area;
	}

	@Override
	public double perimetroCirculoYcuadrado(double numero) {
		double perimetro = 2 * 3.14 * numero;
		return perimetro;
	}

}
