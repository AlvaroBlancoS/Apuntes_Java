package listas.datos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import listas.util.Agregar;

public class Ejemplo1 {

	private String elementos[] = { "El primer elemento es ", "El segundo elemento es ", "El tercer elemento es ",
			"El cuarto elemento es " };

	/***
	 * Simplemente ver como funciona un arraytList con dos metodos fundamentales: add y remove
	 * Además ver como funcionar un bucle for normal y un foreach
	 * 
	 * @param elementoSeleccionado
	 * @param elementoEliminado
	 */
	public void arraySencillo(int elementoSeleccionado, int elementoEliminado) {
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		numeros.add(10);// primer
		numeros.add(20);// segundo
		numeros.add(100);// tercero
		numeros.add(120);// cuatro
		// Elemento originales
		System.out.println("Estos son los elementos originales");
		for (int i = 0; i < numeros.size(); i++) {
			System.out.println(numeros.get(i));
		}
		int num = elementoSeleccionado - 1;
		String mensaje = (num == 0) ? elementos[0]
				: (num == 1) ? elementos[1] : (num == 2) ? elementos[2] : elementos[3];
		System.out.println(mensaje + numeros.get(num) + " ha sido seleccionado");

		int num2 = elementoEliminado - 1;
		int verElementoEliminado = numeros.get(num2);
		numeros.remove(num2);

		String mensaje2 = (num2 == 0) ? elementos[0]
				: (num2 == 1) ? elementos[1] : (num2 == 2) ? elementos[2] : elementos[3];
		System.out.println(mensaje2 + verElementoEliminado + " ha sido eleminado");

		// Elementos modificados
		System.out.println("Estos son los elementos modificados, solo hemos eleminado un elemento");
		for (Integer numero : numeros) {
			System.out.println(numero);
		}

	}

	/**
	 * 
	 * @param cantidadElementos
	 * @param numero
	 * @return almacena los numeros de cualquier cantidad de elemento
	 */
    public  ArrayList<Integer> cajaNumeros(int cantidadElementos) {
        ArrayList<Integer> numeros = new ArrayList<Integer>(Collections.nCopies(cantidadElementos, 0));
        for (int i = 0; i < cantidadElementos; i++) {
            numeros.set(i, Agregar.leerEntero("(" + i + ") introduzca un numero: "));
            if (i == cantidadElementos - 1) {
                break;
            }
        }
        return numeros;
    }

	public List<Integer> verNumeros() {
		List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(100);
		numeros.add(23);
		numeros.add(43);
		numeros.add(867);
		numeros.add(1);
		return numeros;
	}
}
