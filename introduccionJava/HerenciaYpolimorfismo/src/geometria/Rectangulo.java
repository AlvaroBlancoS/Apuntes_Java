package geometria;

public class Rectangulo extends Figura {
	



	@Override
	public double areaRectangulo(double numero, double numero2) {
		double area = numero * numero2;
		return area;
	}

	@Override
	public double perimetroRectangulo(double numero, double numero2) {
		double perimetro = numero * 2.0 + numero2 * 2.0;
		return perimetro;
	}

}
