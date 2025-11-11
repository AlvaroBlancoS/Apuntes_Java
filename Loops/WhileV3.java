public class WhileV3 {
    static int i = 1;

    public static void main(String[] args) {
        switch (1) {
            case 1 -> numeroPar();
            case 2 -> numeroImpar();
            default -> System.err.println("Opción inválido");
        }

    }

    public static void numeroPar() {
        while (i <= 10) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
            i++;
        }

    }

    public static void numeroImpar() {
        while (i <= 10) {
            if (i % 2 != 0) {
                System.out.println(i);
            }
            i++;
        }
    }
}
