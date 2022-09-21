import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArreglosSencillos {

	public static void main(String[] args) {
		// multidimensional();
//		primerEjemploArrayList();
		// ejemploArrayListConIterator();
		mayorOmenor();
	}

	public static void arraySencillo() {
		System.out.println("---Primer array como ejemplo---");
		int primeraOpcion[] = new int[4];
		primeraOpcion[0] = 18;
		primeraOpcion[1] = 23;
		primeraOpcion[2] = 56;
		primeraOpcion[3] = 32;

		int segundaOpcion[] = { 18, 23, 56, 32 };
		for (int i : segundaOpcion) {
			System.out.println(i);

		}
//		for (int i = 0; i < segundaOpcion.length; i++) {
//			System.out.println(segundaOpcion[i]);
//		}

	}

	public static void unidimensional() {
		System.out.println("---Estoy utlizando un array unidimensional---");
		String[] nombres = { "Pepito", "Maria", "Juanito", "Laurita" };
		for (int i = 0; i < nombres.length; i++) {
			System.out.println(nombres[i]);
		}
	}

	public static void unidimensional2() {
		System.out.println("---Estoy utilizando dos arrays de unidimensional---");
		String[] nombres = { "Pepito", "Maria", "Juanito", "Laurita" };
		int[] edades = { 21, 25, 18, 18 };
		for (int i = 0; i < nombres.length && i < edades.length; i++) {
			System.out.println("Se llama " + nombres[i] + " y tiene " + edades[i]);
		}

	}

	public static void multidimensional() {
		System.out.println("---Estoy utilizando un array de multidimensional---");
		String datos[][] = { { "Pepito", "Maria", "Juanito", "Laurita" }, { "Coche", "Moto", "Avion", "Helicoptero" } };
		for (int i = 0; i < datos.length; i++) {
			for (int j = 0; j < datos[i].length; j++) {
				System.out.println(datos[i][j]);
			}
		}

	}

	public static void mayorOmenor() {

		int array[] = { 3, 8, 9, 2, 10, 8, 5, 10, 2, 3 };
		int mayor, menor;
		mayor = menor = array[0];

		for (int i = 0; i < array.length; i++) {
			if (array[i] > mayor) {
				mayor = array[i];
			}
			if (array[i] < menor) {
				menor = array[i];
			}
		}
		System.out.println("El mayor valor es: " + mayor);
		System.out.println("El menor valor es: " + menor);
	}

	public static void ejemploConClaseArray() {
		System.out.println("---Estoy utilizando un array con clase---");
		Personas[] dato = { new Personas("Pepito", 21), new Personas("Maria", 25), new Personas("Juanito", 18),
				new Personas("Laurita", 18) };

//		for (Personas personas : dato) {
//			System.out.println("Se llama " + personas.getNombre() + " y tiene " + personas.getEdad() + " anios");
//		}

		for (int i = 0; i < dato.length; i++) {
			System.out.println("Se llama " + dato[i].getNombre() + " y tiene " + dato[i].getEdad() + " anios");
		}

	}

	public static void primerEjemploArrayList() {
		System.out.println("---Estoy utilizando un array---");
		List<String> nombres = new ArrayList<>();
		nombres.add("Pepito");
		nombres.add("Maria");
		nombres.add("Juanito");
		nombres.add("Laurita");

//		for (String nombre : nombres) {
//			System.out.println(nombre);
//		}
		for (int i = 0; i < nombres.size(); i++) {
			System.out.println(nombres.get(i));
		}
//		nombres.remove(1);
//		
	}

	public static void segundoEjemploArrayList() {
		System.out.println("--Estoy utilizando dos arrayLists---");
		ArrayList<String> nombres = new ArrayList<>();
		nombres.add("Pepito");
		nombres.add("Maria");
		nombres.add("Juanito");
		nombres.add("Laurita");
		ArrayList<Integer> edades = new ArrayList<>();
		edades.add(21);
		edades.add(25);
		edades.add(18);
		edades.add(18);

		for (int i = 0; i < nombres.size() && i < edades.size(); i++) {
			System.out.println("Se llama " + nombres.get(i) + " y tiene " + edades.get(i));
		}

	}

	public static void ejemploArrayListConIterator() {
		System.out.println("---Estoy utilizando un arrayList con iterator---");
		List<String> nombres = new ArrayList<>();
		nombres.add("Pepito");
		nombres.add("Maria");
		nombres.add("Juanito");
		nombres.add("Laurita");
		Iterator listaNombres = nombres.iterator();
		ArrayList<Integer> edades = new ArrayList<>();
		edades.add(21);
		edades.add(25);
		edades.add(18);
		edades.add(18);
		Iterator listaEdades = edades.iterator();
		while (listaNombres.hasNext() && listaEdades.hasNext()) {
			String nombre = (String) listaNombres.next();
			int edad = (int) listaEdades.next();
			System.out.println("Se llama " + nombre + " y tiene " + edad + " anios");
		}

	}

	public static void ejemploConClaseArrayList() {
		System.out.println("---Estoy utilizando arrayList con clase---");
		ArrayList<Personas> dato = new ArrayList<>();
		dato.add(new Personas("Pepito", 21));
		dato.add(new Personas("Maria", 25));
		dato.add(new Personas("Juanito", 18));
		dato.add(new Personas("Laurita", 18));
		for (Personas personas : dato) {
			System.out.println("Se llama " + personas.getNombre() + " y tiene " + personas.getEdad() + " anios");
		}

	}

	public static void arrayListConIterator() {
		System.out.println("---Estoy utilizando arrayList con clase y con iterator---");
		ArrayList<Personas> dato = new ArrayList<>();
		dato.add(new Personas("Pepito", 21));
		dato.add(new Personas("Maria", 25));
		dato.add(new Personas("Juanito", 18));
		dato.add(new Personas("Laurita", 18));
		Iterator it = dato.iterator();
		while (it.hasNext()) {
			// Recojo toda informacion
			Personas informacion = (Personas) it.next();
			// Y muestra la informacion que he recogido
			System.out.println("Se llama " + informacion.getNombre() + " y tiene " + informacion.getEdad() + " anios");
		}
	}

}

class Personas {
	private String nombre;
	private int edad;

	public Personas() {

	}

	public Personas(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

}
