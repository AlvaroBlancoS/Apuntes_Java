package com.futbol;

import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.futbol.control", "com.futbol.model", "com.futbol.service"})
public class FutbolV1Application {
	public static void main(String[] args) {

		switch (1) {
		case 1:
			SpringApplication.run(FutbolV1Application.class, args);
			break;
		case 2:
			deleteFile("ficheros/");
			break;
		default:
			break;
		}

	}

	private static void deleteFile(String carpeta) {
		File folder = new File(carpeta);
		File[] files = folder.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
					// Eliminar el archivo
					if (file.delete()) {
						System.out.println("El fichero " + file.getName() + " fue eliminado con exito");
					} else {
						System.err.println("El fichero " + file.getName() + " no fue eliminado");
					}

				}
			}
		} else {
			System.err.println("No hay ningun fichero o no existe la carpeta " + carpeta);
		}
	}
}
