package ejerciciosArrays;

public class Ejercicio4 {

	public static void main(String[] args) {
		mayorYmenor(2, 10, 33, 6, 21, 30, 20);
	}
	
	public static void mayorYmenor(int num1, int num2, int num3, int num4, int num5, int num6, int num7) {
		int nums[] = { num1, num2, num3, num4, num5, num6, num7 };
		int menor = 0;
		int mayor = 0;
		menor = mayor =nums[0];
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > mayor) {
				mayor = nums[i];
			}

			if (nums[i] < menor) {
				menor = nums[i];
			}

		}

		System.out.println("El numero mayor es " + mayor + "\nEl numero menor es " + menor);

	}

}
