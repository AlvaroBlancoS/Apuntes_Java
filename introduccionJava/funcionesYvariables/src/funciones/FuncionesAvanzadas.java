package funciones;

public class FuncionesAvanzadas {

	public static void main(String[] args) {
		int param1 = 10;
		int param2 = 30;
		
		var valor = suma(param1, param2);
		System.out.println(valor);
		
		
		Potato miPotato = new Potato();
		miPotato.quitarBrazo();
		miPotato.quitarBrazo();
		miPotato.quitarBrazo();
		System.out.println(miPotato.brazos);
		
		
	}
	//Paso por valor
	public static int suma(int a, int b) {
		return a +b;
	}
	//Paso por referencia

}

class Potato{
	public int brazos = 0;
	public void quitarBrazo() {
		this.brazos--;
	}
	
}
