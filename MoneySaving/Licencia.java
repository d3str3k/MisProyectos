import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Licencia extends JFrame implements ActionListener, ChangeListener{
    // Para poder implementar un evento asociado a una JCheckBox hay que implementar la clase ChangeListener, y para los botones ActionListener
    private JLabel label1, label2;
    private JCheckBox check1;
    private JButton button1, button2;
    private JScrollPane scrollpane1;
    private JTextArea textarea1;
    String nombre = "";

    public Licencia () {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Licencia de uso");
        getContentPane().setBackground(new Color(0,0,0));
        setIconImage(new ImageIcon(getClass().getResource("./images/enigma-icon.png")).getImage());
        Login ventanaLogin = new Login();
        nombre = ventanaLogin.nombre;

        label1 = new JLabel("TÉRMINOS Y CONDICIONES");
        label1.setBounds(215,5,250,30);
        label1.setFont(new Font("Andale Mono", 1, 14));
        label1.setForeground(new Color(255,255,255));
        add(label1);

        textarea1 = new JTextArea();
        textarea1.setEditable(false); // El usuario no podrá modificar el texto que esté dentro de JTextArea
        textarea1.setFont(new Font("Andale Mono", 0, 9));
        textarea1.setText("\n\n \t\t\t     TÉRMINOS Y CONDICIONES");
        textarea1.setBackground(new Color(0,0,0));
        textarea1.setForeground(new Color(0,255,0));
        scrollpane1 = new JScrollPane(textarea1);
        scrollpane1.setBounds(10,40,575,200);
        add(scrollpane1); 

        check1 = new JCheckBox("Yo " + nombre + " Acepto");
        check1.setBackground(new Color(0,0,0));
        check1.setBounds(10,250,300,30);
        check1.setForeground(new Color(255,255,255));
        check1.addChangeListener(this);
        add(check1);

        button1 = new JButton("Continuar");
        button1.setBounds(10,290,100,30);
        button1.addActionListener(this);
        button1.setEnabled(false);
        add(button1);

        button2 = new JButton("No Acepto");
        button2.setBounds(120,290,100,30);
        button2.addActionListener(this);
        button2.setEnabled(true);
        add(button2);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("./images/enigma-logo.png"));
        Image image = imageIcon.getImage();//1
        Image newimage = image.getScaledInstance(200,75, java.awt.Image.SCALE_SMOOTH);//2
        imageIcon = new ImageIcon(newimage);//3 estas tres líneas sirven para que la imagen se cargue correctamente
        label2 = new JLabel(imageIcon);
        label2.setBounds(315,130,300,300);
        add(label2);
    }

    public void stateChanged (ChangeEvent event) {
        if (check1.isSelected() == true) {
            button1.setEnabled(true);
            button2.setEnabled(false);
        } else {
            button1.setEnabled(false);
            button2.setEnabled(true);
        }
    }

    public void actionPerformed (ActionEvent action) {
        if (action.getSource() == button1) {
            Principal ventanaPrincipal = new Principal();
            ventanaPrincipal.setBounds(0,0,640,535);
            ventanaPrincipal.setVisible(true);
            ventanaPrincipal.setResizable(false);
            ventanaPrincipal.setLocationRelativeTo(null);
            this.setVisible(false);
        } else if (action.getSource() == button2) {
            Login ventanaLogin = new Login();
            ventanaLogin.setBounds(0,0,350,450);
            ventanaLogin.setVisible(true);
            ventanaLogin.setResizable(false);  
            ventanaLogin.setLocationRelativeTo(null);
            this.setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        Licencia ventanaLicencia = new Licencia();
        ventanaLicencia.setBounds(0,0,600,360);
        ventanaLicencia.setVisible(true);
        ventanaLicencia.setResizable(false);
        ventanaLicencia.setLocationRelativeTo(null);
    }
}
