public class WhileV2 {
    public static void main(String[] args) {
        String[] frutas = { "Manzana", "Banana", "Naranja" };

        int i = 0;

        while (i<frutas.length) {
            System.out.println((i+1)+"-"+frutas[i]);
            i++;
        }
    }
}
