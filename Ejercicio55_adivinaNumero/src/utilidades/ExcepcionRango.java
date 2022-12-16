package utilidades;

public class ExcepcionRango extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcepcionRango() {

	}

	public ExcepcionRango(int numero) {
		super();
	}

	public String mensajeERROR(String msg) {
		return msg;
	}

	@Override
	public String toString() {
		return "El numero que es marcado es incorrecto";
	}

}
