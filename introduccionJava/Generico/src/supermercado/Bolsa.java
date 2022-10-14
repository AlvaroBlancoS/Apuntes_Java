package supermercado;

public class Bolsa<T> {

	private T contenido;

	public T getContenido() {
		return contenido;
	}

	public void setContenido(T nuevoValor) {
		this.contenido = nuevoValor;
	}

}
