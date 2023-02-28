package logger_0;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */
public class MainLogger {
	private static Logger miLog = Logger.getLogger(MainLogger.class.toString());
	private static FileHandler fh;

	public static void main(String[] args) {
		try {
			// Creas un fichero apara agregar logger
			fh = new FileHandler("Ficheros/prueba.log");
			miLog.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			// Si devuelve un true, entonces aparece los mensajes de logger
			miLog.setUseParentHandlers(true);
			//Y empiezas introducir unos cuantos logger lo que tu quieras.
			miLog.log(Level.INFO, "Esto es un texto que da información");
			miLog.log(Level.WARNING, "Esto es un texto que da advertencia");
			miLog.log(Level.SEVERE, "Esto es un texto que da error o severo");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
