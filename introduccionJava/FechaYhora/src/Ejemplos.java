import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Ejemplos {
	public static void main(String[] args) {
		
//		agregarHoras();
		enGeneral();
	}

	public static void agregarHoras() {
		LocalTime hour = LocalTime.of(6, 30);
		System.out.println(hour);
	}
	public static void horas() {
		System.out.println("------------");
		LocalDateTime locaDate = LocalDateTime.now();
		
		int hours = locaDate.getHour();
		int minutes = locaDate.getMinute();
		int seconds = locaDate.getSecond();
		System.out.println("Hora actual : " + hours + ":" + minutes + ":" + seconds);
	}

	public static void enGeneral() {
		// https://www.localeplanet.com/java/es-ES/index.html
		ZoneId zone = ZoneId.of("Europe/Berlin");
		Clock clock = Clock.system(zone);
		System.out.println("Hora de Europe/Berlin: " + clock.instant().toString());

		LocalTime lt = LocalTime.now(clock);
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(1990, Month.SEPTEMBER, 1);

		Period p = Period.between(birthday, today);
		long p2 = ChronoUnit.DAYS.between(birthday, today);

		System.out.println("Fecha de Nacimiento:   " + birthday.getDayOfMonth() + "/" + birthday.getMonth() + "/"
				+ birthday.getYear());
		// Para hacerlo en castellano realizar un método que traduzca el mes en Ingles a
		// Español
		System.out.println("Dia actual:            " + today.getDayOfMonth() + "/" + today.getMonth() + "/"
				+ today.getYear() + " (" + lt.toString() + ")");
		System.out.println("Tu tienes " + p.getYears() + " años, " + p.getMonths() + " meses, y " + p.getDays()
				+ " dias. (" + p2 + " dias en total)");

		// TRABAJANDO CON ZONAS HORARIAS
		System.out.println("-----");

		ZoneId zone1 = ZoneId.of("Greenwich");
		LocalDateTime local1 = LocalDateTime.now(zone1);
		System.out.println("Hora Greenwich: " + local1);

		ZoneId zone2 = ZoneId.of("Europe/Madrid");
		LocalDateTime local2 = LocalDateTime.now(zone2);
		System.out.println("Hora Madrid:    " + local2);

		int diferencia = local2.getHour() - local1.getHour();
		System.out.println("Diferencia:     " + diferencia + " horas");

		// TRABAJANDO CON VALORES CONCRETOS
		System.out.println("-----");
		LocalDateTime ldt = LocalDateTime.now();

		DateTimeFormatter isoFecha = DateTimeFormatter.ISO_LOCAL_DATE;
		System.out.println("Fecha:         " + ldt.format(isoFecha));

		DateTimeFormatter isoHora = DateTimeFormatter.ISO_LOCAL_TIME;
		System.out.println("Hora completa: " + ldt.format(isoHora));

		DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
		System.out.println("Hora:          " + ldt.format(f));

		// En castellano
		DateTimeFormatter formEsp = DateTimeFormatter.ofPattern("dd MMMM (uuuu) hh:mm a", new Locale("es", "ES"));
		// .ofLocalizedDateTime(FormatStyle)
		// .withLocale(new Locale("es", "ES"));
		System.out.println("En castellano: " + ldt.format(formEsp));
	}


}
