public class DoWhileV2 {
    public static void main(String[] args) {
        String[] frutas = { "Manzana", "Banana", "Naranja" };
        int i = 0;
        do {      
            System.out.println((i+1)+"-"+frutas[i]);
            i++;
        } while (i<frutas.length);
    }
}
