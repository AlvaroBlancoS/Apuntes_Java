package EjerciciosBucles;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Ejercicio1 {
	/*
	 * Crear un menú para calcular el cuadrado, reactangulo, triangulo y circulo
	 *-Debe funcionar con Scanner para introducir la opción de menú
	 *- Debe hacer que funcione JOptionPane cuando el usuario introduzca el valor
	 *- La entrada por salida debe funcionar con JOptionPane como resultado
	 */

	public static void main(String[] args) {
		calcularAritmetica();
	}

	public static void calcularAritmetica() {
		Scanner in = new Scanner(System.in);
		int figura = 0;
		do {
			System.out.println("Elige la opción:\n1. Cuadrado\n2. Rectangulo\n3. Triangulo\n4. Circulo\n0. Salir");
			figura = in.nextInt();
			switch (figura) {
			case 1:
				int lado = Integer.parseInt(JOptionPane.showInputDialog("Intoduce al lado"));
				JOptionPane.showMessageDialog(null, Math.pow(lado, 2), null, JOptionPane.INFORMATION_MESSAGE);

				break;
			case 2:
				int base = Integer.parseInt(JOptionPane.showInputDialog("Intoduce la base"));
				int altura = Integer.parseInt(JOptionPane.showInputDialog("Intoduce la altura"));
				JOptionPane.showMessageDialog(null, "El area de rectangulo es: " + base * altura, null,
						JOptionPane.INFORMATION_MESSAGE);
				break;
			case 3:
				base = Integer.parseInt(JOptionPane.showInputDialog("Intoduce la base"));
				altura = Integer.parseInt(JOptionPane.showInputDialog("Intoduce la altura"));
				JOptionPane.showMessageDialog(null, "El area de triangulo es: " + (base * altura) / 2, null,
						JOptionPane.INFORMATION_MESSAGE);
				break;
			case 4:
				int radio = Integer.parseInt(JOptionPane.showInputDialog("Intoduce el radio"));
				JOptionPane.showMessageDialog(null, "El area del circulo es: " + Math.PI * (Math.pow(radio, 2)), null,
						JOptionPane.INFORMATION_MESSAGE);
				break;
			default:
				if (figura >= 5) {
					System.err.println("ERROR!!!");
				} else {
					System.out.println("Hasta pronto!!");
				}

				break;
			}

		} while (figura != 0);

	}

}
