package interfaces;

public class InterfacesMain {
	/**
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		CocheService service = new CocheDeportivo();
		CocheService service2 = new CocheClassic();
		service.crearCoche();
		service2.crearCoche();
	}

}
