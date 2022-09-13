package condicionales;

public class OperadorTernario {

	public static void main(String[] args) {
		Personas p1 = new Personas("Paco", 2);
		Personas p2 = new Personas("Maria", 1);
		// p1.presentarCorregir();
		// p2.presentarCorregir();
		p1.presentar();
		p2.presentar();
	}

}

class Personas {
	private String nombre;
	private int cantidadHijos;

	public Personas(String nombre, int cantidadHijos) {
		this.nombre = nombre;
		this.cantidadHijos = cantidadHijos;
	}

	public void presentar() {
		System.out.println("Se llama " + this.nombre + " y tiene " + this.cantidadHijos + " hijo");
	}

	public void presentarCorregir() {
		System.out.println("Se llama " + this.nombre + " y tiene " + this.cantidadHijos + " hijo"
				+ (this.cantidadHijos != 1 ? "s" : ""));
	}

}
