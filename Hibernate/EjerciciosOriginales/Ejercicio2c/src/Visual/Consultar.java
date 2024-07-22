package Visual;

import java.awt.BorderLayout;
import java.awt.Container;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultEditorKit;

import Modelo.Gestion;
import Primero.Departamentos;
import Primero.Empleados;
import Visual.GestorDepartamentos;
//import Vista.GestorEmpleados;

public class Consultar {
	private Gestion g = new Gestion();
	private JFrame ventana = new JFrame();
	private Container contenedor = ventana.getContentPane();
	private JPanel primerPanel = new JPanel();
	private JLabel introducirNoDep = new JLabel("Introducir N Departamento");
	private JTextField textDep = new JTextField();
	private JButton bConsultar = new JButton("Consultar");
	private JLabel NombreDep = new JLabel("Nombre Departamento: ");
	private JLabel inforDep = new JLabel();
	private JTextArea textArea = new JTextArea();
	private JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private Font fuente = new Font("calibri", Font.BOLD, 20);
	private ImageIcon imagenMenu = new ImageIcon("Carpetas/img" + File.separator + "consulta.png");
	private JMenuBar menuBar;
	private JMenu gestores, abrir;
	private JMenuItem gestorEmpleado, gestorDepartamento;
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

	public Consultar() {
		formulario();
		raton();
		menu();
		activarListeners();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(800, 500);
		ventana.setLocation(600, 200);
		ventana.setResizable(false);
		ventana.setTitle("Ejercicio 2");
		ventana.setVisible(true);
		ventana.setIconImage(imagenMenu.getImage());
	}
	
	public void menu() {
		menuBar = new JMenuBar();
		ventana.setJMenuBar(menuBar);
		gestores = new JMenu("Gestores");
		gestores.setMnemonic(KeyEvent.VK_G);
		menuBar.add(gestores);
		abrir = new JMenu("Abrir");
		abrir.setMnemonic(KeyEvent.VK_A);
		gestores.add(abrir);
		gestorEmpleado = new JMenuItem("Gestión de empleados");
		gestorEmpleado.setMnemonic(KeyEvent.VK_E);
		gestorDepartamento = new JMenuItem("Gestión de departamento");
		gestorDepartamento.setMnemonic(KeyEvent.VK_D);
		// Fuentes
		gestores.setFont(fuente);
		abrir.setFont(fuente);
		gestorEmpleado.setFont(fuente);
		gestorDepartamento.setFont(fuente);
		// Add gestores
		abrir.add(gestorEmpleado);
		abrir.add(gestorDepartamento);
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
		textArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == e.BUTTON3)
					popupMenu.show(textArea, e.getX(), e.getY());
			}
		});
		textDep.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == e.BUTTON3)
					popupMenu.show(textArea, e.getX(), e.getY());
			}
		});
	}

	public void formulario() {
		contenedor.setLayout(new BorderLayout(0, 0));
		primerPanel.setLayout(new GridBagLayout());
		GridBagConstraints restricciones = new GridBagConstraints();
		// Introduce numero departamento
		introducirNoDep.setFont(fuente);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 0;
		restricciones.gridy = 0;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(15, 20, 0, 0);
		primerPanel.add(introducirNoDep, restricciones);
		// Caja de texto departamento
		textDep.setFont(fuente);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 1;
		restricciones.gridy = 0;
		restricciones.ipady = 0;
		restricciones.ipadx = 120;
		restricciones.insets = new Insets(15, 5, 0, 0);
		primerPanel.add(textDep, restricciones);
		// Boton de consultar departamento
		bConsultar.setFont(fuente);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 2;
		restricciones.gridy = 0;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(15, 5, 0, 100);
		primerPanel.add(bConsultar, restricciones);
		// Un label de nombre departamento
		NombreDep.setFont(fuente);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 0;
		restricciones.gridy = 1;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(10, 20, 0, 0);
		primerPanel.add(NombreDep, restricciones);
		// Otro label para dar informacion del departamento
		inforDep.setFont(fuente);
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx = 1;
		restricciones.gridy = 1;
		restricciones.ipady = 0;
		restricciones.ipadx = 0;
		restricciones.insets = new Insets(10, 0, 0, 0);
		primerPanel.add(inforDep, restricciones);
		// -------------------------------------
		contenedor.add(primerPanel, BorderLayout.NORTH);
		scroll.setFont(fuente);
		textArea.setFont(fuente);
		textArea.setEnabled(false);
		contenedor.add(scroll, BorderLayout.CENTER);
	}

	public void activarListeners() {
		bConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verEmpleadosPorNumDep();
			}
		});

		gestorEmpleado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GestorEmpleados();
			}
		});

		gestorDepartamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GestorDepartamentos();
			}
		});

	}
	
	public void verEmpleadosPorNumDep(){
		try {
			if (textDep.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Tienes que rellenar el campo de numero departamento ", null,
						JOptionPane.ERROR_MESSAGE);
			} else {
				int numDep = Integer.valueOf(textDep.getText());
				Departamentos buscarDep = g.ConsultarDepartamento(numDep);
				if (buscarDep ==null) {
					JOptionPane.showMessageDialog(null, "El departamento numero "+numDep+" no existe", null,
							JOptionPane.ERROR_MESSAGE);
				}else{
					ArrayList<Empleados> empleadosDep = g.ConsultarEmpleadosPorNumDep(numDep);
					boolean siExiste = false;
					textArea.setText("Numero de empleados: " + empleadosDep.size()
							+ "\n----------------------------------------\nNOMBRE\tAPELLIDOS\n----------------------------------------\n");				
					// Mostrar informacion de los empleados
					for (Empleados empleados : empleadosDep) {
						textArea.append(empleados.getNombre() + "\t" + empleados.getApellidos() + "\n");
						siExiste = true;
					}
					if (siExiste == true) {		
						inforDep.setText(buscarDep.getNombreDep());
					} else {
						inforDep.setText("");
						textArea.setText("");
						JOptionPane.showMessageDialog(null, "No hay ningún empleado en el departamento: "+buscarDep.getNombreDep(), null,
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Sólo se escribe en números ", null,
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
