

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;

import utilidades.ExcepcionesTime;

public class AgregarFechaYhora {
	private ZoneId zone = ZoneId.of("Europe/Madrid");
	private Clock clock = Clock.system(zone);
	private LocalDate ld = LocalDate.now(clock);
	private LocalTime lt = LocalTime.now(clock);

	public String agregarFecha(int dia, int mes, int anio) throws ExcepcionesTime {
		LocalDate agregarFecha = null;
		String resp = null;
		if (mes == 1) {
			agregarFecha = LocalDate.of(anio, Month.JANUARY, dia);
		} else if (mes == 2) {
			agregarFecha = LocalDate.of(anio, Month.FEBRUARY, dia);
		} else if (mes == 3) {
			agregarFecha = LocalDate.of(anio, Month.MARCH, dia);
		} else if (mes == 4) {
			agregarFecha = LocalDate.of(anio, Month.APRIL, dia);
		} else if (mes == 5) {
			agregarFecha = LocalDate.of(anio, Month.MAY, dia);
		} else if (mes == 6) {
			agregarFecha = LocalDate.of(anio, Month.JUNE, dia);
		} else if (mes == 7) {
			agregarFecha = LocalDate.of(anio, Month.JULY, dia);
		} else if (mes == 8) {
			agregarFecha = LocalDate.of(anio, Month.AUGUST, dia);
		} else if (mes == 9) {
			agregarFecha = LocalDate.of(anio, Month.SEPTEMBER, dia);
		} else if (mes == 10) {
			agregarFecha = LocalDate.of(anio, Month.OCTOBER, dia);
		} else if (mes == 11) {
			agregarFecha = LocalDate.of(anio, Month.NOVEMBER, dia);
		} else if (mes == 12) {
			agregarFecha = LocalDate.of(anio, Month.DECEMBER, dia);
		} else {
			throw new ExcepcionesTime(mes+" no es un mes");
		}

		resp = agregarFecha.getDayOfMonth() + "/" + agregarFecha.getMonthValue() + "/" + agregarFecha.getYear();
		return resp;
	}

	public String fechaAutomatica() {
		return ld.getDayOfMonth() + "/" + ld.getMonthValue() + "/" + ld.getYear();
	}

	public String agregarHora(int hora, int minutos, int segundos) {
		LocalTime agregando = LocalTime.of(hora, minutos, segundos);
		return agregando.getHour() + ":" + agregando.getMinute() + ":" + agregando.getSecond();
	}

	public String horaAutomatica() {
		return lt.getHour() + ":" + lt.getMinute() + ":" + lt.getSecond();
	}

	public int getDayAuto() {

		return ld.getDayOfMonth();
	}

	public int getMonthAuto() {

		return ld.getMonthValue();
	}

	public int getYearAuto() {

		return ld.getYear();
	}

	public int getHourAuto() {
		return lt.getHour();
	}

	public int getMinuteAuto() {
		return lt.getMinute();
	}

	public int getSecondAuto() {
		return lt.getSecond();
	}
}
