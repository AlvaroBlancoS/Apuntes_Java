package geometria;

public class Main {

	private final static double LADO_CUADRADO = 2.0;
	private final static double CIRCULO = 2.0;
	private final static double LADORECTANGULO1=2.0, LADORETACNGULO2=3.0;
	

	public static void main(String[] args) {
		Cuadrado cuadrado = new Cuadrado();
		
		cuadrado.setLado(LADO_CUADRADO);
		
		System.out.println("El area del cuadrado es: "+cuadrado.calcularArea());
		
		System.out.println("El perimetro del cuadrado es: "+cuadrado.calcularPerimetro());
		
		
		Circulo circulo = new Circulo();
		
		circulo.setNumero(CIRCULO);
		
		System.out.println("El area de circulo es: "+circulo.calcularArea());
		
		System.out.println("El perimetro de circulo es: "+circulo.calcularPerimetro());
		
		
		Rectangulo rectangulo = new Rectangulo();
		
		rectangulo.setNum1(LADORECTANGULO1);
		rectangulo.setNum2(LADORETACNGULO2);
		
		System.out.println("El area de rectangulo es: "+rectangulo.calcularArea());
		
		System.out.println("El perimetro de rectangulo es: "+rectangulo.calcularPerimetro());
		
		
	}

}
