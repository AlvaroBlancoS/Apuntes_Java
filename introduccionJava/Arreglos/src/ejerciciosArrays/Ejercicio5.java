package ejerciciosArrays;

public class Ejercicio5 {

	public static void main(String[] args) {
		numerosAleatorios(5);

	}
	
	public static void numerosAleatorios(int numeros) {
		int nums[] = new int[numeros];
		int numeroMenor = 0, numeroMayor = 0;
		int sum = 0;
		numeroMenor = numeroMayor = nums[0];
		for (int i = 0; i < nums.length; i++) {
			int aleatorio = (int) (Math.random() * 100 + 1);
			nums[i] = aleatorio;

			System.out.println(nums[i]);

			if (nums[i] > numeroMayor) {
				numeroMayor = nums[i];

			}

			if (nums[i] < numeroMenor) {
				numeroMenor = nums[i];
			}

			sum += nums[i];

		}
		int media = sum / nums.length;
		System.out.println("El numero menor es: " + numeroMenor + " y el numero mayor es: " + numeroMayor
				+ "\nLa suma es: " + sum + " y la media es: " + media);
	}

}
