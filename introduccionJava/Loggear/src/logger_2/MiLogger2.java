package logger_2;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.*;
/**
 * Es parecido la versión anterior, pero el objeto milog ahora es una 
 * variable global para que nos permita crear los métodos de log
 * @author Álvaro Blanco Sanginés
 *
 */
public class MiLogger2 {
	static String nombredelaclase;
	static 	Logger milog ;
	/**
	 * 
	 * @param nombre
	 * @param conMensajeEnConsola
	 * @return Básicamente nos devuelve un logger para funcionar un log
	 */
	public static Logger crearLog(String nombre , boolean conMensajeEnConsola) {
		//El nombre de la clase la llevamos dentro del fichero
		nombredelaclase = nombre;
		try {	 
			 milog = Logger.getLogger("MILOG");
			String fecha = new SimpleDateFormat("dd-MM-YYYY_").format(Calendar.getInstance().getTime());
			
			String nombreDelFicheroDeLog  ="LOG_"+ fecha + ".log";
			FileHandler gestorDeFicherosDeLog = new FileHandler("Ficheros/"+nombreDelFicheroDeLog, true);
			
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
	
	private static  class MIFormato extends Formatter {
		@Override
		public String format(LogRecord record) {
		   String fecha = new SimpleDateFormat("dd-MM-YYYY_hh:mm:ss ").format(Calendar.getInstance().getTime());
		   String formato = nombredelaclase + " \\ " + fecha + " " + record.getLevel() + " : " + record.getMessage() +"\n";
		   return formato;
		}
	}
	/**
	 * Llamamos este método desde esta clase
	 * @param mensaje
	 */
	public static void logError(String mensaje) {
		milog.log(Level.SEVERE,mensaje);
	}
	/**
	 * Llamamos este método desde esta clase
	 * @param mensaje
	 */
	public static void logInfo(String mensaje) {
		milog.log(Level.INFO,mensaje);
	}
	/**
	 * Llamamos este método desde esta clase
	 * @param mensaje
	 */
	public static void logWarning(String mensaje) {
		milog.log(Level.WARNING, mensaje);
	}
	
}
