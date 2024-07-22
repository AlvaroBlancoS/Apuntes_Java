package Visual;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.text.DefaultEditorKit;

import Modelo.Gestion;
import Primero.Departamentos;
import Primero.Empleados;

public class GestorEmpleados {
	private JFrame ventana = new JFrame();
	private Container contenedor = ventana.getContentPane();
	private ImageIcon imagenMenu = new ImageIcon("Carpetas/img" + File.separator + "empleados.png");
	final JPopupMenu popupMenu = new JPopupMenu();
	// Imagen de copiar
	private Image copyImage = new ImageIcon("Carpetas/img/copy.png").getImage();
	private ImageIcon copyIcono = new ImageIcon(copyImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	// Imagen de cortar
	private Image cutImage = new ImageIcon("Carpetas/img/cut.png").getImage();
	private ImageIcon cutIcono = new ImageIcon(cutImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	// Imagen de pegar
	private Image pasteImage = new ImageIcon("Carpetas/img/paste.png").getImage();
	private ImageIcon pasteIcono = new ImageIcon(pasteImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	// Creamos tres elementos del menú
	private JMenuItem cortar, copiar, pegar;
	private JLabel titulo = new JLabel("GESTIÓN DE EMPLEADOS");
	private JPanel panelTitulo = new JPanel();
	private Font negrita = new Font("Arial", Font.BOLD, 25);
	private Font fuente = new Font("calibri", Font.PLAIN, 20);
	private JPanel panelCampos = new JPanel();
	private JLabel labelNombre = new JLabel("Nombre: ");
	private JTextField textNombre = new JTextField();
	private JLabel labelApellido = new JLabel("Apellidos: ");
	private JTextField textApellido = new JTextField();
	private JLabel labelDni = new JLabel("DNI: ");
	private JTextField textDni = new JTextField();
	private JLabel labelSalario = new JLabel("Salario: ");
	private JTextField textSalario = new JTextField();
	private JLabel labelDep = new JLabel("N Departamento");
	private JLabel lIdEmpleado = new JLabel("ID Empleado");
	private JTextField textIdEmpleado = new JTextField();
	private JComboBox<String> comboDep = new JComboBox();
	/*-----*/
	private JPanel panelBotones = new JPanel();
	private JButton bInsertar = new JButton("Insertar");
	private JButton bBorrar = new JButton("Borrar");
	private JButton bModificar = new JButton("Modificar");
	private JButton bCambioDpto = new JButton("Cambio Dpto.");
	private JTextField textDpto = new JTextField(10);
	private JMenuBar menuBar;
	private JMenuItem modificarDni;
	private JMenu archivo;
	private Gestion g = new Gestion();
	private ArrayList<Departamentos> listaDepartamentos;

	public GestorEmpleados() {
		raton();
		menu();
		formulario();
		botones();
		leerDatosDepartamentos();
		activarListeners();
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventana.setSize(700, 500);
		ventana.setLocation(600, 200);
		ventana.setResizable(false);
		ventana.setTitle("Gestion de empleados");
		ventana.setVisible(true);
		ventana.setIconImage(imagenMenu.getImage());
	}

	public void menu() {
		menuBar = new JMenuBar();
		ventana.setJMenuBar(menuBar);
		archivo = new JMenu("Modificar...");
		archivo.setMnemonic(KeyEvent.VK_M);
		menuBar.add(archivo);

		modificarDni = new JMenuItem("DNI");
		modificarDni.setMnemonic(KeyEvent.VK_D);
		archivo.add(modificarDni);
	}

	public void formulario() {
		contenedor.setLayout(new BorderLayout(0, 0));
		GridBagConstraints restricciones = new GridBagConstraints();
		// ------ PANEL DE TITULO------
		panelTitulo.setLayout(new GridBagLayout());
		titulo.setFont(negrita);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 0;
		restricciones.gridy = 0;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(10, 0, 0, 0);
		panelTitulo.add(titulo, restricciones);
		contenedor.add(panelTitulo, BorderLayout.NORTH);
		// ------- PANEL DE CAMPOS----
		panelCampos.setLayout(new GridBagLayout());
		// ID empleado
		lIdEmpleado.setFont(fuente);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 0;
		restricciones.gridy = 0;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(0, 0, 0, 0);
		panelCampos.add(lIdEmpleado, restricciones);
		textIdEmpleado.setFont(fuente);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 1;
		restricciones.gridy = 0;
		restricciones.ipady = 0;
		restricciones.ipadx = 120;
		restricciones.insets = new Insets(0, 0, 0, 0);
		panelCampos.add(textIdEmpleado, restricciones);
		// DNI
		labelDni.setFont(fuente);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 2;
		restricciones.gridy = 0;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(0, 10, 0, 0);
		panelCampos.add(labelDni, restricciones);
		textDni.setFont(fuente);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 3;
		restricciones.gridy = 0;
		restricciones.ipady = 0;
		restricciones.ipadx = 120;
		restricciones.insets = new Insets(0, -70, 0, 80);
		panelCampos.add(textDni, restricciones);
		// Nombre
		labelNombre.setFont(fuente);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 0;
		restricciones.gridy = 1;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(20, 0, 0, 0);
		panelCampos.add(labelNombre, restricciones);
		textNombre.setFont(fuente);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 1;
		restricciones.gridy = 1;
		restricciones.ipady = 0;
		restricciones.ipadx = 120;
		restricciones.insets = new Insets(20, 0, 0, 0);
		panelCampos.add(textNombre, restricciones);
		// Apellidos
		labelApellido.setFont(fuente);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 2;
		restricciones.gridy = 1;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(20, 10, 0, 0);
		panelCampos.add(labelApellido, restricciones);
		textApellido.setFont(fuente);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 3;
		restricciones.gridy = 1;
		restricciones.ipady = 0;
		restricciones.ipadx = 120;
		restricciones.insets = new Insets(20, -70, 0, 80);
		panelCampos.add(textApellido, restricciones);
		// SALARIO
		labelSalario.setFont(fuente);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 0;
		restricciones.gridy = 2;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(20, 5, 0, 0);
		panelCampos.add(labelSalario, restricciones);
		textSalario.setFont(fuente);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 1;
		restricciones.gridy = 2;
		restricciones.ipady = 0;
		restricciones.ipadx = 120;
		restricciones.insets = new Insets(20, 0, 0, 0);
		panelCampos.add(textSalario, restricciones);
		// Numero departamento COMBOBOX
		labelDep.setFont(fuente);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 0;
		restricciones.gridy = 3;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(20, 0, 0, 0);
		panelCampos.add(labelDep, restricciones);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 1;
		restricciones.gridy = 3;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(20, 0, 0, 0);
		panelCampos.add(comboDep, restricciones);
		textDpto.setFont(fuente);
		textDpto.setEnabled(false);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 2;
		restricciones.gridy = 3;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(20, 10, 0, 0);
		panelCampos.add(textDpto, restricciones);
		contenedor.add(panelCampos, BorderLayout.CENTER);
	}

	public void botones() {
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		// Fuentes para botones
		bInsertar.setFont(fuente);
		bBorrar.setFont(fuente);
		bModificar.setFont(fuente);
		bCambioDpto.setFont(fuente);
		panelBotones.add(bInsertar);
		panelBotones.add(bBorrar);
		panelBotones.add(bModificar);
		panelBotones.add(bCambioDpto);
		contenedor.add(panelBotones, BorderLayout.SOUTH);

	}

	public void raton() {
		copiar = new JMenuItem(new DefaultEditorKit.CopyAction());
		copiar.setText("Copiar");
		copiar.setMnemonic(KeyEvent.VK_C);
		copiar.setIcon(copyIcono);
		popupMenu.add(copiar);
		cortar = new JMenuItem(new DefaultEditorKit.CutAction());
		cortar.setText("cortar");
		cortar.setMnemonic(KeyEvent.VK_C);
		cortar.setIcon(cutIcono);
		popupMenu.add(cortar);
		pegar = new JMenuItem(new DefaultEditorKit.PasteAction());
		pegar.setText("pegar");
		pegar.setMnemonic(KeyEvent.VK_P);
		pegar.setIcon(pasteIcono);
		popupMenu.add(pegar);
		textDpto.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == e.BUTTON3)
					popupMenu.show(textDpto, e.getX(), e.getY());
			}
		});
		textNombre.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == e.BUTTON3)
					popupMenu.show(textNombre, e.getX(), e.getY());
			}
		});
		textApellido.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == e.BUTTON3)
					popupMenu.show(textApellido, e.getX(), e.getY());
			}
		});
		textDni.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == e.BUTTON3)
					popupMenu.show(textDni, e.getX(), e.getY());
			}
		});
		textIdEmpleado.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == e.BUTTON3)
					popupMenu.show(textIdEmpleado, e.getX(), e.getY());
			}
		});
		textSalario.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == e.BUTTON3)
					popupMenu.show(textSalario, e.getX(), e.getY());
			}
		});
	}

	public void activarListeners() {
		modificarDni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String idCampo = JOptionPane.showInputDialog("Escribe el id");
				if (idCampo != null) {
					idCampo = (String) idCampo;
					if (!idCampo.isEmpty()) {
						int idEmpleado = Integer.valueOf(idCampo);
						Empleados buscarId = g.consultarEmpleadoPorId(idEmpleado);
						if (buscarId != null) {
							String modificarDni = JOptionPane.showInputDialog("Escribe el dni para modificar");
							if (g.modificarDniPorId(idEmpleado, modificarDni) == true) {
								JOptionPane.showMessageDialog(null, "Ha sido modificado", null,
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null,
										"ERROR: no ha sido modificado, puede que existe el dni", null,
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "El id empleado no existe", null,
									JOptionPane.ERROR_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(null, "Tienes que rellenar", null, JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Hasta pronto!!!", null, JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		comboDep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ordinal = comboDep.getSelectedIndex();
				if (comboDep.getSelectedIndex() != 0) {
					Departamentos d = listaDepartamentos.get(ordinal - 1);
					textDpto.setText(d.getNombreDep());
				} else {
					if (comboDep.getSelectedItem().toString().isEmpty()) {
						textDpto.setText("");
					}

				}

			}
		});
		bInsertar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertarEmpleado();
			}
		});

		bModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modificarEmpleado();
			}
		});

		bCambioDpto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiarDepartamento();
			}
		});

		bBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				borrarEmpleado();
			}
		});

	}


	public void leerDatosDepartamentos() {
		comboDep.addItem("");
		listaDepartamentos = g.ConsultarTodosLosDepartamentos();
		for (Departamentos departamentos : listaDepartamentos) {
			String convertir = String.valueOf(departamentos.getIdDep());
			comboDep.addItem(convertir);
		}
	}

	public void insertarEmpleado() {

		if (textNombre.getText().isEmpty() || textApellido.getText().isEmpty() || textSalario.getText().isEmpty()
				|| textDni.getText().isEmpty() || textIdEmpleado.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tienes que rellenar todos los campos", null,
					JOptionPane.ERROR_MESSAGE);
		} else {
			if (comboDep.getSelectedItem().toString().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Tienes que seleccionar un numero departamento", null,
						JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					String nombre = textNombre.getText();
					String apellidos = textApellido.getText();
					String dni = textDni.getText();
					int idEmpleado = Integer.valueOf(textIdEmpleado.getText());
					double salario = Double.valueOf(textSalario.getText());
					Empleados buscarId = g.consultarEmpleadoPorId(idEmpleado);
					int ordinal = comboDep.getSelectedIndex();
					if (buscarId != null) {
						JOptionPane.showMessageDialog(null, "Existe el id empleado", null, JOptionPane.ERROR_MESSAGE);
					} else {
						if (!validarDni(dni)) {
							JOptionPane.showMessageDialog(null, "El dni no es válido", null,
									JOptionPane.WARNING_MESSAGE);
						} else {
							Empleados buscarDni = g.consultarEmpleadoPorDni(dni);
							if (buscarDni != null) {
								JOptionPane.showMessageDialog(null, "Existe el dni", null, JOptionPane.ERROR_MESSAGE);
							} else {
								// Es donde seleccionamos el número departamento
								Departamentos dep = listaDepartamentos.get(ordinal - 1);
								Empleados insertarEmpleado = new Empleados(idEmpleado, dep, nombre, apellidos,
										dni.toUpperCase(), salario);
								if (g.agregarEmpleados(insertarEmpleado) == true) {
									JOptionPane.showMessageDialog(null, "Ha sido insertado con éxito", null,
											JOptionPane.INFORMATION_MESSAGE);
									limpiar();
								} else {
									JOptionPane.showMessageDialog(null, "Error de insertar", null,
											JOptionPane.ERROR_MESSAGE);
								}

							}
						}
					}

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,
							"El campo id empleado y/o el campo de salario solo está permitido en números", null,
							JOptionPane.ERROR_MESSAGE);
				}

			}

		}

	}

	public void modificarEmpleado() {
		if (!(textDni.getText().isEmpty() || textIdEmpleado.getText().isEmpty())) {
			JOptionPane.showMessageDialog(null,
					"No puedes introducir el id y el dni del empleado al mismo tiempo, debes introducir el campo id o campo dni",
					null, JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (textNombre.getText().isEmpty() && textApellido.getText().isEmpty() && textSalario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debes modificar algún campo", null, JOptionPane.ERROR_MESSAGE);
		} else {
			if (textDni.getText().isEmpty() && textIdEmpleado.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"Para cambiar modificar, debes introducir el id o el dni del empleado ", null,
						JOptionPane.ERROR_MESSAGE);
			} else {
				String nombre = textNombre.getText();
				String apellidos = textApellido.getText();
				Double salario;
				String mensaje = "Ha sido modificado: ";

				// buscamos por id y modificar cualquier campo o ambos campos
				if (!textIdEmpleado.getText().isEmpty()) {
					int idEmpleado = Integer.valueOf(textIdEmpleado.getText());
					try {
						Empleados buscarId = g.consultarEmpleadoPorId(idEmpleado);
						if (buscarId != null) {
							if (!nombre.isEmpty()) {
								buscarId.setNombre(nombre);
								mensaje = mensaje + " nombre";
								textNombre.setText("");
								textIdEmpleado.setText("");
							}
							if (!apellidos.isEmpty()) {
								buscarId.setApellidos(apellidos);
								mensaje = mensaje + " apellidos";
								textApellido.setText("");
								textIdEmpleado.setText("");

							}
							if (!textSalario.getText().isEmpty()) {
								salario = Double.valueOf(textSalario.getText());
								buscarId.setSalario(salario);
								mensaje = mensaje + " salario";
								textSalario.setText("");
								textIdEmpleado.setText("");
							}

							if (g.modificarEmpleado(buscarId) == true) {
								JOptionPane.showMessageDialog(null, mensaje, null, JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "ERROR: No se puede modificar", null,
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "No existe el id empleado", null,
									JOptionPane.ERROR_MESSAGE);
						}

					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null,
								"El campo id empleado y/o el campo de salario solo está permitido en números",
								null, JOptionPane.ERROR_MESSAGE);
					}
				}
				// buscamos por dni y modificar cualquier campo o ambos campos
				if (!textDni.getText().isEmpty()) {
					String dni = textDni.getText();
					try {
						Empleados buscarDni = g.consultarEmpleadoPorDni(dni);
						if (!validarDni(dni)) {
							JOptionPane.showMessageDialog(null, "El dni no es válido", null,
									JOptionPane.WARNING_MESSAGE);
						} else {
							if (buscarDni != null) {
								if (!nombre.isEmpty()) {
									buscarDni.setNombre(nombre);
									mensaje = mensaje + " nombre";
									textNombre.setText("");
									textDni.setText("");
								}
								if (!apellidos.isEmpty()) {
									buscarDni.setApellidos(apellidos);
									mensaje = mensaje + " apellidos";
									textApellido.setText("");
									textDni.setText("");

								}
								if (!textSalario.getText().isEmpty()) {
									salario = Double.valueOf(textSalario.getText());
									buscarDni.setSalario(salario);
									mensaje = mensaje + " salario";
									textSalario.setText("");
									textDni.setText("");
								}

								if (g.modificarEmpleado(buscarDni) == true) {
									JOptionPane.showMessageDialog(null, mensaje, null, JOptionPane.INFORMATION_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(null, "ERROR: No se puede modificar", null,
											JOptionPane.ERROR_MESSAGE);
								}

							} else {
								JOptionPane.showMessageDialog(null, "No existe el dni empleado", null,
										JOptionPane.ERROR_MESSAGE);
							}
						}

					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null,
								"El campo de salario solo está permitido de introducir números", null,
								JOptionPane.ERROR_MESSAGE);
					}

				}

			}

		}

	}

	public void cambiarDepartamento() {
		boolean confirmar = false;
		if (!(textDni.getText().isEmpty() || textIdEmpleado.getText().isEmpty())) {
			JOptionPane.showMessageDialog(null,
					"No puedes introducir el id y el dni del empleado al mismo tiempo, debes introducir uno", null,
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (comboDep.getSelectedItem().toString().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tienes que seleccionar un numero departamento", null,
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (textDni.getText().isEmpty() && textIdEmpleado.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Para cambiar el departamento, debes introducir el id o el dni del empleado ", null,
					JOptionPane.ERROR_MESSAGE);
		} else {
			int ordinal = comboDep.getSelectedIndex();
			// Buscar por dni y cambiar departamento
			if (!textDni.getText().isEmpty()) {
				String dni = textDni.getText();
				if (!validarDni(dni)) {
					JOptionPane.showMessageDialog(null, "El DNI no es válido", null, JOptionPane.ERROR_MESSAGE);
				} else {
					Empleados buscarDni = g.consultarEmpleadoPorDni(dni);
					if (buscarDni == null) {
						JOptionPane.showMessageDialog(null, "No existe el DNI", null, JOptionPane.ERROR_MESSAGE);
					} else {
						if (g.cambiarDepartamentoPorDNI(dni, ordinal) == true) {
							confirmar = true;
						} else {
							JOptionPane.showMessageDialog(null, "ERROR: No se ha podido cambiar el departamento", null,
									JOptionPane.ERROR_MESSAGE);
						}
						// poner resupeusta
						Empleados emp2 = g.consultarEmpleadoPorDni(dni.toUpperCase());
						if (emp2 != null) {
							JOptionPane.showMessageDialog(null,
									emp2.getNombre() + " " + emp2.getApellidos() + " ahora está en el departamento de "
											+ emp2.getDepartamentos().getNombreDep(),
									null, JOptionPane.INFORMATION_MESSAGE);
						}

					}
				}

			}
			// Buscar por id y cambiar departamento
			if (!textIdEmpleado.getText().isEmpty()) {

				try {
					int idEmpleado = Integer.valueOf(textIdEmpleado.getText());
					Empleados buscarId = g.consultarEmpleadoPorId(idEmpleado);
					if (buscarId == null) {
						JOptionPane.showMessageDialog(null, "No existe el Id de empleado", null,
								JOptionPane.ERROR_MESSAGE);
					} else {
						if (g.cambiarDepartamentoPorID(idEmpleado, ordinal) == true) {
							confirmar = true;
						} else {
							JOptionPane.showMessageDialog(null, "ERROR: No se ha podido cambiar el departamento", null,
									JOptionPane.ERROR_MESSAGE);
						}

					}
					if (confirmar == true) {
						Empleados emp = g.consultarEmpleadoPorId(idEmpleado);
						if (emp != null) {
							JOptionPane.showMessageDialog(null,
									emp.getNombre() + " " + emp.getApellidos() + " ahora está en el departamento de "
											+ emp.getDepartamentos().getNombreDep(),
									null, JOptionPane.INFORMATION_MESSAGE);
						}

					}

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,
							"El campo id empleado solo está permitido en números", null,
							JOptionPane.ERROR_MESSAGE);
				}

			}

		}
	}

	public void borrarEmpleado() {

		if (!(textDni.getText().isEmpty() || textIdEmpleado.getText().isEmpty())) {
			JOptionPane.showMessageDialog(null,
					"No puedes introducir el id y el dni del empleado al mismo tiempo, debes introducir el campo id o campo dni",
					null, JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (textDni.getText().isEmpty() && textIdEmpleado.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Para borrar, tienes que rellenar el campo ID o el campo dni", null,
					JOptionPane.ERROR_MESSAGE);

		} else {

			// buscamos por id y borrar un dato
			if (!textIdEmpleado.getText().isEmpty()) {
				try {
					int idEmpleado = Integer.valueOf(textIdEmpleado.getText());
					Empleados buscarId = g.consultarEmpleadoPorId(idEmpleado);
					if (buscarId != null) {
						int resp = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?");
						if (JOptionPane.OK_OPTION == resp) {
							if (g.borrarUnEmpleadoPorId(idEmpleado) == true) {
								JOptionPane.showMessageDialog(null, "Ha sido borrado", null,
										JOptionPane.INFORMATION_MESSAGE);
								textIdEmpleado.setText("");
							} else {
								JOptionPane.showMessageDialog(null, "ERROR: no puede borrar", null,
										JOptionPane.ERROR_MESSAGE);
							}
						}

					} else {
						JOptionPane.showMessageDialog(null, "El id empleado no existe", null,
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,
							"El campo id empleado solo está permitido en números", null,
							JOptionPane.ERROR_MESSAGE);
				}

			}

			// Buscamos por dni y borrar un dato
			if (!textDni.getText().isEmpty()) {
				String dni = textDni.getText();
				if (!validarDni(dni)) {
					JOptionPane.showMessageDialog(null, "El dni no es válido", null, JOptionPane.ERROR_MESSAGE);
				} else {
					Empleados buscarDni = g.consultarEmpleadoPorDni(dni);
					if (buscarDni != null) {
						int resp = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?");
						if (JOptionPane.OK_OPTION == resp) {
							if (g.borrarUnEmpleadoPorDni(dni) == true) {
								JOptionPane.showMessageDialog(null, "Ha sido borrado", null,
										JOptionPane.INFORMATION_MESSAGE);
								textDni.setText("");
							} else {
								JOptionPane.showMessageDialog(null, "ERROR: no puede borrar", null,
										JOptionPane.ERROR_MESSAGE);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "El dni empleado no existe", null,
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		}

	}

	public void limpiar() {
		textIdEmpleado.setText("");
		textDni.setText("");
		textApellido.setText("");
		textNombre.setText("");
		textSalario.setText("");
	}

	public boolean validarDni(String dni) {
		boolean comprobar = false;
		Pattern patron = Pattern.compile("[0-9]{8}[A-Z a-z]");
		Matcher mat = patron.matcher(dni);
		if (mat.matches()) {
			comprobar = true;
		} else {
			comprobar = false;
		}
		return comprobar;
	}
}
