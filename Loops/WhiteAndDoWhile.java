import java.util.Scanner;

public class WhiteAndDoWhile {
    final static String password = "java123";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Introduce la contraseña: ");
        switch (2) {
            case 1 -> {
                opcionWhile(sc.nextLine());
            }
            case 2 -> {
                opcionDoWhile(sc.nextLine());
            }
            default -> System.err.println("Opcion equivocado");
        }
       

    }

    public static void opcionWhile(String intento) {
        while (!intento.equals(password)) {
            System.out.println("Contraseña incorrecta, intenta de nuevo:");
            intento = sc.nextLine();
        }
        System.out.println("¡Acceso permitido!");
        sc.close();
    }

    public static void opcionDoWhile(String intento) {
        int i = 0;
        do {
            if (!intento.equals(password)) {
                i++;
                System.out.println(i+"- Contraseña incorrecta, intenta de nuevo:");
                intento = sc.nextLine();
                
                if (i==5) {
                    break;
                }
            }

        } while (!intento.equals(password));



        if (i==5) {
            System.out.println("Has agotado los 5 intentos. Vuelve más tarde.");
        }else{
            System.out.println("¡Acceso permitido!");
        }
        sc.close();
    }
}
