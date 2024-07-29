package com.futbol.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.*;

public class MiLogger {
	static String nombredelaclase;
	static Logger milog;

	public MiLogger(String nombre, boolean conMensajeEnConsola) {
		crearLog(nombre, conMensajeEnConsola);
	}

	private static Logger crearLog(String nombre, boolean conMensajeEnConsola) {
		// El nombre de la clase la llevamos dentro del fichero
		nombredelaclase = nombre;
		try {
			milog = Logger.getLogger("Mensaje personalizado");
			String fecha = new SimpleDateFormat("dd-MM-YYYY_").format(Calendar.getInstance().getTime());

			String nombreDelFicheroDeLog = "LOG_" + fecha + ".log";
			FileHandler gestorDeFicherosDeLog = new FileHandler("Ficheros/" + nombreDelFicheroDeLog, true);

			MIFormato miformato = new MIFormato();
			gestorDeFicherosDeLog.setFormatter(miformato);
			milog.addHandler(gestorDeFicherosDeLog);

			milog.setUseParentHandlers(conMensajeEnConsola);

			return milog;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static class MIFormato extends Formatter {
		@Override
		public String format(LogRecord record) {
			String fecha = new SimpleDateFormat("dd-MM-YYYY_hh:mm:ss ").format(Calendar.getInstance().getTime());
			String formato = nombredelaclase + " \\ " + fecha + " " + record.getLevel() + " : " + record.getMessage()
					+ "\n";
			return formato;
		}
	}

	public void logError(String mensaje) {
		milog.log(Level.SEVERE, mensaje);
	}

	public void logInfo(String mensaje) {
		milog.log(Level.INFO, mensaje);
	}

	public void logWarning(String mensaje) {
		milog.log(Level.WARNING, mensaje);
	}
}
