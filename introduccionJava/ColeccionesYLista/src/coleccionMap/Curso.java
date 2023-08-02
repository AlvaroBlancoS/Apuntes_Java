package coleccionMap;

import java.util.HashMap;
import java.util.Map;

public class Curso {
	public static void main(String[] args) {
		Map<String, int[]> asignaturas = new HashMap<String, int[]>();
		String nombreCurso = "Dam";

		// Agregar notas para "Programacion"
		int[] notaProgramacion = { 5, 6, 6 };
		asignaturas.put("Programacion", notaProgramacion);

		// Agregar notas para "Sistema"
		int[] notaSistema = { 9, 5, 6 };
		asignaturas.put("Sistema", notaSistema);
		System.out.println("---"+nombreCurso+"---");
		// Utilizar un for-each para recorrer el Map e imprimir las notas
		for (Map.Entry<String, int[]> entry : asignaturas.entrySet()) {
			String asignatura = entry.getKey();//Es una clave
			int[] notas = entry.getValue();//Es un valor

			System.out.println("Asignatura: " + asignatura);
			System.out.print("Notas: ");
			for (int nota : notas) {
				System.out.print(nota + " ");
			}
			System.out.println("\n------");
		}
	}
}
