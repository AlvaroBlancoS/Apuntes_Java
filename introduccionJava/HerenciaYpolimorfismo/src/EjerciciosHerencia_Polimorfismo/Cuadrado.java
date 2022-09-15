package EjerciciosHerencia_Polimorfismo;

public class Cuadrado extends Figura{
	



	@Override
	public double areaCirculoYcuadrado(double numero) {
		double area = numero * numero;
		return area;
	}

	@Override
	public double perimetroCirculoYcuadrado(double numero) {
		double perimetro = numero + numero + numero + numero;
		return perimetro;
	}
	
}
