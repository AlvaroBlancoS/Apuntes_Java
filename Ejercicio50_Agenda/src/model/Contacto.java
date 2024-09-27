package model;

public class Contacto {
	private String nombre;
	private String apellidos;
	private String email;
	private String telf;
	private TipoContacto tipo;

	public Contacto(String nombre, String apellidos, String email, String telf, TipoContacto tipo) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telf = telf;
		this.tipo = tipo;
	}

	public Contacto() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelf() {
		return telf;
	}

	public void setTelf(String telf) {
		this.telf = telf;
	}

	public TipoContacto getTipo() {
		return tipo;
	}

	public void setTipo(TipoContacto tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", telf=" + telf
				+ ", tipo=" + tipo.getTipoContacto() + "]";
	}
	
	

}
