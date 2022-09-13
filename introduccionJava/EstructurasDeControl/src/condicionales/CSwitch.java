package condicionales;

public class CSwitch {

	public static void main(String[] args) {
//		opcion1("verAno");
//		opcion2("s");

		int dia = 2;
		switch (dia) {
		case 1:
			System.out.println("Lunes");
			break;
		case 2:
			System.out.println("Martes");
			break;
		case 3:
			System.out.println("Miercoles");
			break;
		case 4:
			System.out.println("Jueves");
			break;
		case 5:
			System.out.println("Viernes");
			break;

		default:
			System.out.println("Fin de semana");
			break;
		}

	}

	public static void primerSwitch() {
		String weather = "sunny";
		switch (weather) {
		case "sunny":
			System.out.println("El tiempo es soleado");
			break;
		case "cloudy":
			System.out.println("El tiempo es nublado");
			break;
		default:// caso por defecto
			System.out.println("No se ha podido identificar el clima");
			break;
		}
	}

	public static void opcion1(String estacion) {
		switch (estacion.toLowerCase()) {
		case "verano":
			System.out.println("Es verano");
			break;
		case "invierno":
			System.out.println("Es invierno");
			break;
		case "primavera":
			System.out.println("Es primavera");

			break;
		case "otoño":
			System.out.println("Es otoño");
			break;

		default:
			System.out.println("Estoy en default");
			break;
		}
	}

	public static void opcion2(String dias) {
		System.out.println("--------------------");
		switch (dias.toLowerCase()) {
		case "lunes":
		case "martes":
		case "miercoles":
		case "jueves":
		case "viernes":
			System.out.println(dias + ", hoy es laborable");
			break;
		case "sabado":
		case "domingo":
			System.out.println(dias + ", hoy NO es laborable");
			break;
		default:
			System.err.println("Por favor, vuelva a escribir de nuevo");
			break;
		}
	}

}
