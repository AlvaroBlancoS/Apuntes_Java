package ejemplos;

import java.io.File;
import java.nio.file.Paths;

public class BuscarFicheros {

	public static void main(String[] args) {
		
//		 System.out.println(buscar("primerEjemplo.txt", Paths.get(".").toFile()));
		 
		 if (encontrar("SegundoEjemplo.txt", Paths.get(".").toFile())) {
			System.out.println("Existe");
		}else {
			System.out.println("No existe");
		}
	}
	
	private static boolean encontrar(String buscarArchivo, File directorio) {
		File[] archivos = directorio.listFiles();
		for (File file : archivos) {			
	        if (file.isDirectory()) {
	            File busquedaResultado = buscar(buscarArchivo, file);
	            if (busquedaResultado != null) {
	                return true;
	            }
	        }
		}
		return false;
	}
	
	private static File buscar(String archivoABuscar, File directorio) {
	    File[] archivos = directorio.listFiles();
	    for (File archivo : archivos) {
	        if (archivo.getName().equals(archivoABuscar)) {
	            return archivo;
	        }
	        if (archivo.isDirectory()) {
	            File resultadoRecursion = buscar(archivoABuscar, archivo);
	            if (resultadoRecursion != null) {
	                return resultadoRecursion;
	            }
	        }
	    }
	    return null;
	}


}

