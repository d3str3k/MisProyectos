import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Antonio Manuel Pavón Villar
 * @author Anabel Ruiz Gonzalez
 */
public class RegistroHucha extends JFrame implements ActionListener {

    // private int rLayout = 220;
    // private int gLayout = 255;
    // private int bLayout = 255;

    // private int rButton = 250;
    // private int gButton = 15;
    // private int bButton = 100;

    private JTextField insertName, insertCount;
    private JTextField insertYear, insertMonth, insertDay;
    private JLabel label1, label2, label3, label4;
    private JButton buttonNext;
    private Choice choice;

    public RegistroHucha() {
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Se cerrará solo esta ventana
        // La interfaz gr�fica tiene 3 caracter�sticas fuera de los componentes: el título, un icono y el background. Estos componentes se indican en el constructor
        setTitle("Hucha"); //Inserta el t�tulo
        getContentPane().setBackground(new Color(0,0,0)); //Selecciona el color del background con un RGB
        setIconImage(new ImageIcon(getClass().getResource("./images/enigma-icon.png")).getImage()); //Coloca icono
        
        // Label: Bienvenido a hucha
        label1 = new JLabel("Bienvenido a hucha");
        label1.setBounds(90,20,300,30);
        label1.setFont(new Font("Andale Mono", 3, 18)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(new Color(255,255,255)); //Seleccionar color texto
        add(label1);

        // Label: Complete para crear su hucha
        label2 = new JLabel("Complete para crear su hucha:");
        label2.setBounds(25,55,300,20);
        label2.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label2.setForeground(new Color(255,255,255)); //Seleccionar color texto
        add(label2);

        // Label: Inserte Nombre
        label3 = new JLabel("Nombre: ");
        label3.setBounds(25,90,100,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(new Color(255,255,255)); //Seleccionar color texto
        add(label3);        

        // insertField: Introducir Nombre
        insertName = new JTextField();
        insertName.setBounds(100,90,200,25);
        insertName.setBackground(new Color(0,0,0));
        insertName.setFont(new Font("Andale Mono", 1, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        insertName.setForeground(new Color(0,255,0)); //Seleccionar color texto
        add(insertName);

        // Label: Inserte Cantidad
        label3 = new JLabel("Cantidad: ");
        label3.setBounds(25,120,100,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(new Color(255,255,255)); //Seleccionar color texto
        add(label3);        

        // insertField: Introducir Cantidad
        insertCount = new JTextField();
        insertCount.setBounds(100,120,100,25);
        insertCount.setBackground(new Color(0,0,0));
        insertCount.setFont(new Font("Andale Mono", 1, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        insertCount.setForeground(new Color(0,255,0)); //Seleccionar color texto
        add(insertCount);

        // Label: Inserte Fecha
        label3 = new JLabel("Fecha: ");
        label3.setBounds(25,150,100,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(new Color(255,255,255)); //Seleccionar color texto
        add(label3);

         // Label: Dia
        label3 = new JLabel("DD");
        label3.setBounds(105,170,100,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(new Color(255,255,255)); //Seleccionar color texto
        add(label3);
        
        // Label: Mes
        label3 = new JLabel("MM");
        label3.setBounds(140,170,100,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(new Color(255,255,255)); //Seleccionar color texto
        add(label3);

        // Label: Año
        label3 = new JLabel("YYYY");
        label3.setBounds(175,170,100,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(new Color(255,255,255)); //Seleccionar color texto
        add(label3);

        // insertField: Introducir Dia
        insertDay = new JTextField();
        insertDay.setBounds(100,150,30,25);
        insertDay.setBackground(new Color(0,0,0));
        insertDay.setFont(new Font("Andale Mono", 1, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        insertDay.setForeground(new Color(0,255,0)); //Seleccionar color texto
        add(insertDay);

        // insertField: Introducir Mes
        insertMonth = new JTextField();
        insertMonth.setBounds(135,150,30,25);
        insertMonth.setBackground(new Color(0,0,0));
        insertMonth.setFont(new Font("Andale Mono", 1, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        insertMonth.setForeground(new Color(0,255,0)); //Seleccionar color texto
        add(insertMonth);

        // insertField: Introducir Año
        insertYear = new JTextField();
        insertYear.setBounds(170,150,50,25);
        insertYear.setBackground(new Color(0,0,0));
        insertYear.setFont(new Font("Andale Mono", 1, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        insertYear.setForeground(new Color(0,255,0)); //Seleccionar color texto
        add(insertYear);

        // Label: Plazo
        label3 = new JLabel("Plazos: ");
        label3.setBounds(25,200,100,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(new Color(255,255,255)); //Seleccionar color texto
        add(label3);    
        
        // Choice: Plazos
        choice = new Choice();
        choice.add("30 seg");
        choice.add("1 min");
        choice.add("2 min");
        choice.setBounds(100,200,50,25);
        choice.setBackground(new Color(0,0,0));
        choice.setFont(new Font("Andale Mono", 1, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        choice.setForeground(new Color(0,255,0)); //Seleccionar color texto
        add(choice);

        // Label: Pulse el siguiente boton para continuar
        label3 = new JLabel("Pulse el siguiente boton para continuar ");
        label3.setBounds(25,250,400,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(new Color(255,255,255)); //Seleccionar color texto
        add(label3);  

        buttonNext =  new JButton("Siguiente");
        buttonNext.setBounds(90,300,150,50);
        buttonNext.setBackground(new Color(255,255,255));
        buttonNext.setFont(new Font("Andale Mono", 1, 20));
        buttonNext.setForeground(new Color(0,0,0));
        buttonNext.addActionListener(this); //El componente al que se le va a agregar el evento es a este botón
        add(buttonNext);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonNext) {
            String name = insertName.getText().trim(); //trim hace que elimina los espacios anteriores al texto
            String count = insertCount.getText().trim();
            String[] date = new String[3];
            date[0] = insertDay.getText().trim();
            date[1] = insertMonth.getText().trim();
            date[2] = insertYear.getText().trim();
            String plazo = choice.getSelectedItem();

            // Introducir en la base de datos los datos anteriores
    		File file = new File("./databaseHucha.txt");
    		try {
                file.createNewFile();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            try (PrintWriter pw = new PrintWriter("databaseHucha.txt")) {
                pw.print(name);
                pw.print(":");
                pw.print(count);
                pw.print(":");
                pw.print(plazo);
                pw.print(":");
                pw.print(date[0]);
                pw.print(":");
                pw.print(date[1]);
                pw.print(":");
                pw.print(date[2]);
                pw.print(":");
            } catch (FileNotFoundException exc) {
                exc.printStackTrace();
            }

            // Hucha ventanaH = new Hucha();
            // ventanaH.setBounds(0,0,350,450);
            // ventanaH.setVisible(true);
            // ventanaH.setResizable(false);  
            // ventanaH.setLocationRelativeTo(null); // cuando se inicie la interfaz aparecerá en el centro de la pantalla

        }
    }

    public static void main(String[] args) {
        RegistroHucha ventanaRH = new RegistroHucha();
        ventanaRH.setBounds(0,0,350,450);
        ventanaRH.setVisible(true);
        ventanaRH.setResizable(false);  
        ventanaRH.setLocationRelativeTo(null); // cuando se inicie la interfaz aparecerá en el centro de la pantalla
    }
    
}
