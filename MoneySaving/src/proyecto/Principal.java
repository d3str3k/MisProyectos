package proyecto;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyStore.TrustedCertificateEntry;
import java.text.ParseException;
import java.util.Scanner;
import java.awt.TrayIcon.MessageType;

public class Principal extends JFrame implements ActionListener{
    //Cositas del menú
    private JMenuBar mb;
    private JMenu menuAjustes,menuAcercaDe, menuColor;
    private JMenuItem menuOscuro, menuItemClaro, menuitemTerminos, menuitemRecuperacion, menuItemNotificaciones;
    //Textos
    private JLabel labelLogo,labelBienvenido, labelSavingTips;
    //Botones
    private JButton botonCuenta, botonHucha, botonTriangularAccount, botonHome, botonExit;
    //Área de texto
    private JTextArea textarea1;
    private JScrollPane scrollpane1;
    //
    private String [] database = new String[4];
    //Booleans    
    public static boolean modoOscuro = false; 
    //Colores
    public static Color layoutColor =new Color(232,248, 245);
    public static Color ButtonColor =new Color(23, 165, 137);
    public static Color wordBlack =new Color(0, 0, 0);            //Para letras sobre el fondo
    public static Color wordWhite =new Color(255,255,255);        //Para letras sobre botones
    public static boolean notificacionesOn;

    public static Cuenta cuenta = new Cuenta();
	public static Hucha hucha = new Hucha();

    public Principal() throws ParseException, FileNotFoundException {
        try (Scanner sc = new Scanner(new File("database.txt"))){
            String aux = sc.next();
            database = aux.split("[:]");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        notificacionesOn = Boolean.parseBoolean(database[3]);
                
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE); //El programa no quedarÃƒÂ¡ en segundo plano (muere) cuando se cierra la interfaz
        setTitle("Pantalla principal");
        getContentPane().setBackground(layoutColor);
        setIconImage(new ImageIcon(getClass().getResource("/images/enigma-icon.png")).getImage());

//-----------------------------------------------------------------------------------------------------------------------------------------
//                                                  MENÃš
        mb = new JMenuBar();
        mb.setBackground(ButtonColor);
        setJMenuBar(mb);

        menuAjustes = new JMenu("Ajustes");
        menuAjustes.setBackground(ButtonColor);
        menuAjustes.setFont(new Font("Andale Mono", 1, 14));
        menuAjustes.setForeground(wordWhite);
        mb.add(menuAjustes);

        menuColor = new JMenu("Cambiar Tema");
        menuColor.setFont(new Font("Andale Mono", 1, 14));
        menuColor.setForeground(wordBlack);
        menuAjustes.add(menuColor);

        menuOscuro = new JMenuItem("Modo Oscuro");
        menuOscuro.setBackground(wordBlack);
        menuOscuro.setFont(new Font("Andale Mono", 1, 14));
        menuOscuro.setForeground(wordWhite);
        menuOscuro.addActionListener(this); 
        menuColor.add(menuOscuro);

        menuItemClaro = new JMenuItem("Modo Claro");
        menuItemClaro.setBackground(wordBlack);
        menuItemClaro.setFont(new Font("Andale Mono", 1, 14));
        menuItemClaro.setForeground(wordWhite);
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
        //Las paletas de colores se crean ahÃƒÂ­ con unas variables declaradas y ya simplemente es poner cada varible en donde corresponda, que en base a la opciÃƒÂ³n los colores se seleccionan solos

        menuitemRecuperacion = new JMenuItem("Restablecer ContraseÃ±a");
        menuitemRecuperacion.setBackground(new Color(0,0,0));
        menuitemRecuperacion.setFont(new Font("Andale Mono", 1, 14));
        menuitemRecuperacion.setForeground(new Color(255,255,255));
        menuitemRecuperacion.addActionListener(this);
        menuAjustes.add(menuitemRecuperacion);

        menuItemNotificaciones = new JMenuItem("Activar/Desactivar las Notificaciones");
        menuItemNotificaciones.setBackground(wordBlack);
        menuItemNotificaciones.setFont(new Font("Andale Mono", 1, 14));
        menuItemNotificaciones.setForeground(wordWhite);
        menuItemNotificaciones.addActionListener(this);
        menuAjustes.add(menuItemNotificaciones);


        menuAcercaDe = new JMenu("Acerca de");
        menuAcercaDe.setBackground(wordBlack);
        menuAcercaDe.setFont(new Font("Andale Mono", 1, 14));
        menuAcercaDe.setForeground(wordWhite);
        mb.add(menuAcercaDe);

        menuitemTerminos = new JMenuItem("T�rminos y Condiciones");
        menuitemTerminos.setBackground(wordWhite);
        menuitemTerminos.setFont(new Font("Andale Mono", 1, 14));
        menuitemTerminos.setForeground(wordBlack);
        menuitemTerminos.addActionListener(this);
        menuAcercaDe.add(menuitemTerminos);
//-----------------------------------------------------------------------------------------------------------------------------------------

//-----------------------------------------------------------------------------------------------------------------------------------------
//              MINI LOGO CABECERA
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/enigma-logo.png"));
        Image image = imageIcon.getImage();//1
        Image newimage = image.getScaledInstance(200,75, java.awt.Image.SCALE_SMOOTH);//2
        imageIcon = new ImageIcon(newimage);//estas tres l�neas sirven para que la imagen se cargue correctamente
        labelLogo = new JLabel(imageIcon);  
        labelLogo.setBounds(330,120,250,100);
        add(labelLogo);
//-----------------------------------------------------------------------------------------------------------------------------------------

//-----------------------------------------------------------------------------------------------------------------------------------------
//                    TEXTO
        labelBienvenido = new JLabel("Bienvenido " + database[0]);  
        labelBienvenido.setBounds(215,30,300,50);
        labelBienvenido.setFont(new Font("Andale Mono", 1, 26));
        labelBienvenido.setForeground(wordBlack);
        add(labelBienvenido);
//-----------------------------------------------------------------------------------------------------------------------------------------

//-----------------------------------------------------------------------------------------------------------------------------------------
//                      BOTONES
        botonCuenta =  new JButton("CUENTA");
        botonCuenta.setBounds(30,100,250,150);
        botonCuenta.setBackground(ButtonColor);
        botonCuenta.setFont(new Font("Andale Mono", 1, 26));
        botonCuenta.setForeground(wordWhite);
        botonCuenta.addActionListener(this); 
        add(botonCuenta);

        botonHucha =  new JButton("HUCHA");
        botonHucha.setBounds(30,260,250,150);
        botonHucha.setBackground(ButtonColor);
        botonHucha.setFont(new Font("Andale Mono", 1, 26));
        botonHucha.setForeground(wordWhite);
        botonHucha.addActionListener(this); 
        add(botonHucha);

        ImageIcon iconExit = new ImageIcon(getClass().getResource("/images/acceso(1)(3).png"));
        botonExit =  new JButton(iconExit);
        botonExit.setBorder(BorderFactory.createEmptyBorder());
        botonExit.setContentAreaFilled(false);
        botonExit.setBounds(15,0,100, 100);
        botonExit.setFont(new Font("Andale Mono", 1, 26));
        botonExit.addActionListener(this); 
        add(botonExit);

        botonTriangularAccount = new JButton("TriangularAccount");
        botonTriangularAccount.setBounds(330,100,250,150);
        botonTriangularAccount.setBackground(ButtonColor);
        botonTriangularAccount.setFont(new Font("Andale Mono", 1, 14));
        botonTriangularAccount.setForeground(wordWhite);
        botonTriangularAccount.addActionListener(this); 
        mb.add(botonTriangularAccount);
//-----------------------------------------------------------------------------------------------------------------------------------------

//-----------------------------------------------------------------------------------------------------------------------------------------
//              �REA DE TEXTO MONEYSAVING TIPS
        
        labelSavingTips = new JLabel("SavingTips");  
        labelSavingTips.setBounds(330,230,180,100);
        labelSavingTips.setFont(new Font("Andale Mono", 1, 14));
        labelSavingTips.setForeground(wordBlack);
        add(labelSavingTips);
        
        MoneySavingTips monkey = new MoneySavingTips();
        int rnd = (int) (Math.random() * (monkey.numPhrases - 0)) + 0;
        textarea1 = new JTextArea();
        textarea1.setEditable(false); // El usuario no podr� modificar el texto que est� dentro de JTextArea
        textarea1.setFont(new Font("Andale Mono", 0, 9));
        textarea1.setText("\n\n " + monkey.getTips(rnd));
        textarea1.setBackground(ButtonColor);
        textarea1.setForeground(wordWhite);
        textarea1.setLineWrap(true);
        scrollpane1 = new JScrollPane(textarea1);
        scrollpane1.setBounds(330,300,250,100);
        add(scrollpane1); 


//-----------------------------------------------------------------------------------------------------------------------------------------
//              NOTIFICACIONES


    }
//-----------------------------------------------------------------------------------------------------------------------------------------



//-----------------------------------------------------------------------------------------------------------------------------------------
//                      ACCIONES DE MEN� Y BOTONES

    public void actionPerformed(ActionEvent e) {
        //BOT�N MEN� RECUPERACI�N CONTRASENYA
        if(e.getSource() == menuitemRecuperacion) {
        	String nuevo_pin = JOptionPane.showInputDialog(null, "Introduzca su nuevo PIN", "Nuevo PIN", JOptionPane.PLAIN_MESSAGE);
        }
        //BOT�N MEN� T�RMINOS Y CONDICIONES DE SERVICIO
        if(e.getSource() == menuitemTerminos) {
            JOptionPane.showMessageDialog(null, "COMO NUEVO USUARIO DEL SISTEMA MONEYSAVING SE COMPROMETE \n    1) A aprobar el proyecto MoneySaving con una nota m�nma de 5.", "T�rminos y Condiciones de Servicio", JOptionPane.DEFAULT_OPTION);        
        }
        //BOT�N TRIANGULAR ACCOUNT
        if(e.getSource() == botonTriangularAccount){
            
        }
        //BOT�N CUENTA
        if(e.getSource() == botonCuenta){
            cuenta.setBounds(0,0,350,450);
            cuenta.setVisible(true);
            cuenta.setResizable(false);
            cuenta.setLocationRelativeTo(null);
            this.dispose();
        }
        
        //BOT�N HUCHA
        if(e.getSource() == botonHucha){
            // Condicional por si hay hucha o no para acceder a registroHucha o  a hucha
    		File huchaExiste = new File("databaseHucha.txt");
            if (huchaExiste.exists()) {
                boolean exitsHucha = false;
                try (Scanner sc = new Scanner(new File("databaseHucha.txt"))) {
                    if (sc.hasNext())   exitsHucha = true;
                } catch (FileNotFoundException excH) {
                    excH.printStackTrace();
                }
                if (exitsHucha == true) {
                    Hucha hucha = null;
					hucha = new Hucha();
					hucha .setBounds(0,0,350,450);
                    hucha.setVisible(true);
                    hucha.setResizable(false);  
                    hucha.setLocationRelativeTo(null); // cuando se inicie la interfaz aparecerÃ¡ en el centro de la pantalla
                    this.setVisible(false);
                } else {
                    RegistroHucha ventanaRH = new RegistroHucha();
                    ventanaRH.setBounds(0,0,350,450);
                    ventanaRH.setVisible(true);
                    ventanaRH.setResizable(false);  
                    ventanaRH.setLocationRelativeTo(null);                    
                    this.setVisible(false);
                }
            } else {
                RegistroHucha ventanaRH = new RegistroHucha();
                ventanaRH.setBounds(0,0,350,450);
                ventanaRH.setVisible(true);
                ventanaRH.setResizable(false);  
                ventanaRH.setLocationRelativeTo(null);
                this.dispose();
            }

        }
        //BOTÃ“N MODO OSCURO
        if(e.getSource() == menuOscuro){
            modoOscuro = true;

            JOptionPane.showMessageDialog(null, "Modo Oscuro Activado", "Modo Oscuro", JOptionPane.DEFAULT_OPTION);

            layoutColor =new Color(23, 165, 137);
            ButtonColor =new Color(11, 83, 69 );
            wordBlack =new Color(255,255,255);            //Para letras sobre el fondo
            wordWhite =new Color(0,0,0);        //Para letras sobre botones       
            
            getContentPane().setBackground(layoutColor); 
            botonCuenta.setBackground(ButtonColor);
            botonHucha.setBackground(ButtonColor);
            mb.setBackground(ButtonColor);
            textarea1.setBackground(ButtonColor);

            // menu = new Color(151, 141, 88);
            // menuitems = new Color(11,82,105);
            // text = new Color(234, 225, 225);           
        }
        //BOTÃ“N MODO CLARO
        if(e.getSource() == menuItemClaro){
            modoOscuro = false;

            JOptionPane.showMessageDialog(null, "Modo Claro Activado", "Modo Claro", JOptionPane.DEFAULT_OPTION);

            layoutColor =new Color(232,248, 245);
            ButtonColor =new Color(23, 165, 137);
            wordBlack =new Color(0, 0, 0);            //Para letras sobre el fondo
            wordWhite =new Color(255,255,255);        //Para letras sobre botones
            
            getContentPane().setBackground(layoutColor); 
            botonCuenta.setBackground(ButtonColor);
            botonHucha.setBackground(ButtonColor);
            mb.setBackground(ButtonColor);
            textarea1.setBackground(ButtonColor);
            
        } 
        //BOTON NOTIFICACIONES
        if(e.getSource() == menuItemNotificaciones){
            if(notificacionesOn){
            	notificacionesOn = false;
            	System.out.println(notificacionesOn);
                JOptionPane.showMessageDialog(null, "Notificaciones DESACTIVADAS", "Notificaciones", JOptionPane.DEFAULT_OPTION);
            }else if (!notificacionesOn){
            	notificacionesOn = true;
            	System.out.println(notificacionesOn);
                JOptionPane.showMessageDialog(null, "Notificaciones ACTIVADAS", "Notificaciones", JOptionPane.DEFAULT_OPTION);
            }
        }
        //BOTÃ“N VOLVER AL LOGIN
        if(e.getSource() == botonExit){
            Login login = new Login();
            login.setBounds(0,0,350,450);
            login.setVisible(true);
            login.setResizable(false);  
            login.setLocationRelativeTo(null);
            this.dispose();
        }
    }
//-----------------------------------------------------------------------------------------------------------------------------------------

    //public void stateChanged (ChangeEvent event) {
    //  if (menuOscuro.isSelected() == true) {
        //    modoOscuro = true;
            //Incluir Paleta del fondo Oscuro
       // } else {
        //	modoOscuro = false;
        	//Incluir Paleta del fondo Claro
       // }
    // }
    //Las paletas de colores se crean ahÃƒÂ­ con unas variables declaradas y ya simplemente es poner cada varible en donde corresponda, que en base a la opciÃƒÂ³n los colores se seleccionan solos    
    

    public void comprobarIngresosFijos() throws FileNotFoundException {
    	File cuentaExiste = new File("databaseIngresoFijo.txt");
 	    if (cuentaExiste.exists()) {
 	        boolean existsCuenta = false;
 	        try (Scanner sc = new Scanner(new File("databaseIngresoFijo.txt"))) {
 	            if (sc.hasNext())   existsCuenta = true;
 	        } catch (FileNotFoundException excH) {
 	        	;
 	        }
 	        if (existsCuenta == true) {
 	        	try (Scanner sc = new Scanner(new File("databaseIngresoFijo.txt"))){
 	        		cuenta.conc_ing = sc.nextLine();
 	        		cuenta.cant_ing = Double.parseDouble(sc.nextLine());
 	        		int tiempo = Integer.parseInt(sc.nextLine());
 	        		try {
						cuenta.nuevoIngresoFijo(tiempo);
					} catch (FileNotFoundException e) {
				
					}
 	        	}
 	        
 	        } 
 	    }
    }
    
    public void comprobarHucha() throws FileNotFoundException {

	    File huchaExiste = new File("databaseHucha.txt");
	    if (huchaExiste.exists()) {
	        boolean exitsHucha = false;
	        try (Scanner sc = new Scanner(new File("databaseHucha.txt"))) {
	            if (sc.hasNext())   exitsHucha = true;
	        } catch (FileNotFoundException excH) {
	            excH.printStackTrace();
	        }
	        if (exitsHucha == true) {
	            hucha.recordatorio();
	        } 
	    }
	
    }
    
//-----------------------------------------------------------------------------------------------------------------------------------------
//                  MAIN
    public static void main(String args[]) throws ParseException, FileNotFoundException {
        Principal ventanaPrincipal = new Principal();
        ventanaPrincipal.setBounds(0,0,640,535);
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.setLocationRelativeTo(null);
    } 
    
}
//-----------------------------------------------------------------------------------------------------------------------------------------
