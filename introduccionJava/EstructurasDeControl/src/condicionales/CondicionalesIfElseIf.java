package condicionales;

public class CondicionalesIfElseIf {

	public static void main(String[] args) {

		String dia = "Martes";

		boolean resultado = dia.equals("Lunes");
		// if else if

		if (dia.equals("Lunes")) {
			System.out.println("Animo con la semana champions");
		} else if (dia.equals("Martes")) {
			System.out.println("Martes con M de Me besas");
		} else if (dia.equals("Miercoles")) {
			System.out.println("Hoy es miercoles y lo sabes");
		} else if (dia.equals("Jueves")) {
			System.out.println("Se acerca el viernes");
		} else if (dia.equals("Viernes")) {
			System.out.println("Hoy es viernes y el cuerpo lo sabe");
		} else if (dia.equals("Sabado")) {
			System.out.println("A disfrutar como pueda");
		} else if (dia.equals("Domingo")) {
			System.out.println("Hoy descansito para madrugar");
		} else {
			System.out.println("El dia introducido no es un dia valido");
		}

	}

}
