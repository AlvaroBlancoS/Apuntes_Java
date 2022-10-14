package ejercicios;

public class Main {

	public static void main(String[] args) {
		Operaciones<Integer, Integer> opI = new Operaciones<Integer, Integer>();

		System.out.println("La suma es " + opI.suma(5, 2));
		System.out.println("La resta es " + opI.resta(5,2));
		System.out.println("La multiplicacion es " + opI.producto(5,2));
		System.out.println("La division es " + opI.division(5,2));

		Operaciones<Double, Double> opD = new Operaciones<Double, Double>();
		System.out.println("La suma es " + opD.suma(5.0,2.0));
		System.out.println("La resta es " + opD.resta(5.0,2.0));
		System.out.println("La multiplicacion es " + opD.producto(5.0,2.0));
		System.out.println("La division es " + opD.division(5.0,2.0));
	}

}
