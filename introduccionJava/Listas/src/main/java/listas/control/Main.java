package listas.control;



import java.util.ArrayList;

import listas.datos.Ejemplo1;
import listas.datos.Ejemplo2;
import listas.model.Agenda;
import listas.util.Agregar;

public class Main {
	private static Ejemplo1 e1 = new Ejemplo1();
	private static Ejemplo2 e2 = new Ejemplo2();

	public static void main(String[] args) {
		algoMasComplicado();
//		verAgenda();
//		buscadorAgenda("Paco");
	
	}
	
	private static void arraySencillo() {
		e1.arraySencillo(2, 3);
	}
	
	private static void arrayPocoComplicado() {
		ArrayList<Integer> listaNumeros = (ArrayList<Integer>) e1.verNumeros();
//		listaNumeros.remove(0);
		for (int i = 0; i < listaNumeros.size(); i++) {
			System.out.println(listaNumeros.get(i));
		}
	}
	
	private static void algoMasComplicado() {
	     int cantidadElementos = 5;
	        ArrayList<Integer> numeros = e1.cajaNumeros(cantidadElementos);
	        System.out.println("Numeros ingresados:");
	        for (int numero : numeros) {
	            System.out.println(numero);
	        }
	}
	
	private static void verAgenda() {
		for (int i = 0; i < e2.listaPersonas().size(); i++) {
			System.out.println(e2.listaPersonas().get(i));
		}
		
	}
	
	private static void buscadorAgenda(String nombre) {
		int bandera = 0;
		for (Agenda buscar : e2.listaPersonas()) {
			if (nombre.equals(buscar.getNombre())) {
				System.out.println(buscar.getNombre() + " " + buscar.getApellidos());
				bandera = 1;
			} 
		}
		
		if (bandera==0) {
			System.out.println("No existe");
		}

	}

}
