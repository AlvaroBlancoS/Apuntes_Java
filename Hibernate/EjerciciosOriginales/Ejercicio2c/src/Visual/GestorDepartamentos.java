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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.text.DefaultEditorKit;

import Modelo.Gestion;
import Primero.Departamentos;
import Primero.Empleados;

public class GestorDepartamentos {
	private JFrame ventana = new JFrame();
	private Container contenedor = ventana.getContentPane();
	final JPopupMenu popupMenu = new JPopupMenu();
	private ImageIcon imagenMenu = new ImageIcon("Carpetas/img" + File.separator + "departamento.png");
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
	private JLabel titulo = new JLabel("GESTIÓN DE DEPARTAMENTOS");
	private JPanel panelTitulo = new JPanel();
	private Font negrita = new Font("Arial", Font.BOLD, 25);
	private Font fuente = new Font("calibri", Font.PLAIN, 20);
	private JPanel panelCampos = new JPanel();
	private JLabel labelDepartamento = new JLabel("N Departamento: ");
	private JLabel labelNombre = new JLabel("Nombre: ");
	private JTextField textDep = new JTextField(5);
	private JTextField textNombre = new JTextField(10);
	/*-----*/
	private JPanel panelBotones = new JPanel();
	private JButton bInsertar = new JButton("Insertar");
	private JButton bBorrar = new JButton("Borrar");
	private JButton bModificar = new JButton("Modificar");
	private Gestion g = new Gestion();

	public GestorDepartamentos() {
		formulario();
		botones();
		raton();
		activarListener();
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventana.setSize(700, 500);
		ventana.setLocation(600, 200);
		ventana.setResizable(false);
		ventana.setTitle("Gestion de departamento");
		ventana.setVisible(true);
		ventana.setIconImage(imagenMenu.getImage());
	}

	public void formulario() {
		contenedor.setLayout(new BorderLayout(0, 0));
		GridBagConstraints restricciones = new GridBagConstraints();
		panelTitulo.setLayout(new GridBagLayout());
		// TITULO
		titulo.setFont(negrita);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 0;
		restricciones.gridy = 0;
		restricciones.ipady = 50;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(0, 0, 0, 0);
		panelTitulo.add(titulo, restricciones);
		contenedor.add(panelTitulo, BorderLayout.NORTH);
		// CAMPOS
		panelCampos.setLayout(new GridBagLayout());
		// Fuentes de campos y labels
		labelDepartamento.setFont(fuente);
		labelNombre.setFont(fuente);
		textDep.setFont(fuente);
		textNombre.setFont(fuente);
		// label departamento
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 0;
		restricciones.gridy = 0;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(0, 0, 0, 0);
		panelCampos.add(labelDepartamento, restricciones);
		// campo departamento
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 1;
		restricciones.gridy = 0;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(0, 30, 0, 100);
		panelCampos.add(textDep, restricciones);
		// label nombre
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 0;
		restricciones.gridy = 1;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(10, 0, 180, 70);
		panelCampos.add(labelNombre, restricciones);
		// Campo nombre
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 1;
		restricciones.gridy = 1;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(10, 28, 180, 15);
		panelCampos.add(textNombre, restricciones);
		contenedor.add(panelCampos, BorderLayout.CENTER);
	}

	public void botones() {
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		// Fuentes para botones
		bInsertar.setFont(fuente);
		bBorrar.setFont(fuente);
		bModificar.setFont(fuente);
		panelBotones.add(bInsertar);
		panelBotones.add(bBorrar);
		panelBotones.add(bModificar);
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
		textDep.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == e.BUTTON3)
					popupMenu.show(textDep, e.getX(), e.getY());
			}
		});
		textNombre.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == e.BUTTON3)
					popupMenu.show(textNombre, e.getX(), e.getY());
			}
		});

	}

	public void activarListener() {
		bInsertar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertarDepartamento();
			}
		});

		bModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modificarDepartamento();

			}
		});
		bBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				borrarDepartamento();

			}
		});
	}

	public void insertarDepartamento() {
		if (textDep.getText().isEmpty() || textNombre.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tienes que rellenar todos los campos", null,
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			int numDep = Integer.valueOf(textDep.getText());
			String nombreDep = textNombre.getText();
			Departamentos averiguarDep = g.ConsultarDepartamento(numDep);
			if (averiguarDep != null) {
				JOptionPane.showMessageDialog(null, "Existe el departamento", null, JOptionPane.ERROR_MESSAGE);
			} else {
				Departamentos agregarDepartamento = new Departamentos(numDep, nombreDep, null);
				if (g.agregarDepartamento(agregarDepartamento) == true) {
					JOptionPane.showMessageDialog(null, "Ha sido insertado con exito", null,
							JOptionPane.INFORMATION_MESSAGE);
					textDep.setText("");
					textNombre.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "No ha sido insertado", null, JOptionPane.ERROR_MESSAGE);
				}
			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El id departamento se escribe solo por numeros", null,
					JOptionPane.ERROR_MESSAGE);

		}

	}

	public void modificarDepartamento() {
		if (textDep.getText().isEmpty() || textNombre.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tienes que rellenar todos los campos", null,
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			int numDep = Integer.valueOf(textDep.getText());
			String nombreDep = textNombre.getText();
			Departamentos averiguarDep = g.ConsultarDepartamento(numDep);
			if (averiguarDep == null) {
				JOptionPane.showMessageDialog(null, "No existe el id departamento", null, JOptionPane.ERROR_MESSAGE);
			} else {
				averiguarDep.setIdDep(numDep);
				averiguarDep.setNombreDep(nombreDep);

				if (g.modificarDepartamento(numDep, nombreDep) == true) {
					JOptionPane.showMessageDialog(null, "Ha sido modificado con éxito", null,
							JOptionPane.INFORMATION_MESSAGE);
					textDep.setText("");
					textNombre.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "No ha sido modificado", null, JOptionPane.ERROR_MESSAGE);
				}

			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El id departamento se escribe solo por numeros", null,
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void borrarDepartamento() {
		if (textDep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tienes que rellenar un campo de id departamento", null,
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			int numDep = Integer.valueOf(textDep.getText());
			//Confirmar si id departamento existe
			Departamentos buscarId = g.ConsultarDepartamento(numDep);
			if (buscarId!=null) {
				//Averiguar si dentro del departamento hay empleados
				boolean siEstan = false;
				ArrayList <Empleados> buscarEmpleados = g.ConsultarEmpleadosPorNumDep(numDep);
				for (Empleados empleados : buscarEmpleados) {
					siEstan=true;
				}
				//Si hay empleados, abrirá una ventana de diálogo para confirmar
				if (siEstan==true) {
					int resp = JOptionPane.showConfirmDialog(null, "Hay empleados en este departamento y también serán eliminados en la base de datos\n¿Deseas continuar?", null, JOptionPane.WARNING_MESSAGE);
					if (JOptionPane.OK_OPTION==resp) {
						if (g.borrarDepartamento(numDep)) {
							JOptionPane.showMessageDialog(null, "Ha sido borrado con éxito", null,
									JOptionPane.INFORMATION_MESSAGE);
							textDep.setText("");
						} else{
							JOptionPane.showMessageDialog(null, "ERROR: no se puede borrar ", null,
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
					//Si no hay empleados, el id departamento se borra
				}else{
					if (g.borrarDepartamento(numDep)) {
						JOptionPane.showMessageDialog(null, "Ha sido borrado con éxito", null,
								JOptionPane.INFORMATION_MESSAGE);
					} else{
						JOptionPane.showMessageDialog(null, "ERROR: no se puede borrar ", null,
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "No existe el id dep", null, JOptionPane.ERROR_MESSAGE);

			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El campo id Departamento solo está permitido los números", null,
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
