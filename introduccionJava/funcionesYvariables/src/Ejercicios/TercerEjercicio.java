package Ejercicios;

import java.util.Scanner;

public class TercerEjercicio {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String usuario = sc.nextLine();
		String password = sc.nextLine();
		OtherSesion s = new OtherSesion(usuario,password);
		s.verificar();

	}

}

class OtherSesion {
	public static String usuario;
	public static String password;

	public OtherSesion(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}

	public static void verificar() {		
		String verificarUsuario = "alvarito";
		String verificarPassword = "123";
		
		if (verificarUsuario.equals(usuario)&& verificarPassword.equals(password)) {
			System.out.println("Bienvenido "+usuario);
			return;
		}else {
			System.out.println("oohh baya.... no pudimos validar "+usuario);
		}
		
	}
	
}
