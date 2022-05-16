import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Principal extends JFrame implements ActionListener{

    private JMenuBar mb;
    private JMenu menuAjustes,menuAcercaDe, menuColor;
    private JMenuItem menuOscuro, menuItemClaro, menuitemTerminos, menuitemPreferencias;
    //private JCheckBoxMenuItem menuOscuro;
    private JLabel labelLogo,labelBienvenido;
    private JButton botonCuenta, botonHucha, botonTriangularAccount;
    private JTextArea textarea1;
    private JScrollPane scrollpane1;
    private Color background, bottons, menu, menuitems, text;
    private String [] database = new String[2];
    public static boolean modoOscuro = false; 


    public Principal() {
        try (Scanner sc = new Scanner(new File("database.txt"))){
            String aux = sc.next();
            database = aux.split("[:]");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE); //El programa no quedarÃ¡ en segundo plano (muere) cuando se cierra la interfaz
        setTitle("Pantalla principal");
        getContentPane().setBackground(new Color(0,0,0));
        setIconImage(new ImageIcon(getClass().getResource("./images/enigma-icon.png")).getImage());

        mb = new JMenuBar();
        mb.setBackground(new Color(225,0,0));
        setJMenuBar(mb);

        menuAjustes = new JMenu("Ajustes");
        menuAjustes.setBackground(new Color(0, 0, 0));
        menuAjustes.setFont(new Font("Andale Mono", 1, 14));
        menuAjustes.setForeground(new Color(255, 255, 255));
        mb.add(menuAjustes);

        menuColor = new JMenu("Cambiar Tema");
        menuColor.setFont(new Font("Andale Mono", 1, 14));
        menuColor.setForeground(new Color(0, 0, 0));
        menuAjustes.add(menuColor);

        menuOscuro = new JMenuItem("Modo Oscuro");
        menuOscuro.setBackground(new Color(0,0,0));
        menuOscuro.setFont(new Font("Andale Mono", 1, 14));
        menuOscuro.setForeground(new Color(255, 255, 255));
        menuOscuro.addActionListener(this); 
        menuColor.add(menuOscuro);

        menuItemClaro = new JMenuItem("Modo Claro");
        menuItemClaro.setBackground(new Color(0,0,0));
        menuItemClaro.setFont(new Font("Andale Mono", 1, 14));
        menuItemClaro.setForeground(new Color(255, 255, 255));
        menuItemClaro.addActionListener(this); 
        menuColor.add(menuItemClaro);

        // menuOscuro.addItemListener(new ItemListener() {
        //         @Override
        // 		public void itemStateChanged(ItemEvent e) {   
        //             if(e.getSource() == menuOscuro){
        //                 if(e.getStateChange() == 1){
        //                     modoOscuro = true;
        //                     background = new Color(3,5,30); 
        //                     bottons = new Color(11,82,105);
        //                     menu = new Color(151, 141, 88);
        //                     menuitems = new Color(11,82,105);
        //                     text = new Color(234, 225, 225);
        //                 }else{
        //                     modoOscuro = false;
        //                     background = new Color(163,247,191); 
        //                     bottons = new Color(5,223,215);
        //                     menu = new Color(243, 85, 136);
        //                     menuitems = new Color(5,223,215);
        //                     text = new Color(255, 245, 145); 
        //                 }

        //             }
        //         }
        //      });
        //Las paletas de colores se crean ahÃ­ con unas variables declaradas y ya simplemente es poner cada varible en donde corresponda, que en base a la opciÃ³n los colores se seleccionan solos

        menuColor.add(menuOscuro);

        menuitemPreferencias = new JMenuItem("Preferencias");
        menuitemPreferencias.setBackground(new Color(0,0,0));
        menuitemPreferencias.setFont(new Font("Andale Mono", 1, 14));
        menuitemPreferencias.setForeground(new Color(255,255,255));
        menuitemPreferencias.addActionListener(this);
        menuAjustes.add(menuitemPreferencias);

        menuAcercaDe = new JMenu("Acerca de");
        menuAcercaDe.setBackground(new Color(0, 0, 0));
        menuAcercaDe.setFont(new Font("Andale Mono", 1, 14));
        menuAcercaDe.setForeground(new Color(255, 255, 255));
        mb.add(menuAcercaDe);

        menuitemTerminos = new JMenuItem("TÃ©rminos y Condiciones");
        menuitemTerminos.setBackground(new Color(0,0,0));
        menuitemTerminos.setFont(new Font("Andale Mono", 1, 14));
        menuitemTerminos.setForeground(new Color(255,255,255));
        menuitemTerminos.addActionListener(this);
        menuAcercaDe.add(menuitemTerminos);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("./images/enigma-logo.png"));
        Image image = imageIcon.getImage();//1
        Image newimage = image.getScaledInstance(200,75, java.awt.Image.SCALE_SMOOTH);//2
        imageIcon = new ImageIcon(newimage);//3 estas tres lÃ­neas sirven para que la imagen se cargue correctamente
        labelLogo = new JLabel(imageIcon);  
        labelLogo.setBounds(330,120,250,100);
        add(labelLogo);

        labelBienvenido = new JLabel("Bienvenido " + database[0]);  
        labelBienvenido.setBounds(235,30,300,50);
        labelBienvenido.setFont(new Font("Andale Mono", 1, 26));
        labelBienvenido.setForeground(new Color(255, 255, 255));
        add(labelBienvenido);

        botonCuenta =  new JButton("CUENTA");
        botonCuenta.setBounds(30,100,250,150);
        botonCuenta.setBackground(new Color(255,255,255));
        botonCuenta.setFont(new Font("Andale Mono", 1, 14));
        botonCuenta.setForeground(new Color(0,0,0));
        botonCuenta.addActionListener(this); 
        add(botonCuenta);

        botonHucha =  new JButton("HUCHA");
        botonHucha.setBounds(30,260,250,150);
        botonHucha.setBackground(new Color(255,255,255));
        botonHucha.setFont(new Font("Andale Mono", 1, 14));
        botonHucha.setForeground(new Color(0,0,0));
        botonHucha.addActionListener(this); 
        add(botonHucha);

        botonTriangularAccount = new JButton("TriangularAccount");
        botonTriangularAccount.setBounds(330,100,250,150);
        botonTriangularAccount.setBackground(new Color(243, 36, 36));
        botonTriangularAccount.setFont(new Font("Andale Mono", 1, 14));
        botonTriangularAccount.setForeground(new Color(255, 255, 255));
        botonTriangularAccount.addActionListener(this); 
        mb.add(botonTriangularAccount);

        MoneySavingTips monkey = new MoneySavingTips();
        int rnd = (int) (Math.random() * (monkey.numPhrases - 0)) + 0;
        textarea1 = new JTextArea();
        textarea1.setEditable(false); // El usuario no podrÃƒÂ¡ modificar el texto que estÃƒÂ© dentro de JTextArea
        textarea1.setFont(new Font("Andale Mono", 0, 9));
        textarea1.setText("\n\n " + monkey.getTips(rnd));
        textarea1.setBackground(new Color(0,0,0));
        textarea1.setForeground(new Color(0,255,0));
        scrollpane1 = new JScrollPane(textarea1);
        scrollpane1.setBounds(330,300,250,100);
        add(scrollpane1); 

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
        if(e.getSource() == menuitemTerminos) {
            JOptionPane.showMessageDialog(null, "", "TÃ©rminos y Condiciones de Servicio", JOptionPane.DEFAULT_OPTION);        
        }
        if(e.getSource() == botonTriangularAccount){
            
        }
        if(e.getSource() == botonCuenta){

        }
        if(e.getSource() == botonHucha){

        }
        if(e.getSource() == menuOscuro){
            modoOscuro = true;
            getContentPane().setBackground(new Color(3,5,30)); 
            botonCuenta.setBackground(new Color(11,82,105));
            // menu = new Color(151, 141, 88);
            // menuitems = new Color(11,82,105);
            // text = new Color(234, 225, 225);           
        }if(e.getSource() == menuItemClaro){
            background = new Color(0,255,0);
            getContentPane().setBackground(background);
        } 
    }

    //public void stateChanged (ChangeEvent event) {
    //  if (menuOscuro.isSelected() == true) {
        //    modoOscuro = true;
            //Incluir Paleta del fondo Oscuro
       // } else {
        //	modoOscuro = false;
        	//Incluir Paleta del fondo Claro
       // }
    // }
    //Las paletas de colores se crean ahÃ­ con unas variables declaradas y ya simplemente es poner cada varible en donde corresponda, que en base a la opciÃ³n los colores se seleccionan solos

    
    public static void main(String args[]) {
        Principal ventanaPrincipal = new Principal();
        ventanaPrincipal.setBounds(0,0,640,535);
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.setLocationRelativeTo(null);
    } 
}