package Ejercicios;

import java.util.Scanner;

public class CuartoEjercicio {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		OtherSesionButOther o = new OtherSesionButOther();
		String usuario = sc.nextLine();
		o.setUsuario(usuario);
		String password = sc.nextLine();
		o.setPassword(password);
		
		verificar(o.getUsuario(), o.getPassword());
		System.out.println(o.toString());
	}

	public static void verificar(String usuario, String password) {
		if("alvarito".equals(usuario)&&"123".equals(password)) {
			System.out.println("Bienvenido "+usuario);
			return;
		}else {
			System.out.println("oohh baya.... no pudimos validar "+usuario);
		}
	}

}

class OtherSesionButOther {
	public String usuario;
	public String password;

	public OtherSesionButOther() {

	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "OtherSesionButOther [usuario=" + usuario + "Password="+password+"]";
	}

}
