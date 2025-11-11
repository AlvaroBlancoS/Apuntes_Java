public class LoopControl {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            if (i == 3)
                continue; // salta el 3
            if (i == 5)
                break; // sale cuando i es 5
            System.out.println(i);
        }
    }
}
