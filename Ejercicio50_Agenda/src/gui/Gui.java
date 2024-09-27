package gui;


import Util.Agregar;
import data.ContactoData;
import model.Contacto;

/**
 * 
 * @author Álvaro Blanco
 *
 */
public class Gui {
	private static boolean sigo = true;
	private static ContactoData d = new ContactoData();

	public static void menu() {
		do {
			sigo = elegirOpcion(Agregar.leerEntero(pantalla()));
		} while (sigo);
	}

	private static boolean elegirOpcion(int opcion) {
		String resp = null;
		int respEntero = 0;
		switch (opcion) {
		case 0:
			resp = Agregar.leerString("Estas seguro que quieres salir? Si-No");
			if (resp.equalsIgnoreCase("si") || resp.equalsIgnoreCase("s")) {
				System.out.println("Hasta pronto!!!");
				sigo = false;
			}
			break;
		case 1:
			String nombre = Agregar.leerString("Nombre");
			String apellidos = Agregar.leerString("Apellidos:");
			if (d.isExist(nombre, apellidos) == false) {
				String email = Agregar.leerString("email:");
				String telf = Agregar.leerString("Telf:");
				respEntero = Agregar.leerEntero(grupos(2));
				if (respEntero == 1) {
					Contacto c = d.crearContacto(nombre, apellidos, email, telf, Agregar.contactoAmistad());
					d.incluirContacto(c);

				} else if (respEntero == 2) {
					Contacto c = d.crearContacto(nombre, apellidos, email, telf, Agregar.contactoFamiliar());
					d.incluirContacto(c);
				} else {
					System.err.println("Error!");
				}
			} else {
				System.err.println("No se ha podido registrar porque existe el contacto");
			}

			break;
		case 2:
			d.verTodosLosContactos();
			break;
		case 3:
			respEntero = Agregar.leerEntero(grupos(1));
			if (respEntero == 1) {
				d.verContactosDeAmistad();
			} else if (respEntero == 2) {
				d.verContactosDeFamiliar();
			} else {
				System.err.println("Error!");
			}
			break;

		default:
			System.err.println("Error!!");
			break;
		}
		return sigo;
	}

	private static String pantalla() {
		return "\n---Opciones---\n0-Salir\n1-Incluir contactos\n2-Listar todos los contactos\n3-Listar contactos";
	}

	private static String grupos(int op) {
		return (op == 1) ? "\n---Opciones---\n1-Ver contacto de amigos\n2-Ver contacto de familia"
				: "\n---Opciones---\n1-Agregar contacto de amigos\n2-Agregar contacto de familia";
	}
}
