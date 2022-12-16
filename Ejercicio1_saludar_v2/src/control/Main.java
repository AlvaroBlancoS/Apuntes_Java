package control;

import java.util.Scanner;

public class Main {
	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Como te llamas?");
		String nombre = in.next();
		System.out.println("Hola " + nombre);
	}

}
