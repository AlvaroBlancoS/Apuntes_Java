package services;

import org.apache.logging.log4j.Level;

import datos.GestionEmpresa;
import model.Director;
import model.Empleado;
import model.Gerente;
import utilidades.Agregar;

/**
 * 
 * @author Álvaro Blanco Sanginés
 *
 */

public class EmpleadosServiceImpl implements EmpleadosService {
	GestionEmpresa ge = new GestionEmpresa();
	private Empleado em = new Empleado();
	private Gerente g = new Gerente();
	private Director d = new Director();
	private int dia;
	private int mes;
	private int anio;
	private String dni;

	@Override
	public void visualizar(int opcion) {
		switch (opcion) {
		case 1:
			ge.visualizarEmpleados();
			break;
		case 2:
			ge.visualizarGerentes();
			break;
		case 3:
			ge.visualizarDirectores();
			break;

		default:
			Agregar.mensajeEmpleadosService().log(Level.ERROR, "Has seleccionado incorrecto");
			break;
		}
	}

	@Override
	public void registrarEmpleado() {
		em.setDni(Agregar.leerString("Introduzca el DNI: "));
		try {
			if (validarDNI(em.getDni()) == false) {
				throw new ExceptionDNI();
			} else {
				try {
					if (ge.isExistEmploye(em.getDni()) == true) {
						throw new ExceptionEmploye("Ya existe y es un empleado");
					} else if (ge.isExistGerente(em.getDni()) == true) {
						throw new ExceptionEmploye("Ya existe y es un gerente");
					} else if (ge.isExistDirector(em.getDni()) == true) {
						throw new ExceptionEmploye("Ya existe y es un director");
					} else {
						em.setNombre(Agregar.leerString(msg(1)));
						em.setApellidos(Agregar.leerString(msg(2)));
						em.setSalario(Agregar.leerDouble(msg(3)));
						System.out.println(msg(4));
						dia = Agregar.leerEntero(msfFecha(1));
						mes = Agregar.leerEntero(msfFecha(2));
						anio = Agregar.leerEntero(msfFecha(3));
						em.setFecha_nacimiento(Agregar.fecha(dia, mes, anio));
						ge.incluirEmpleados(em);

					}

				} catch (ExceptionEmploye e) {
					e.printStackTrace();
				}

			}

		} catch (ExceptionDNI e) {
			e.printStackTrace();
		}

	}

	@Override
	public void registrarGerente() {
		g.setDni(Agregar.leerString("Introduzca el DNI: "));
		try {
			if (validarDNI(g.getDni()) == false) {
				throw new ExceptionDNI();
			} else {
				try {
					if (ge.isExistEmploye(g.getDni()) == true) {
						throw new ExceptionEmploye("Ya existe y es un empleado");
					} else if (ge.isExistGerente(g.getDni()) == true) {
						throw new ExceptionEmploye("Ya existe y es un gerente");
					} else if (ge.isExistDirector(g.getDni()) == true) {
						throw new ExceptionEmploye("Ya existe y es un director");
					} else {
						g.setNombre(Agregar.leerString(msg(1)));
						g.setApellidos(Agregar.leerString(msg(2)));
						g.setSalario(Agregar.leerDouble(msg(3)));
						System.out.println(msg(4));
						dia = Agregar.leerEntero(msfFecha(1));
						mes = Agregar.leerEntero(msfFecha(2));
						anio = Agregar.leerEntero(msfFecha(3));
						g.setFecha_nacimiento(Agregar.fecha(dia, mes, anio));
						g.setEncargoDep(Agregar.leerString("Introduzca el encargo de departamento"));
						ge.incluirGerentes(g);
					}

				} catch (ExceptionEmploye e) {
					e.printStackTrace();
				}

			}

		} catch (ExceptionDNI e) {
			e.printStackTrace();
		}

	}

	@Override
	public void registrarDirector() {
		d.setDni(Agregar.leerString("Introduzca el DNI: "));
		try {
			if (validarDNI(d.getDni()) == false) {
				throw new ExceptionDNI();
			} else {
				try {
					if (ge.isExistEmploye(d.getDni()) == true) {
						throw new ExceptionEmploye("Ya existe y es un empleado");
					} else if (ge.isExistGerente(d.getDni()) == true) {
						throw new ExceptionEmploye("Ya existe y es un gerente");
					} else if (ge.isExistDirector(d.getDni()) == true) {
						throw new ExceptionEmploye("Ya existe y es un director");
					} else {
						d.setNombre(Agregar.leerString(msg(1)));
						d.setApellidos(Agregar.leerString(msg(2)));
						d.setSalario(Agregar.leerDouble(msg(3)));
						System.out.println(msg(4));
						dia = Agregar.leerEntero(msfFecha(1));
						mes = Agregar.leerEntero(msfFecha(2));
						anio = Agregar.leerEntero(msfFecha(3));
						d.setFecha_nacimiento(Agregar.fecha(dia, mes, anio));
						String respCoche = Agregar.leerString("¿Tiene coche? si|no");
						if (respCoche.equalsIgnoreCase("si") || respCoche.equalsIgnoreCase("s")) {
							d.setCoche(true);
							d.setMatriculaCoche(Agregar.leerString("Introduzca la matricula"));
							ge.incluirDirectores(d);

						} else if (respCoche.equalsIgnoreCase("no") || respCoche.equalsIgnoreCase("n")) {
							if (d.isCoche() == false) {
								ge.incluirDirectores(d);
							}
						} else {
							Agregar.mensajeEmpleadosService().log(Level.ERROR, "Respuesta incorrecta");
						}
					}

				} catch (ExceptionEmploye e) {
					e.printStackTrace();
				}

			}

		} catch (ExceptionDNI e) {
			e.printStackTrace();
		}

	}

	/**
	 * Permite borrar un empleado selecionando el puesto de trabajo e introducir el
	 * dni
	 */
	@Override
	public void borrar() {
		int numeroPuesto = Agregar.leerEntero(borrado());
		dni = Agregar.leerString("Introduzca el dni");
		try {
			if (validarDNI(dni) == false) {
				throw new ExceptionDNI();

			} else {
				String resp = Agregar.leerString("¿Deseas contignuar?");
				if (resp.equalsIgnoreCase("si") || resp.equalsIgnoreCase("s")) {
					if (ge.borrarUnEmpleado(dni, numeroPuesto)) {
						System.out.println("Ha sido borrado con exito");
					} else {
						Agregar.mensajeEmpleadosService().log(Level.ERROR, "No existe el dni");
					}

				} else if (resp.equalsIgnoreCase("no") || resp.equalsIgnoreCase("n")) {
					System.out.println("No ha sido borrado");
				} else {
					Agregar.mensajeEmpleadosService().log(Level.ERROR, "Respuesta incorrecta");
				}

			}

		} catch (ExceptionDNI e) {
			e.printStackTrace();
		}

	}

	@Override
	public void cambiarDepartamento() {
		String dni = null;
		dni = Agregar.leerString("Introduzca el dni");
		if (ge.isExistGerente(dni) == true) {
			if (ge.modificarDepartamento(dni, Agregar.leerString("Introduzca el nuevo departamento"))) {
				System.out.println("Ha sido modificado");
			} else {
				Agregar.mensajeEmpleadosService().log(Level.ERROR, "No ha sido modificado");
			}
		} else {
			Agregar.mensajeEmpleadosService().log(Level.ERROR, "No existe el dni");
		}

	}

	@Override
	public void empleadoAgerente() {
		dni = Agregar.leerString("Introduzca el dni");
		try {
			if (validarDNI(dni) == false) {
				throw new ExceptionDNI();
			} else {
				if (ge.isExistEmploye(dni) == true) {
					g = ge.serGerente(dni, 1, Agregar.leerString("Introduzca el nuevo departamento"));
					ge.incluirGerentes(g);
				} else {
					Agregar.mensajeEmpleadosService().log(Level.ERROR, "No existe el empleado");
				}
			}
		} catch (ExceptionDNI e) {
			e.printStackTrace();
		}

	}

	@Override
	public void directorAgerente() {
		dni = Agregar.leerString("Introduzca el dni");
		try {
			if (validarDNI(dni) == false) {
				throw new ExceptionDNI();
			} else {
				if (ge.isExistDirector(dni) == true) {
					g = ge.serGerente(dni, 2, Agregar.leerString("Introduzca el nuevo departamento"));
					ge.incluirGerentes(g);
				} else {
					Agregar.mensajeEmpleadosService().log(Level.ERROR, "No existe el director");
				}

			}

		} catch (ExceptionDNI e) {
			e.printStackTrace();
		}
	}

	@Override
	public void empleadoAdirector() {
		dni = Agregar.leerString("Introduzca el dni");
		try {
			if (validarDNI(dni) == false) {
				throw new ExceptionDNI();
			} else {
				if (ge.isExistEmploye(dni) == true) {
					String respCoche = Agregar.leerString("¿Tiene coche? si|no");
					if (respCoche.equalsIgnoreCase("si") || respCoche.equalsIgnoreCase("s")) {
						String matricula = Agregar.leerString("Introduzca la matricula del coche");
						d = ge.serDirector(dni, 3, true, matricula);
						ge.incluirDirectores(d);
					} else if (respCoche.equalsIgnoreCase("no") || respCoche.equalsIgnoreCase("n")) {
						d = ge.serDirector(dni, 3, false, null);
						ge.incluirDirectores(d);
					} else {
						Agregar.mensajeEmpleadosService().log(Level.ERROR, "Respuesta incorrecta");
					}
				} else {
					Agregar.mensajeEmpleadosService().log(Level.ERROR, "No existe el empleado");
				}
			}

		} catch (ExceptionDNI e) {
			e.printStackTrace();
		}

	}

	@Override
	public void gerenteAdirector() {
		dni = Agregar.leerString("Introduzca el dni");
		try {
			if (validarDNI(dni) == false) {
				throw new ExceptionDNI();
			} else {
				try {
					if (validarDNI(dni) == false) {
						throw new ExceptionDNI();
					} else {
						if (ge.isExistGerente(dni) == true) {
							String respCoche = Agregar.leerString("¿Tiene coche? si|no");
							if (respCoche.equalsIgnoreCase("si") || respCoche.equalsIgnoreCase("s")) {
								String matricula = Agregar.leerString("Introduzca la matricula del coche");
								d = ge.serDirector(dni, 4, true, matricula);
								ge.incluirDirectores(d);
							} else if (respCoche.equalsIgnoreCase("no") || respCoche.equalsIgnoreCase("n")) {
								d = ge.serDirector(dni, 4, false, null);
								ge.incluirDirectores(d);
							} else {
								Agregar.mensajeEmpleadosService().log(Level.ERROR, "Respuesta incorrecta");
							}

						} else {
							Agregar.mensajeEmpleadosService().log(Level.ERROR, "No existe gerente");
						}
					}

				} catch (ExceptionDNI e) {
					e.printStackTrace();
				}

			}

		} catch (ExceptionDNI e) {
			e.printStackTrace();
		}

	}

	@Override
	public void directorAEmpleado() {
		dni = Agregar.leerString("Introduzca el dni");
		try {
			if (validarDNI(dni) == false) {
				throw new ExceptionDNI();
			} else {
				if (ge.isExistDirector(dni) == true) {
					em = ge.serEmpleado(dni, 5);
					ge.incluirEmpleados(em);

				} else {
					Agregar.mensajeEmpleadosService().log(Level.ERROR, "No existe director");
				}
			}

		} catch (ExceptionDNI e) {
			e.printStackTrace();
		}

	}

	@Override
	public void gerenteAEmpleado() {
		dni = Agregar.leerString("Introduzca el dni");
		try {
			if (validarDNI(dni) == false) {
				throw new ExceptionDNI();
			} else {
				if (ge.isExistGerente(dni) == true) {
					em = ge.serEmpleado(dni, 6);
					ge.incluirEmpleados(em);
				} else {
					Agregar.mensajeEmpleadosService().log(Level.ERROR, "No existe gerente");
				}
			}

		} catch (ExceptionDNI e) {
			e.printStackTrace();
		}

	}

	private static String msg(int opcion) {
		return "Introduzca " + (opcion == 1 ? "un nombre"
				: (opcion == 2) ? "apellidos"
						: (opcion == 3) ? "Un salario" : (opcion == 4) ? "la fecha de nacimiento" : "el dni");
	}

	public static String borrado() {
		return "--Opciones---\n1-Eliminar un empleado\n2-Eliminar un gerente\n3-Eliminar un director";
	}

	private static String msfFecha(int opcion) {
		return (opcion == 1 ? "Dia =>" : (opcion == 2) ? "Mes =>" : "Anio =>");
	}

	private static boolean validarDNI(String document) {
		char[] letras = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
				'L', 'C', 'K', 'E' };
		int caracteres = 9;
		boolean isValido = true;
		boolean tieneLetra = false;
		char[] miLetra = document.toUpperCase().toCharArray();
		if (caracteres == document.length()) {
			// Averiguar si tiene letra
			for (int i = 0; i < letras.length; i++) {
				if (letras[i] == miLetra[8]) {
					tieneLetra = true;
				}
			}
			if (tieneLetra == true) {
				// Tiene letra
				// Calcular el resto
				int calcularDNI = Integer.valueOf(document.substring(0, document.length() - 1)) % 23;
				if (miLetra[8] == letras[calcularDNI]) {
					// Es un dni de verdad
					isValido = true;
				} else {
					isValido = false;
				}

			} else {
				isValido = false;
			}
		} else {
			isValido = false;
		}

		return isValido;
	}

}
