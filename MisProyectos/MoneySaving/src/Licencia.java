import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

// import ds.desktop.notify.DesktopNotify;

public class Licencia extends JFrame implements ActionListener, ChangeListener{
    // Para poder implementar un evento asociado a una JCheckBox hay que implementar la clase ChangeListener, y para los botones ActionListener
    private JLabel label1, label2;
    private JCheckBox check1;
    private JButton button1, button2;
    private JScrollPane scrollpane1;
    private JTextArea textarea1;
    private String[] database = new String[2];

    public Licencia () {
        
        try (Scanner sc = new Scanner(new File("database.txt"))){
            String aux = sc.next();
            database = aux.split("[:]");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Licencia de uso");
        getContentPane().setBackground(Principal.layoutColor);
        setIconImage(new ImageIcon(getClass().getResource("./images/enigma-icon.png")).getImage());

        label1 = new JLabel("TÉRMINOS Y CONDICIONES");
        label1.setBounds(215,5,250,30);
        label1.setFont(new Font("Andale Mono", 1, 14));
        label1.setForeground(Principal.wordBlack);
        add(label1);

        textarea1 = new JTextArea();
        textarea1.setEditable(false); // El usuario no podrá modificar el texto que esté dentro de JTextArea
        textarea1.setFont(new Font("Andale Mono", 0, 9));
        textarea1.setText("\n\n \t\t     COMO NUEVO USUARIO DEL SISTEMA MONEYSAVING SE COMPROMETE" +
                          "\n\n \t\t     1) A aprobar el proyecto MoneySaving con una nota mínma de 5.");
        textarea1.setBackground(Principal.ButtonColor);
        textarea1.setForeground(Principal.wordWhite);
        scrollpane1 = new JScrollPane(textarea1);
        scrollpane1.setBounds(10,40,575,200);
        add(scrollpane1); 

        check1 = new JCheckBox("Yo, " + database[0] + " acepto los términos y condiciones del contrato");
        check1.setBackground(Principal.ButtonColor);
        check1.setBounds(10,250,300,30);
        check1.setForeground(Principal.wordWhite);
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
            // DesktopNotify.showDesktopMessage("Bienvenido a MoneySaving", , DesktopNotify.SUCCESS);
            JOptionPane.showMessageDialog(null, "Te damos la bienvenida a la mejor aplicación de gestoría de ahorros. Disfruta de nuestro servicio :)", "Bienvenido a MoneySaving", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else if (action.getSource() == button2) {
            try (// Elimina los datos
            PrintWriter pw = new PrintWriter("database.txt")) {
                pw.write("");
                pw.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Login ventanaLogin = new Login();
            ventanaLogin.setBounds(0,0,350,450);
            ventanaLogin.setVisible(true);
            ventanaLogin.setResizable(false);  
            ventanaLogin.setLocationRelativeTo(null);
            this.dispose(); //dispose() es un m�todo que hace que la ventana JFrame sea destruida y limpiada del sistema ¿Qué es mejor: invisibilizar esta ventana o eliminarla?
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
