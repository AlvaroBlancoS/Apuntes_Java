import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArreglosSencillos {

	public static void main(String[] args) {
		// multidimensional();
//		primerEjemploArrayList();
		// ejemploArrayListConIterator();
//		mayorOmenor();
		
		for (int i = 0; i < rellenarArray().length; i++) {
			System.out.println(i);
		}
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
	//------IMPORTANTÍSIMO!!!!
	public static void multidimensional() {
		System.out.println("---Estoy utilizando un array de multidimensional---");
		String datos[][] = { { "Pepito", "Maria", "Juanito", "Laurita" }, { "Coche", "Moto", "Avion", "Helicoptero" } };
		for (int i = 0; i < datos.length; i++) {
			for (int j = 0; j < datos[i].length; j++) {
//				System.out.println(datos[i][j]);
				System.out.println(datos[0][j]+" tiene "+datos[1][j]);
			}
		}

	}
	//-----IMPORTANTÍSIMO!!!!
	public static void bidimensional() {
		//Tiene dos filas y cuatro columnas
		int arrayBi[][] = new int [2][4];
		arrayBi[0][0]=1;
		arrayBi[0][1]=2;
		arrayBi[0][2]=3;
		arrayBi[0][3]=4;
		
		arrayBi[1][0]=10;
		arrayBi[1][1]=20;
		arrayBi[1][2]=30;
		arrayBi[1][3]=40;
		
		for (int i = 0; i < arrayBi.length; i++) {
			System.out.println("Valor de i: "+i);
			for (int j = 0; j < arrayBi[1].length; j++) {
				System.out.println("Estoy en i (fila) = "+i+", j (columna) = "+j);
				System.out.println(arrayBi[i][j]);
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

	// Como crear un metodo array
	public static int[] rellenarArray() {
		int num[] = new int[10];
		int a = 0;
		for (int i = 0; i < num.length; i++) {
			//num[i] = a;
			
		}

		return num;

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
	
	public static void convertirDeArrayListAarray() {
		ArrayList<String> lista = new ArrayList<>();
		lista.add("Primer elemento");
		lista.add("Segundo elemento");
		lista.add("Tercer elemento");
		String array[] = new String[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			array[i] = lista.get(i);
		}

//		for (Object arrayObjeto : lista.toArray()) {
//			System.out.println(arrayObjeto.toString());
//		}

		for (String elemento : array) {
			System.out.println("Elemento es: " + elemento);
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
	
	public static void primerLinkedList() {
		LinkedList<String> listaEnlazada = new LinkedList<String>();
		listaEnlazada.add("Primer elemento");
		listaEnlazada.add("Segundo elemento");
		listaEnlazada.add("Tercer elemento");
		listaEnlazada.add("Cuarto elemento");

		for (String elementos : listaEnlazada) {
			System.out.println(elementos);
		}
	}
	/*
	 * Qué diferencia hay entre un vector y un arrayList
	 * 
	 * Es similar a un array, la diferencia estriba en que 
	 * un vector crece automáticamente  cuando alcanza la 
	 * dimesión inicial máxima.
	 * 
	 * De forma preterminada, Vector duplica  el tamaño de 
	 * su matriz cuando aumenta su tamaño. Pero, ArrayList aumenta
	 * a la mitad de su tamaño cuando aumenta su tamaño.
	 * Por lo tanto, según la API de java, la única diferencia 
	 * principal es que los métodos de Vector están sincronizados y los
	 * métodos de ArrayList no están sincronizados
	 */
	//CapacidadVector = CapacidadVector *2
	public static void primerVector() {
			int inicioCapacidad = 1;
			int capacidadIncrementada = 15;
			System.out.println("---Primer ejemplo de vector estamos utilizando el bucle foreach y no representa la posición---");
			Vector<Integer> vector = new Vector(inicioCapacidad, capacidadIncrementada);
			vector.add(1);
			vector.add(2);
			vector.add(3);
			
//			vector.remove(1);
			
//			System.out.println("Datos del vector: "+vector);
			
			
			for (int i : vector) {
				System.out.println(i);
			}
			
			
			System.out.println("Vector primer ejemplo tamaño: "+vector.size()+" y capacidad: "+vector.capacity());
			vector.trimToSize();
			System.out.println("Vector primer ejemplo tamaño: "+vector.size()+" y capacidad: "+vector.capacity());
			
			System.out.println("---Este es el segundo ejemplo de vector y estamos utilizando el bucle for que si representa la posición---");
			
			Vector<Integer> vector2 = new Vector(inicioCapacidad, capacidadIncrementada);
			vector2.add(1);
			vector2.add(2);
			vector2.add(4);
			
			for(int i =0; i<vector2.size(); i++) {
		
				System.out.println("Número de posicion: "+i+" y el resultado es: "+vector2.get(i));
			}
			
			
			boolean resultado = vector.equals(vector2);
			
			System.out.println("El vector de primer ejemplo es igual que el vector de segundo ejemplo?: "+resultado);
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
