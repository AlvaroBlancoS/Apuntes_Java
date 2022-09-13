package Ejercicios;

import java.util.Scanner;

public class SegundoEjercicio {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Sesion s = new Sesion();
		s.usuario = sc.nextLine();
		s.password = sc.nextLine();
		Sesion.verificar(s.usuario, s.password);
	}

}

class Sesion {
	public String usuario;
	public String password;

	public Sesion() {
	}

	public static void verificar(String usuario, String password) {
		String usuarioValido = "alvarito";
		String passwordValido = "123";
		if (usuarioValido.equals(usuarioValido) && passwordValido.equals(passwordValido)) {
			System.out.println("Bienvenido " + usuario);
			return;
		} else {
			System.out.println("ooohh vaya... No pudimos validar tus datos " + usuario);
		}

	}

}
