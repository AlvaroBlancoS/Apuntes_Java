package HerenciaVehiculos3;

public class Main {

	public static void main(String[] args) {
		Vehiculo coche1 = new Vehiculo();
		coche1.establece_color("rojo");
		
		Furgoneta miFurgoneta = new Furgoneta(7,580);
		miFurgoneta.establece_color("azul");
		
		miFurgoneta.configura_climatizador("si");
		
		miFurgoneta.configura_climatizador("si");
		
		System.out.println(coche1.dime_datos_generales()+" color:"+coche1.dime_color()+"\n"+miFurgoneta.dimeDatosFurgoneta()+" color: "+miFurgoneta.dime_color());
		
	}

}
