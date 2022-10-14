package geometria;

public class Rectangulo extends FiguraGeometrica {
	private final double CUADRADO = 2.0;
	private double num1, num2;

	@Override
	public double calcularArea() {
		double area = num1 * num2;
		return area;
	}

	@Override
	public double calcularPerimetro() {
		double perimetro = num1 * CUADRADO + num2 * CUADRADO;
		return perimetro;
	}

	public void setNum1(double num1) {
		this.num1 = num1;
	}

	public void setNum2(double num2) {
		this.num2 = num2;
	}
	
	

}
