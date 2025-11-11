public class ForV3 {
    public static void main(String[] args) {
        switch (2) {
            case 1 -> {
                numeroPar();
            }
            case 2 -> {
                numeroImpar();
            }

            default -> {
                System.err.println("Opción equivocado");
            }
        }
    }

    public static void numeroPar() {
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }

    public static void numeroImpar() {
        for (int i = 1; i <= 10; i++) {
            if (i % 2 != 0) {
                System.out.println(i);
            }
        }
    }
}
