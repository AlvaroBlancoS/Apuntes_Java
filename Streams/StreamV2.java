
import java.util.List;

public class StreamV2 {

    public static void main(String[] args) {
        List<Integer> numeros = List.of(1, 2, 3, 4, 5);

        List<Integer> cuadrados = numeros.stream()
                .map(n -> n * n)
                .toList();

        System.out.println(cuadrados);  // [1, 4, 9, 16, 25]
        
    }
}
