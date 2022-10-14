package geometria;

public class Circulo extends FiguraGeometrica {

	private final double PI = 3.14;
	private final double CUADRADO = 2;

	private double numero;

	@Override
	public double calcularArea() {
		double area = PI * Math.pow(this.numero, this.CUADRADO);
		return area;
	}

	@Override
	public double calcularPerimetro() {
		double perimetro = CUADRADO * PI * numero;
		return perimetro;
	}

	public double getNumero() {
		return numero;
	}

	public void setNumero(double numero) {
		this.numero = numero;
	}
	
	

}
