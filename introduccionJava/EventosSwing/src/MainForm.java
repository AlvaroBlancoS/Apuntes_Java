import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.*;
public class MainForm {
    private JTextPane textPrecio;
    private JTextPane textIVA;
    private JTextPane textTotal;
    private JLabel lPrecio;
    private JLabel lIVA;
    private JLabel lTotal;
    private JPanel MiPanel;
    private JButton calcularButton;

    private final JFrame frame = new JFrame();
    private final Container contenedor = frame.getContentPane();
    private final Font negrita = new Font("Arial", Font.BOLD, 20);

    private final JPopupMenu popupMenu = new JPopupMenu();
    private JMenuItem copiar, cortar, pegar;

    // Imagen de copiar
    private Image copyImage = new ImageIcon("imgs/copy.png").getImage();
    private final ImageIcon copyIcono = new ImageIcon(copyImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
    // Imagen de cortar
    private Image cutImage = new ImageIcon("imgs/cut.png").getImage();
    private ImageIcon cutIcono = new ImageIcon(cutImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
    // Imagen de pegar
    private Image pasteImage = new ImageIcon("imgs/paste.png").getImage();
    private ImageIcon pasteIcono = new ImageIcon(pasteImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH));

    public MainForm() {
        ventana();
        botonPulsado();
        raton();
    }

    public void ventana() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(MiPanel);
        int width = 220;
        int height = 250;
        frame.setSize(width, height);
        int x = 600;
        int y = 200;
        frame.setLocation(x, y);
        frame.setResizable(true);
        frame.setTitle("Calculadora ");
        frame.setVisible(true);
//        JLabel[] etiquetas = {lIVA, lPrecio, lTotal};
//        for (JLabel etiqueta : etiquetas) {
//            etiqueta.setForeground(Color.WHITE);
//            etiqueta.setFont(negrita);
//        }
        calcularButton.setFont(negrita);

//        JTextPane[] cajasTextos = {textPrecio, textIVA, textTotal};
//        for (JTextPane cajaTexto : cajasTextos) {
//            cajaTexto.setFont(negrita);
//        }
        //Esto parece que mas corto en vez de utilizar dos bucles
        Component [] [] componentes ={{lIVA, lPrecio, lTotal},{textPrecio,textIVA, textTotal}};
        for (int i = 0; i<= componentes.length; i++){
            componentes [0][i].setFont(negrita);
            componentes [0][i].setForeground(Color.WHITE);
            componentes [1][i].setFont(negrita);
        }
        textTotal.setBackground(Color.GRAY);
        textTotal.setEditable(false);
    }

    public void botonPulsado() {
        this.calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!(textPrecio.getText().isEmpty()|| textIVA.getText().isEmpty())){
                        double p = Double.parseDouble(textPrecio.getText());
                        double t = Double.parseDouble(textIVA.getText());
                        double resultado = p + (p / 100 * t);
                        textTotal.setText(Double.toString(resultado));
                    }else {
                        JOptionPane.showMessageDialog(null, "Tienes que rellenar el precio y el IVA",
                                "WARNING_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException i) {
                    JOptionPane.showMessageDialog(null, i.getMessage(),
                            "WARNING_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
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
        textPrecio.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == e.BUTTON3)
                    popupMenu.show(textPrecio, e.getX(), e.getY());
            }
        });
        textIVA.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == e.BUTTON3)
                    popupMenu.show(textIVA, e.getX(), e.getY());
            }
        });
    }

    public static void main(String[] args) {
        new MainForm();
    }
}




