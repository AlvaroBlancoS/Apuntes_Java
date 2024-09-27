package util;

/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */
public class ExceptionDNI extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int soloNumero;

	public ExceptionDNI() {
		super();
	}

	public ExceptionDNI(int soloNumero) {
		super();
		this.soloNumero = soloNumero;
	}

	public int getSoloNumero() {
		return soloNumero;
	}

	public void setSoloNumero(int soloNumero) {
		this.soloNumero = soloNumero;
	}

	@Override
	public String toString() {
		return "El numero " + soloNumero + " que has marcado no es valido";
	}

}
