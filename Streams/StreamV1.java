import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamV1 {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
        
        List<Integer> cuadradosPares = numeros.stream()
                .filter(n -> n % 2 == 0) // filtrar pares
                .map(n -> n * n) // elevar al cuadrado
                .collect(Collectors.toList()); // recolectar en lista

        System.out.println(cuadradosPares); // [4, 16, 36]
    }
}
