package ejemplos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class ElPrintStream {
	static File file = null;

	public static void main(String[] args) {
//		crearYescribirFichero("Nuevo fichero", "Hola Juanito, me lleamo Alvarito");
		copiarPegar("prueba.txt");

	}

	public static void crearYescribirFichero(String nombreFichero, String frase) {
		file = new File("Ficheros/" + nombreFichero + ".txt");
		try {
			try (PrintStream info = new PrintStream(file)) {
				info.println(frase);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void copiarPegar(String nombreFichero) {
		file = new File("Ficheros/" + nombreFichero);
		if (!(file.exists())) {
			System.err.println("Este fichero no existe");
			return;
		}
		InputStream in = null;
		PrintStream out = null;
		try {
			in = new FileInputStream(file);
			byte[] datos = in.readAllBytes();
			out = new PrintStream("Ficheros/" + nombreFichero.substring(0, nombreFichero.length() - 4) + "Copy.txt");
			out.write(datos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void leerFichero() {

	}

}
