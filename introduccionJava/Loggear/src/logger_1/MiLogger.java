package logger_1;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */
public class MiLogger {
	static String nombredelaclase;

	/**
	 * 
	 * @param nombre
	 * @param conMensajeEnConsola
	 * @return Básicamente nos devuelve un logger para funcionar un log
	 */
	public static Logger crearLog(String nombre, boolean conMensajeEnConsola) {
		// El nombre de la clase la llevamos dentro del fichero
		nombredelaclase = nombre;
		try {
			Logger milog = Logger.getLogger("MILOG");
			// Creas un simple formato de fecha
			String fecha = new SimpleDateFormat("dd-MM-YYYY_").format(Calendar.getInstance().getTime());
			// Cancatenamos el nombre del fichero
			String nombreDelFicheroDeLog = "LOG_" + fecha + ".log";
			// Creas un fichero y siempre la devolvemos true para saltar lineas.
			FileHandler gestorDeFicherosDeLog = new FileHandler("Ficheros/" + nombreDelFicheroDeLog, true);

			// Pones el formato que has creado para logger
			MIFormato miformato = new MIFormato();
			gestorDeFicherosDeLog.setFormatter(miformato);
			milog.addHandler(gestorDeFicherosDeLog);
			// Si devuelve un true, entonces aparece los mensajes de logger
			milog.setUseParentHandlers(conMensajeEnConsola);

			return milog;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Esta clase nos permitirá crear nuestro formato para escribir dentro del
	 * fichero
	 * 
	 * @autho Álvaro Blanco Sanginés
	 *
	 */
	private static class MIFormato extends Formatter {
		@Override
		public String format(LogRecord record) {
			String fecha = new SimpleDateFormat("dd-MM-YYYY_hh:mm:ss ").format(Calendar.getInstance().getTime());
			String formato = nombredelaclase + " \\ " + fecha + " " + record.getLevel() + " : " + record.getMessage()
					+ "\n";
			return formato;
		}
	}

}
