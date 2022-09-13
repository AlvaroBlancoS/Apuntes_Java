package Ejercicios;

import java.util.Scanner;

public class PrimerEjercicio {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Escribe usuario: ");
		String usuario = sc.nextLine();
		System.out.println("Escribe el password");
		String password = sc.nextLine();
		verificar(usuario, password);

	}

	public static String saludo(String usuario) {
		String saludo = "Bienvenido " + usuario;
		return saludo;
	}

	public static String error(String usuario) {

		String error = "oohh vaya... No pudimos validar tus datos. " + usuario;

		return error;
	}

	public static void verificar(String usuario, String password) {

		String usuarioValido = "alvaro";
		String passwordValido = "123";

		if (usuarioValido.equals(usuario) && passwordValido.equals(password)) {
			System.out.println(saludo(usuario));
			return;
		} else {
			System.out.println(error(usuario));
		}

	}

}
