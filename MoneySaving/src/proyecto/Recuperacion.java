package proyecto;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * 
 * @author necro
 */
public class Recuperacion extends JFrame implements ActionListener{
    private JLabel label1, label2;
    private JComboBox comboProfesion;
    private JButton btn_return;
    private String profession;
    
    public Recuperacion() {
        try(Scanner sc = new Scanner(new File("recuperacion.txt"))) {
            profession = sc.next();
        } catch (Exception e) {
            System.out.println("No se ha podido leer recuperacion.txt");
        }
        
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE); //El programa no quedará en segundo plano (muere) cuando se cierra la interfaz
        // La interfaz gr�fica tiene 3 caracter�sticas fuera de los componentes: el título, un icono y el background. Estos componentes se indican en el constructor
        setTitle("Bienvenido"); //Inserta el t�tulo
        getContentPane().setBackground(Principal.layoutColor); //Selecciona el color del background con un RGB
        setIconImage(new ImageIcon(getClass().getResource("/images/enigma-icon.png")).getImage()); //Coloca icono
        
        /*
        getImage() indica que es una imagen
        getResource() carga la imagen
        getClass() obtiene el nombre de nuestra clase y la muestra en ella
        */
        
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/enigma-icon.png"));
        Image image = imageIcon.getImage();//1
        Image newimage = image.getScaledInstance(120,105, java.awt.Image.SCALE_SMOOTH);//2
        imageIcon = new ImageIcon(newimage);//3 estas tres líneas sirven para que la imagen se cargue correctamente
        label1 = new JLabel(imageIcon); //carga la imagen cargada en al varaible imagen en la label1
        label1.setBounds(60,0, 229, 200); //Seleccionar dimensiones y coordenadas en píxeles (derecha, abajo, ancho, alto)
        add(label1); //todo lo anterior se añade a label1
        
        label2 = new JLabel("¿Cuál es su profesión ideal?");
        label2.setBounds(60,240,220,25);
        add(label2);
        
        comboProfesion = new JComboBox();
        comboProfesion.setBounds(60,273,220,25);
        comboProfesion.setBackground(Principal.ButtonColor);
        comboProfesion.setFont(new Font("Andale Mono", 1, 14));
        comboProfesion.setForeground(Principal.wordBlack);
        add(comboProfesion);
        comboProfesion.addItem("Granjer@");
        comboProfesion.addItem("Pescador/a");
        comboProfesion.addItem(profession); 
       
        btn_return =  new JButton("Ingresar");
        btn_return.setBounds(125,300,100,30);
        btn_return.setBackground(Principal.ButtonColor);
        btn_return.setFont(new Font("Andale Mono", 1, 14));
        btn_return.setForeground(Principal.wordWhite);
        btn_return.addActionListener(this); //El componente al que se le va a agregar el evento es a este botón
        add(btn_return);
    }
    
    private boolean check_in() {
        if(comboProfesion.getSelectedItem().toString() == profession) {
            return true;
        } else {
            return false;
        }

    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn_return) {
        	String valid_pin = "";
            if(check_in()) {
            	String [] database;
            	try (Scanner sc = new Scanner(new File("database.txt"))) {
            		boolean check = sc.hasNext(); 
            		if(check) {
                            String aux = sc.next(); 
                            database = aux.split("[:]");
                            valid_pin = database[1];
            		} else {
                        JOptionPane.showMessageDialog(null,  "No te has registrado", "Error: usuario no registrado", JOptionPane.ERROR_MESSAGE);
            		}
            		
            	} catch (FileNotFoundException e1) {
                            JOptionPane.showMessageDialog(null,  "No te has registrado", "Error: usuario no registrado", JOptionPane.ERROR_MESSAGE);
                } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                }
	            	JOptionPane.showMessageDialog(null, "PIN: " + valid_pin, "Autenticación realizada correctamente", JOptionPane.DEFAULT_OPTION);
	                Login ventanaLogin = new Login();
	                ventanaLogin.setBounds(0,0,350,450);
	                ventanaLogin.setVisible(true);
	                ventanaLogin.setResizable(false);  
	                ventanaLogin.setLocationRelativeTo(null);
	                this.dispose();
            	}
            } else {
                JOptionPane.showMessageDialog(null, "Repuesta introducida inválida" ,"Error en autenticación", JOptionPane.ERROR_MESSAGE);
            }
        }
    

    
    public static void main(String[] args) {
        Recuperacion ventanaRecuperacion = new Recuperacion();
        ventanaRecuperacion.setBounds(0,0,640,535);
        ventanaRecuperacion.setVisible(true);
        ventanaRecuperacion.setResizable(false);
        ventanaRecuperacion.setLocationRelativeTo(null);
    }     
}
