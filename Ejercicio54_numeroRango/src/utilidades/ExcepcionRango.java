package utilidades;

/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */
public class ExcepcionRango extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcepcionRango() {

	}

	public ExcepcionRango(int numero) {
		super();

	}

	@Override
	public String toString() {
		return "El numero que has marcado es incorrecto";

	}

}
