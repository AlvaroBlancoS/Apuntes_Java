
import java.util.List;


public class StreamV4 {

    public static void main(String[] args) {
        List<String> palabras = List.of("Hola", "Stream", "Java");

        List<Integer> longitudes = palabras.stream()
                .map(String::length)
                .toList();

        System.out.println(longitudes); // [4, 6, 4]

    }
}
