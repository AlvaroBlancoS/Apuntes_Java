import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

import utilidades.ExcepcionEstaciones;
import utilidades.Imprimir;

public class Estaciones {
	private static ZoneId zone = ZoneId.of("Europe/Madrid");
	private static Clock clock = Clock.system(zone);
	private static LocalDate ld = LocalDate.now(clock);

	public static void main(String[] args) {

		int dia = Imprimir.leerEntero("Introduzca un dia");
		int mes = Imprimir.leerEntero("Introduzca un mes");

		if (isWinter(mes, dia) == true) {
			System.out.println("Es invierno");
		} else if (isSpring(mes, dia) == true) {
			System.out.println("Es primavera");
		} else if (isSummer(mes, dia) == true) {
			System.out.println("Es verano");
		} else if (isFall(mes, dia) == true) {
			System.out.println("Es otonio");
		} else if (mes >= 13) {
			throw new ExcepcionEstaciones("\nEl mes " + mes + " es incorrecto.");
		} else {
			throw new ExcepcionEstaciones("\nDia " + dia + " de "+ mes +" es incorrecto");
		}


	}

	public static boolean isWinter(int mes, int dia) {
		boolean season = false;
		if (mes == 12) {
			for (int i = 21; i <= diasEnUnMes(12, ld.getYear()).length; i++) {
				if (dia == i) {
					season = true;
				}
			}
		}

		if (mes == 1) {
			for (int i = 1; i <= diasEnUnMes(1, ld.getYear()).length; i++) {
				if (dia == i) {
					season = true;
				}
			}
		}

		if (mes == 2) {
			for (int i = 1; i <= diasEnUnMes(2, ld.getYear()).length; i++) {
				if (dia == i) {
					season = true;
				}
			}
		}

		if (mes == 3) {
			for (int i = 1; i <= diasEnUnMes(3, ld.getYear()).length; i++) {
				if (i < 20) {
					if (dia == i) {
						season = true;
					}
				}

			}
		}
		return season;
	}

	public static boolean isSpring(int mes, int dia) {
		boolean season = false;
		if (mes == 3) {
			for (int i = 20; i <= diasEnUnMes(3, ld.getYear()).length; i++) {
				if (dia == i) {
					season = true;
				}

			}
		}
		if (mes == 4) {
			for (int i = 1; i <= diasEnUnMes(4, ld.getYear()).length; i++) {
				if (dia == i) {
					season = true;
				}

			}
		}
		if (mes == 5) {
			for (int i = 1; i <= diasEnUnMes(5, ld.getYear()).length; i++) {
				if (dia == i) {
					season = true;
				}

			}
		}
		if (mes == 6) {
			for (int i = 1; i <= diasEnUnMes(6, ld.getYear()).length; i++) {
				if (i < 21) {
					if (dia == i) {
						season = true;
					}

				}

			}
		}

		return season;

	}

	public static boolean isSummer(int mes, int dia) {
		boolean season = false;

		if (mes == 6) {
			for (int i = 21; i <= diasEnUnMes(6, ld.getYear()).length; i++) {
				if (dia == i) {
					season = true;
				}

			}

		}

		if (mes == 7) {
			for (int i = 1; i <= diasEnUnMes(7, ld.getYear()).length; i++) {
				if (dia == i) {
					season = true;
				}

			}

		}

		if (mes == 8) {
			for (int i = 1; i <= diasEnUnMes(8, ld.getYear()).length; i++) {
				if (dia == i) {
					season = true;
				}
			}
		}

		if (mes == 9) {
			for (int i = 1; i <= diasEnUnMes(9, ld.getYear()).length; i++) {
				if (i < 23) {
					if (dia == i) {
						season = true;
					}
				}

			}
		}

		return season;

	}

	public static boolean isFall(int mes, int dia) {
		boolean season = false;
		if (mes == 9) {
			for (int i = 23; i <= diasEnUnMes(9, ld.getYear()).length; i++) {
				if (dia == i) {
					season = true;
				}
			}
		}

		if (mes == 10) {
			for (int i = 1; i <= diasEnUnMes(10, ld.getYear()).length; i++) {
				if (dia == i) {
					season = true;
				}
			}
		}

		if (mes == 11) {
			for (int i = 1; i <= diasEnUnMes(11, ld.getYear()).length; i++) {
				if (dia == i) {
					season = true;
				}
			}
		}

		if (mes == 12) {
			for (int i = 1; i <= diasEnUnMes(12, ld.getYear()).length; i++) {
				if (i < 21) {
					if (dia == i) {
						season = true;
					}
				}
			}
		}

		return season;
	}

	public static int[] diasEnUnMes(int mes, int anyo) throws ExcepcionEstaciones {
		int numDias = 0;
		switch (mes) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			numDias = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			numDias = 30;
			break;
		case 2:
			if ((anyo % 4 == 0 && anyo % 100 != 0) || anyo % 400 == 0) {
				numDias = 29;
			} else {
				numDias = 28;
			}
			break;
		default:
			// error
			break;
		}
		int array[] = new int[numDias];
		for (int i = 0; i < numDias; i++) {
			if (numDias != 0) {
				array[i] = i;
			}
		}
		return array;

	}

	public static void numeroDiasDeUnMes(int mes, int anyo) {
		int numDias = 0;
		switch (mes) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			numDias = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			numDias = 30;
			break;
		case 2:
			if ((anyo % 4 == 0 && anyo % 100 != 0) || anyo % 400 == 0) {
				numDias = 29;
			} else {
				numDias = 28;
			}
			break;
		default:
			System.out.println("\nEl mes " + mes + " es incorrecto.");
			break;
		}

		if (numDias != 0) {
			System.out.println("\nEl mes " + mes + " del año " + anyo + " tiene " + numDias + " días.");
		}

	}

}
