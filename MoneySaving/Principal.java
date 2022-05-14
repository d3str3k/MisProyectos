import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Principal extends JFrame implements ActionListener{

    private JMenuBar mb;
    private JMenu menuAjustes,menuTriangularAccount,menuAcercaDe;
    private JMenuItem menuitemTerminos, menuitemPreferencias;
    private JLabel labelLogo,labelBienvenido;
    public static String name = Login.name;

    public Principal() {
        setLayout(null);
        setTitle("Pantalla principal");
        getContentPane().setBackground(new Color(0,0,0));
        setIconImage(new ImageIcon(getClass().getResource("./images/enigma-icon.png")).getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        

        mb = new JMenuBar();
        mb.setBackground(new Color(0, 0, 0));
        setJMenuBar(mb);

        menuAjustes = new JMenu("Ajustes");
        menuAjustes.setBackground(new Color(0, 0, 0));
        menuAjustes.setFont(new Font("Andale Mono", 1, 14));
        menuAjustes.setForeground(new Color(255, 255, 255));
        mb.add(menuAjustes);

        menuitemPreferencias = new JMenuItem("Preferencias");
        menuitemPreferencias.setBackground(new Color(0,0,0));
        menuitemPreferencias.setFont(new Font("Andale Mono", 1, 14));
        menuitemPreferencias.setForeground(new Color(255,255,255));
        menuitemPreferencias.addActionListener(this);
        menuAjustes.add(menuitemPreferencias);

        menuTriangularAccount = new JMenu("TriangularAccount");
        menuTriangularAccount.setBackground(new Color(0, 0, 0));
        menuTriangularAccount.setFont(new Font("Andale Mono", 1, 14));
        menuTriangularAccount.setForeground(new Color(255, 255, 255));
        mb.add(menuTriangularAccount);

        menuAcercaDe = new JMenu("Acerca de");
        menuAcercaDe.setBackground(new Color(0, 0, 0));
        menuAcercaDe.setFont(new Font("Andale Mono", 1, 14));
        menuAcercaDe.setForeground(new Color(255, 255, 255));
        mb.add(menuAcercaDe);

        menuitemTerminos = new JMenuItem("Términos y Condiciones");
        menuitemTerminos.setBackground(new Color(0,0,0));
        menuitemTerminos.setFont(new Font("Andale Mono", 1, 14));
        menuitemTerminos.setForeground(new Color(255,255,255));
        menuAcercaDe.add(menuitemTerminos);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("./images/enigma-logo.png"));
        Image image = imageIcon.getImage();//1
        Image newimage = image.getScaledInstance(200,75, java.awt.Image.SCALE_SMOOTH);//2
        imageIcon = new ImageIcon(newimage);//3 estas tres líneas sirven para que la imagen se cargue correctamente
        labelLogo = new JLabel(imageIcon);  
        labelLogo.setBounds(5,5,250,100);
        add(labelLogo);

        labelBienvenido = new JLabel("Bienvenido " + name);  
        labelBienvenido.setBounds(280,30,300,30);
        labelBienvenido.setFont(new Font("Andale Mono", 1, 32));
        labelBienvenido.setForeground(new Color(255, 255, 255));
        add(labelBienvenido);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuitemPreferencias) {
        	Login ventanaLogin = new Login();
        	ventanaLogin.setBounds(0,0,350,450);
        	ventanaLogin.setVisible(true);
        	ventanaLogin.setResizable(false);
        	ventanaLogin.setLocationRelativeTo(null);
            this.dispose();
        }
    }

    public static void main(String args[]) {
        Principal ventanaPrincipal = new Principal();
        ventanaPrincipal.setBounds(0,0,640,535);
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.setLocationRelativeTo(null);
    } 
}