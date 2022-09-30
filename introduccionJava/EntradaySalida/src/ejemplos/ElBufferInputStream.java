package ejemplos;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ElBufferInputStream {
	
	static final String ubicacion = "Ficheros/SegundoEjemplo.txt";

	public static void main(String[] args) {
		
		primerEjemplo(ubicacion);
		
	}
	
	public static void primerEjemplo(String ubicacion) {
		InputStream fichero = null;
		BufferedInputStream ficheroBuffer = null;
		

		try {
			fichero = new FileInputStream(ubicacion);
			ficheroBuffer = new BufferedInputStream(fichero);
			int dato = ficheroBuffer.read();
			while (dato!=-1) {
				System.out.print((char)dato);
				dato = ficheroBuffer.read();
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

}
