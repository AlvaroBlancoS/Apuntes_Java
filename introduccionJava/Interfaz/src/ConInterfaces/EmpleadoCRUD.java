package ConInterfaces;

import java.util.List;

import SinInterfaces.Empleado;
/*
 * Se declaran los metodos, no se implementan
 * Actua como un contrato, dice lo que hay que hacer per no lo hace
 */

public interface EmpleadoCRUD {
	
	void guardar(Empleado empleado);
	
	List<Empleado> buscarTodosEmpleados();

	void delete(Empleado empleado);
}

