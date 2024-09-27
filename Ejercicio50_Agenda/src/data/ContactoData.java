package data;

import java.util.ArrayList;
import java.util.List;

import Util.Agregar;
import model.Contacto;
import model.TipoContacto;

/**
 * 
 * @author Álvaro Blanco
 *
 */
public class ContactoData {
	List<Contacto> contactos = ListaContactos();

	public List<Contacto> ListaContactos() {
		List<Contacto> contactos = new ArrayList<>();
		contactos.add(
				new Contacto("Paco", "Garcia Umbral", "paquito@gmail.com", "34+654345323", Agregar.contactoAmistad()));
		contactos.add(new Contacto("Marta", "Sanchez Abascal", "martasb@gmail.com", "34+656443322",
				Agregar.contactoAmistad()));
		contactos.add(new Contacto("Pedro", "Blanco Lopez", "pedrolopez23@gmail.com", "34+654345323",
				Agregar.contactoFamiliar()));
		return contactos;
	}

	public void verTodosLosContactos() {
		for (Contacto contacto : contactos) {
			System.out.println(contacto.toString());
		}
	}

	public void verContactosDeAmistad() {
		System.out.println("---Contacto de amigos---");
		for (Contacto contacto : contactos) {
			if (contacto.getTipo() == Agregar.contactoAmistad()) {
				System.out.println(contacto.toString());
			}

		}

	}

	public void verContactosDeFamiliar() {
		System.out.println("---Contacto de familia---");
		for (Contacto contacto : contactos) {
			if (contacto.getTipo() == Agregar.contactoFamiliar()) {
				System.out.println(contacto.toString());
			}

		}
	}

	public boolean isExist(String nombre, String apellidos) {
		for (Contacto contacto : contactos) {
			if (contacto.getNombre().equals(nombre) && contacto.getApellidos().equals(apellidos)) {
				return true;
			}
		}

		return false;
	}

	public Contacto crearContacto(String nombre, String apellidos, String email, String telf, TipoContacto tipo) {
		return new Contacto(nombre, apellidos, email, telf, tipo);
	}

	public void incluirContacto(Contacto c) {
		contactos.add(c);
	}

}
