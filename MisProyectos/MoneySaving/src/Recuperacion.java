import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author necro
 */
public class Recuperacion extends JFrame{
    
    public Recuperacion() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE); //El programa no quedará en segundo plano (muere) cuando se cierra la interfaz
        // La interfaz gr�fica tiene 3 caracter�sticas fuera de los componentes: el título, un icono y el background. Estos componentes se indican en el constructor
        setTitle("Bienvenido"); //Inserta el t�tulo
        getContentPane().setBackground(Principal.layoutColor); //Selecciona el color del background con un RGB
        setIconImage(new ImageIcon(getClass().getResource("./images/enigma-icon.png")).getImage()); //Coloca icono
        
    }
    
    public static void main(String[] args) {
        Recuperacion ventanaRecuperacion = new Recuperacion();
        ventanaRecuperacion.setBounds(0,0,640,535);
        ventanaRecuperacion.setVisible(true);
        ventanaRecuperacion.setResizable(false);
        ventanaRecuperacion.setLocationRelativeTo(null);
    }     
}
