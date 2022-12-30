package utilidades;

public class ExcepcionEstaciones extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionEstaciones() {

	}

	public ExcepcionEstaciones(String msg) {
		super(msg);
	}
}
