package com.futbol.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import java.util.logging.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;

public class MiLogger {
	static private String nombredelaclase;
	static private Logger milog;
	static private String nombreCarpeta = "Ficheros/";
	static private boolean generarFichero = false;

	public static String getNombreCarpeta() {
		return nombreCarpeta;
	}

	public static void setNombreCarpeta(String nombreCarpeta) {
		MiLogger.nombreCarpeta = nombreCarpeta;
	}

	public static void main(String[] args) {
		deleteFile(nombreCarpeta);
	}

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
			FileHandler gestorDeFicherosDeLog = new FileHandler(getNombreCarpeta() + nombreDelFicheroDeLog, generarFichero);

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

	private static void deleteFile(String carpeta) {
		File folder = new File(carpeta);
		File[] files = folder.listFiles();

		if (files != null) {
			for (File file : files) {
				try {
					if (file.isFile()) {
						// Convertir a Path para usar la clase Files de NIO
						Path filePath = (Path) file.toPath();

						// Establecer permisos para asegurarse de que el archivo puede ser eliminado
						// (solo en sistemas POSIX)
						try {
							Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-rw-rw-");
							if (perms != null) {
								Files.setPosixFilePermissions((java.nio.file.Path) filePath, perms);
							}

						} catch (UnsupportedOperationException e) {
							// Manejar excepción en caso de que el sistema no soporte POSIX (por ejemplo, en
							// Windows)
							System.err.println(
									"No se pueden establecer permisos POSIX en este sistema: " + e.getMessage());
						}

						// Eliminar el archivo
						Files.delete((java.nio.file.Path) filePath);
						System.out.println("El fichero " + file.getName() + " fue eliminado con éxito");
					}
				} catch (IOException e) {
					System.err.println("Fallo al eliminar el fichero " + file.getName() + ". Razón: " + e.getMessage());
				}
			}
		} else {
			System.err.println("No hay ningún fichero o no existe la carpeta " + carpeta);
		}
	}
}
