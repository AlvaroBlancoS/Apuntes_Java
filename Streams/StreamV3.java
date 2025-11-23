
import java.util.List;


public class StreamV3 {

    public static void main(String[] args) {
        List<String> nombres = List.of("juan", "ana", "maria");

        List<String> mayus = nombres.stream()
                .map(String::toUpperCase)
                .toList();

        System.out.println(mayus); // [JUAN, ANA, MARIA]
    }
}
