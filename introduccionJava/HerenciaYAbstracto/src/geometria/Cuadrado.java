package geometria;

public class Cuadrado extends FiguraGeometrica {
	
	private  final int NUM_LADOS = 4;
	
	private double lado;

	@Override
	public double calcularArea() {
		double area = Math.pow(lado, NUM_LADOS);
		return area;
	}

	@Override
	public double calcularPerimetro() {
		double perimetro = lado *NUM_LADOS;
		return perimetro;
	}

	public double getLado() {
		return lado;
	}

	public void setLado(double lado) {
		this.lado = lado;
	}
	
	

}
