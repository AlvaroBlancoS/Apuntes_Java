package ejercicios;

public class Operaciones<A extends Number, B extends Number> {
	double suma(A num1, B num2) {
		return num1.doubleValue() + num2.doubleValue();
	}

	double resta(A num1, B num2) {
		return num1.doubleValue() - num2.doubleValue();
	}

	double producto(A num1, B num2) {
		return num1.doubleValue() * num2.doubleValue();
	}

	double division(A num1, B num2) {
		return num1.doubleValue() / num2.doubleValue();
	}
}
