package services;


/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */

public interface EmpleadosService {

	public void visualizar(int opcion);

	public void registrarEmpleado();
	
	public void registrarGerente();
	
	public void registrarDirector();

	public void borrar();

	public void cambiarDepartamento();
	
	public void empleadoAgerente();
	
	public void directorAgerente();
	
	public void empleadoAdirector();
	
	public void gerenteAdirector();
	
	public void directorAEmpleado();
	
	public void gerenteAEmpleado();
	
	

}
