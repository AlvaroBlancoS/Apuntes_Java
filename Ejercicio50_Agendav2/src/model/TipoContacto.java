package model;

public enum TipoContacto {
	CONTACTO_FAMILIAR("Familia"), CONTACTO_AMISTAD("Amigo");

	private final String tipoContacto;

	private TipoContacto(String tipo) {
		this.tipoContacto = tipo;
	}

	public String getTipoContacto() {
		return tipoContacto;
	}

}
