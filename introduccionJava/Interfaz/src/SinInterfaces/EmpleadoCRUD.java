package SinInterfaces;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoCRUD {
	/*
	 * CREATE RETIEVE/READ UPDATE DELETE
	 */
	// Estructura de datos
	List<Empleado> empleados = new ArrayList<>();

	// ---Estas son las operaciones CRUD

	// CREATE - Guardar empleado
	public void guardar(Empleado empleado) {
		empleados.add(empleado);
	}

	// RETIEVE/READ - Consultar empleado
	// Hay dos maneras de hacerlo
	public List<Empleado> buscarTodosEmpleados() {
		return empleados;
	}

	public List<Empleado> buscarTodosEmpleados2() {
		List<Empleado> empleados2 = new ArrayList<>();
		return empleados2;
	}

	// UPDATE -Modificar empleado

	
	//DELETE -Borra empleado
}
