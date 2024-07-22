package Modelo;

import java.util.ArrayList;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Primero.Departamentos;
import Primero.Empleados;
import Primero.HibernateUtil;
import Visual.Consultar;
import Visual.GestorDepartamentos;
import Visual.GestorEmpleados;

public class Gestion {
	SessionFactory fabrica = HibernateUtil.getSessionFactory();
	// **********************************************************************************
	// ******************* DEPARTAMENTOS ****************************
	// **********************************************************************************

	public boolean agregarDepartamento(Departamentos dep) {
		Session session = fabrica.openSession();
		Transaction mitransaccion = session.beginTransaction();
		try {
			session.save(dep);
			mitransaccion.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return false;
	}

	public ArrayList<Departamentos> ConsultarTodosLosDepartamentos() {
		Session session = fabrica.openSession();
		Transaction miTransaccion = session.beginTransaction();
		ArrayList<Departamentos> listaDepartamentos = null;
		try {
			Query query = session.createQuery("FROM Departamentos");
			listaDepartamentos = (ArrayList<Departamentos>) query.list();
			miTransaccion.commit();
		} catch (ObjectNotFoundException e) {
		} catch (Exception ex) {
			miTransaccion.rollback();
		}
		session.close();
		return listaDepartamentos;
	}

	public Departamentos ConsultarDepartamento(int numDep) {
		Session session = fabrica.openSession();
		Transaction miTransaccion = session.beginTransaction();
		Departamentos departamentoRepuesta = null;
		try {
			departamentoRepuesta = session.get(Departamentos.class, numDep);
			miTransaccion.commit();
		} catch (Exception e) {
			miTransaccion.rollback();
			e.printStackTrace();
		}
		session.close();
		return departamentoRepuesta;

	}

	public boolean modificarDepartamento(int num, String nombre) {
		Session session = fabrica.openSession();
		Transaction miTransaccion = session.beginTransaction();
		Departamentos dep = null;
		try {
			dep = ConsultarDepartamento(num);
			if (dep != null) {
				dep.setNombreDep(nombre);
				session.update(dep);
				miTransaccion.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			miTransaccion.rollback();
		}
		session.close();

		return false;
	}

	public boolean borrarDepartamento(int numDep) {
		Session session = fabrica.openSession();
		Transaction mitransaccion = session.beginTransaction();
		Departamentos dep = null;
		try {
			dep = ConsultarDepartamento(numDep);
			if (dep != null) {
				session.delete(dep);
				mitransaccion.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			mitransaccion.rollback();
		}
		session.close();
		return false;
	}

	// **********************************************************************************
	// ******************* EMPLEADOS ****************************
	// **********************************************************************************

	public ArrayList<Empleados> ConsultarEmpleadosPorNumDep(int numDep) {
		Session session = fabrica.openSession();
		Transaction miTransaccion = session.beginTransaction();
		ArrayList<Empleados> listaEmpleados = null;
		try {
			Query query = session.createQuery("FROM Empleados WHERE id_depEm = :idDepBuscado");
			query.setParameter("idDepBuscado", numDep);
			listaEmpleados = (ArrayList<Empleados>) query.list();
			miTransaccion.commit();
		} catch (ObjectNotFoundException e) {
			System.out.println();
		} catch (Exception ex) {
			ex.printStackTrace();
			miTransaccion.rollback();
		}
		session.close();
		return listaEmpleados;
	}

	public boolean agregarEmpleados(Empleados ep) {
		boolean resultado = false;
		Session session = fabrica.openSession();
		Transaction mitransaccion = session.beginTransaction();

		try {
			session.save(ep);
			mitransaccion.commit();
			resultado = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			mitransaccion.rollback();
		}
		session.close();
		return resultado;
	}
	public Empleados consultarEmpleadoPorId(int idEmpleado) {
		Session session = fabrica.openSession();
		Transaction miTransaccion = session.beginTransaction();
		Empleados empleadoRespuesta = null;
		try {
			empleadoRespuesta = session.get(Empleados.class, idEmpleado);
			miTransaccion.commit();

		} catch (ObjectNotFoundException ex) {
			System.out.println("No hay ningún empleado");
		} catch (Exception e) {
			miTransaccion.rollback();
			e.printStackTrace();
		}
		session.close();

		return empleadoRespuesta;
	}

	public Empleados consultarEmpleadoPorDni(String dni) {
		Session session = fabrica.openSession();
		Transaction miTransaccion = session.beginTransaction();
		ArrayList<Empleados> listaEmpleados = null;
		try {
			Query query = session.createQuery("FROM Empleados WHERE dni = :dniBuscado");
			query.setParameter("dniBuscado", dni);
			listaEmpleados = (ArrayList<Empleados>) query.list();
			if (listaEmpleados.size() > 0) {
				return listaEmpleados.get(0);
			}
			miTransaccion.commit();
		} catch (ObjectNotFoundException ex) {
			System.out.println("No hay ningún empleado");
		} catch (Exception e) {
			miTransaccion.rollback();
		}
		session.close();

		return null;
	}

	public boolean borrarUnEmpleadoPorDni(String dni) {
		Session session = fabrica.openSession();
		Transaction mitransaccion = session.beginTransaction();
		Empleados empleado = null;
		try {
			empleado = consultarEmpleadoPorDni(dni);
			if (empleado != null) {
				session.delete(empleado);
				mitransaccion.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			mitransaccion.rollback();
		}
		session.close();

		return false;
	}
	
	public boolean borrarUnEmpleadoPorId(int idEmpleado){
		Session session = fabrica.openSession();
		Transaction mitransaccion = session.beginTransaction();
		Empleados empleado = null;
		try {
			empleado = consultarEmpleadoPorId(idEmpleado);
			if (empleado != null) {
				session.delete(empleado);
				mitransaccion.commit();
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mitransaccion.rollback();
		}
		
		return false;
	}

	public boolean cambiarDepartamentoPorDNI(String dni, int numDep) {
		Session session = fabrica.openSession();
		Transaction miTransaccion = session.beginTransaction();
		Empleados empleado = null;
		try {
			empleado = consultarEmpleadoPorDni(dni);
			if (empleado != null) {
				Departamentos dep = ConsultarDepartamento(numDep);
				if (dep != null) {
					empleado.setDepartamentos(dep);
					session.update(empleado);
					miTransaccion.commit();
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			miTransaccion.rollback();
		}
		session.close();

		return false;
	}

	public boolean cambiarDepartamentoPorID(int idEmpleado, int numDep) {
		Session session = fabrica.openSession();
		Transaction miTransaccion = session.beginTransaction();
		Empleados empleado = null;
		try {
			empleado = consultarEmpleadoPorId(idEmpleado);
			if (empleado != null) {
				Departamentos dep = ConsultarDepartamento(numDep);
				if (dep != null) {
					empleado.setDepartamentos(dep);
					session.update(empleado);
					miTransaccion.commit();
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			miTransaccion.rollback();
		}
		session.close();

		return false;
	}
	
	public boolean modificarEmpleado(Empleados modificarEmpleado) {
		Session session = fabrica.openSession();
		Transaction miTransaccion = session.beginTransaction();
		try {
			if (modificarEmpleado != null) {
				session.update(modificarEmpleado);
				miTransaccion.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			miTransaccion.rollback();
		}
		session.close();
		return false;
	}

	public boolean modificarNombre(String dni, String nombre) {
		Session session = fabrica.openSession();
		Transaction miTransaccion = session.beginTransaction();
		Empleados empleado = null;
		try {
			empleado = consultarEmpleadoPorDni(dni);
			if (empleado != null) {
				empleado.setNombre(nombre);
				session.update(empleado);
				miTransaccion.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			miTransaccion.rollback();
		}
		session.close();

		return false;
	}

	public boolean modificarApellido(String dni, String apellidos) {
		Session session = fabrica.openSession();
		Transaction miTransaccion = session.beginTransaction();
		Empleados empleado = null;
		try {
			empleado = consultarEmpleadoPorDni(dni);
			if (empleado != null) {
				empleado.setApellidos(apellidos);
				session.update(empleado);
				miTransaccion.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			miTransaccion.rollback();
		}
		session.close();

		return false;
	}

	public boolean modificarDniPorId(int idEmpleado, String dniEmpleado){
		Session session = fabrica.openSession();
		Transaction miTransaccion = session.beginTransaction();
		Empleados empleado = null;
		try {
			empleado = consultarEmpleadoPorId(idEmpleado);
			if (empleado !=null) {
				empleado.setDni(dniEmpleado);
				session.update(empleado);
				miTransaccion.commit();
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			miTransaccion.rollback();
		}
		session.close();
		return false;
	}
	public boolean modificarSalario(String dni, double salario) {
		Session session = fabrica.openSession();
		Transaction miTransaccion = session.beginTransaction();
		Empleados empleado = null;
		try {
			empleado = consultarEmpleadoPorDni(dni);
			if (empleado != null) {
				empleado.setSalario(salario);
				session.update(empleado);
				miTransaccion.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			miTransaccion.rollback();
		}
		session.close();
		return false;
	}
	
	
	//No sé dónde tengo que colocar
	public void cerrarHibernate() {
		fabrica.close();
	}


	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
		switch (1) {
		case 1:
			new Consultar();
			break;
		case 2:
			new GestorDepartamentos();
			break;
		case 3:
			new GestorEmpleados();
			break;

		default:
			break;
		}
		
	}
	

}
