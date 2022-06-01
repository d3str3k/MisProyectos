package proyecto;


import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.awt.*;
import java.util.Date;

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



    private Color layoutColor =new Color(232,248, 245);
    private Color buttonColor =new Color(23, 165, 137);
    private Color wordBlack =new Color(0, 0, 0);            // Para las letras sobre fondo layoutColor
    private Color wordWhite = new Color(255, 255, 255);     // Para las letras sobre botones buttonColor


    private JTextField insertName, insertCount;
    private JTextField insertYear, insertMonth, insertDay;
    private JLabel label1, label2, label3;
    private JButton buttonNext, botonHome;
    private Choice choice;

    public RegistroHucha() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Se cerrará solo esta ventana
        // La interfaz gr�fica tiene 3 caracter�sticas fuera de los componentes: el título, un icono y el background. Estos componentes se indican en el constructor
        setTitle("Hucha"); //Inserta el t�tulo
        getContentPane().setBackground(Principal.layoutColor); //Selecciona el color del background con un RGB
        setIconImage(new ImageIcon(getClass().getResource("/images/enigma-icon.png")).getImage()); //Coloca icono
        
        // Label: Bienvenido a hucha
        label1 = new JLabel("Bienvenido a hucha");
        label1.setBounds(90,20,300,30);
        label1.setFont(new Font("Andale Mono", 3, 18)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label1);

        // Label: Complete para crear su hucha
        label2 = new JLabel("Complete para crear su hucha:");
        label2.setBounds(25,55,300,20);
        label2.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label2.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label2);

        // Label: Inserte Nombre
        label3 = new JLabel("Nombre: ");
        label3.setBounds(25,90,100,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label3);        

        // insertField: Introducir Nombre
        insertName = new JTextField();
        insertName.setBounds(100,90,200,25);
        insertName.setBackground(Principal.layoutColor);
        insertName.setFont(new Font("Andale Mono", 1, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        insertName.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(insertName);

        // Label: Inserte Cantidad
        label3 = new JLabel("Cantidad: ");
        label3.setBounds(25,120,100,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label3);        

        // insertField: Introducir Cantidad
        insertCount = new JTextField();
        insertCount.setBounds(100,120,100,25);
        insertCount.setBackground(Principal.layoutColor);
        insertCount.setFont(new Font("Andale Mono", 1, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        insertCount.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(insertCount);

        // Label: Inserte Fecha
        label3 = new JLabel("Fecha: ");
        label3.setBounds(25,150,100,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label3);

         // Label: Dia
        label3 = new JLabel("DD");
        label3.setBounds(105,170,100,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label3);
        
        // Label: Mes
        label3 = new JLabel("MM");
        label3.setBounds(140,170,100,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label3);

        // Label: Año
        label3 = new JLabel("YYYY");
        label3.setBounds(175,170,100,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label3);

        // insertField: Introducir Dia
        insertDay = new JTextField();
        insertDay.setBounds(100,150,30,25);
        insertDay.setBackground(Principal.layoutColor);
        insertDay.setFont(new Font("Andale Mono", 1, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        insertDay.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(insertDay);

        // insertField: Introducir Mes
        insertMonth = new JTextField();
        insertMonth.setBounds(135,150,30,25);
        insertMonth.setBackground(Principal.layoutColor);
        insertMonth.setFont(new Font("Andale Mono", 1, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        insertMonth.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(insertMonth);

        // insertField: Introducir Año
        insertYear = new JTextField();
        insertYear.setBounds(170,150,50,25);
        insertYear.setBackground(Principal.layoutColor);
        insertYear.setFont(new Font("Andale Mono", 1, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        insertYear.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(insertYear);

        // Label: Plazo
        label3 = new JLabel("Plazos: ");
        label3.setBounds(25,200,50,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label3);    
        
        // Choice: Plazos
        choice = new Choice();
        choice.add("1");
        choice.add("2");
        choice.add("3");
        choice.setBounds(100,200,50,25);
        choice.setBackground(Principal.layoutColor);
        choice.setFont(new Font("Andale Mono", 1, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        choice.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(choice);

        // Label: min
        label3 = new JLabel("min");
        label3.setBounds(150,200,50,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label3); 

        // Label: Pulse el siguiente boton para continuar
        label3 = new JLabel("Pulse el siguiente boton para continuar ");
        label3.setBounds(25,250,400,20);
        label3.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label3);  

        buttonNext =  new JButton("Siguiente");
        buttonNext.setBounds(90,280,150,50);
        buttonNext.setBackground(Principal.ButtonColor);
        buttonNext.setFont(new Font("Andale Mono", 1, 20));
        buttonNext.setForeground(Principal.wordWhite);
        buttonNext.addActionListener(this); //El componente al que se le va a agregar el evento es a este botón
        add(buttonNext);

        ImageIcon iconHome = new ImageIcon(getClass().getResource("/images/casa(1)(2).png"));
        botonHome =  new JButton(iconHome);
        botonHome.setBorder(BorderFactory.createEmptyBorder());
        botonHome.setContentAreaFilled(false);
        botonHome.setBounds(145,350,40,40);
        botonHome.setFont(new Font("Andale Mono", 1, 26));
        botonHome.addActionListener(this); 
        add(botonHome);

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
            if(Integer.parseInt(date[0]) < 0  || Integer.parseInt(date[0]) > 31 ||
            		Integer.parseInt(date[1]) < 0 || Integer.parseInt(date[1]) > 12 ||
            		Integer.parseInt(date[2]) < 0) {
            	JOptionPane.showMessageDialog(null, "Datos introducidos inválidos", "Error valores introducidos", JOptionPane.ERROR_MESSAGE);
            }
            LocalDate fechaAuxiliar = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
            LocalDate fechaActual = LocalDate.now();
            if(fechaAuxiliar.isBefore(fechaActual) || Double.parseDouble(count) <= 0 || fechaAuxiliar.isEqual(fechaActual)) {
            	JOptionPane.showMessageDialog(null, "Datos introducidos inválidos", "Error valores introducidos", JOptionPane.ERROR_MESSAGE);
            } else {
            	String plazo = choice.getSelectedItem();

                // Introducir en la base de datos los datos anteriores
        		File file = new File("/databaseHucha.txt");
        		try {
                    file.createNewFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try (PrintWriter pw = new PrintWriter("databaseHucha.txt")) {
                    pw.print(name);
                    pw.print(":");
                    pw.print(count);
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

                File file2 = new File("/ahorroAcumuladoHucha.txt");
        		try {
                    file2.createNewFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try (PrintWriter pw = new PrintWriter("ahorroAcumuladoHucha.txt")) {
                    pw.print(0);
                } catch (FileNotFoundException excp) {
                    excp.printStackTrace();
                }

                File file3 = new File("/databaseRitmoHucha.txt");
        		try {
                    file3.createNewFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try (PrintWriter pw = new PrintWriter("databaseRitmoHucha.txt")) {
                    pw.write(plazo);
                } catch (FileNotFoundException excp) {
                    excp.printStackTrace();
                }

                Hucha ventanaH = null;
				ventanaH = new Hucha();
                ventanaH.setBounds(0,0,350,450);
                ventanaH.setVisible(true);
                ventanaH.setResizable(false);
                ventanaH.setLocationRelativeTo(null); // cuando se inicie la interfaz aparecerá en el centro de la pantalla
                ventanaH.recordatorio();
                this.dispose();
            }          

        }
        if(e.getSource() == botonHome){
            Principal menu = null;
			try {
				menu = new Principal();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
			}
            menu.setBounds(0,0,640,535);
            menu.setVisible(true);
            menu.setResizable(false);
            menu.setLocationRelativeTo(null);
            this.dispose();
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
