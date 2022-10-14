package supermercado;

public class UsoBolsa {

	public static void main(String[] args) {
		Bolsa<String> q1 = new Bolsa<>();
		q1.setContenido("caramelos");
		System.out.println(q1.getContenido());
		
	}

}
