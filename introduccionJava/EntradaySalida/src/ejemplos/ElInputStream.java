package ejemplos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ElInputStream {

	static final String ubicacion = "Ficheros/primerEjemplo.txt";

	public static void main(String[] args) {

//		primerEjemplo(ubicacion);
		segundoEjemplo(ubicacion);
	}

	public static void primerEjemplo(String ubicacion) {
		InputStream fichero = null;

		try {
			fichero = new FileInputStream(ubicacion);

			byte[] datos = fichero.readAllBytes();

			for (byte dato : datos) {
				System.out.print((char) dato);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fichero.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void segundoEjemplo(String ubicacion) {
		InputStream fichero = null;
		try {
			fichero = new FileInputStream(ubicacion);

			int dato = fichero.read();
			while (dato != -1) {
				System.out.print((char) dato);
				dato = fichero.read();// Actualizar la variable dato cada iteración de un bucle
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fichero.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
