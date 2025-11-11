public class ConditionSwitchV2 {
        public static void main(String[] args) {
        int dia = 1;

        switch (dia) {
            case 1 -> System.out.println("Lunes");
            case 2 -> System.out.println("Martes");
            case 3 -> System.out.println("Miércoles");
            default -> System.out.println("Día no válido");
        }
    }
}
