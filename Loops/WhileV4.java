import java.util.Scanner;

public class WhileV4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numero;
        int i =5;

        System.out.print("Introduce un número mayor que "+i+": ");
        numero = sc.nextInt();

        while (numero <= i) {
            System.out.println("Número inválido, intenta de nuevo:");
            numero = sc.nextInt();
        }

        System.out.println("¡Número aceptado: " + numero + "!");
        sc.close();
    }
}
