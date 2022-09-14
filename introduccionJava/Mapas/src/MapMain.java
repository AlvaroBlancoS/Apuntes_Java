import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

public class MapMain {

	public static void main(String[] args) {
//		ejemploDeHasMap2();
//		ejemploDeLinkedHashMap();
		ejemploTreeMap(5);
	}

	public static void ejemploDeHasMap() {
		HashMap<Integer, String> hashMap = new HashMap<>();
		hashMap.put(1, "1");
		hashMap.put(2, "2");
		hashMap.put(3, "3");
		hashMap.put(4, "4");
		hashMap.put(5, "5");
		System.out.println(hashMap.values());
	}

	public static void ejemploDeHasMap2() {
		HashMap<String, String> personas = new HashMap<>();
		personas.put("45464541w", "Nombre apellido 1");
		personas.put("45464512w", "Nombre apellido 2");
		personas.put("22412541d", "Nombre apellido 2");

//		System.out.println(personas);
		System.out.println("----Muestra las claves----");
		for (String key : personas.keySet()) {
			System.out.println(key);
		}
		System.out.println("----Muestra los valores----");
		for (String value : personas.values()) {
			System.out.println(value);
		}
		System.out.println("----Muestra todos las claves y los valores----");
		for (Entry<String, String> entry : personas.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

	public static void ejemploDeLinkedHashMap() {
		LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put(1, "Paco");
		linkedHashMap.put(2, "Ana");
		linkedHashMap.put(3, "Mario");
		linkedHashMap.put(4, "Josefa");
		linkedHashMap.put(5, "Antonio");
		for (Entry<Integer, String> entry : linkedHashMap.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

	public static void ejemploTreeMap(int nota) {
		TreeMap<Integer, String> puntuaciones = new TreeMap<>();
		puntuaciones.put(8, "Notable alto");
		puntuaciones.put(5, "Aprobado");
		puntuaciones.put(10, "Matricula H.");
		puntuaciones.put(6, "Bien");
		puntuaciones.put(9, "Sobresaliente");
		puntuaciones.put(7, "Notable");
		for (Entry<Integer, String> entry : puntuaciones.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		
		
		System.out.println(puntuaciones.values());
		System.out.println("Por debajo de " + puntuaciones.firstKey() + " es suspenso");
		
		
	}

}
