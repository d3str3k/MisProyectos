import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.awt.*;


public class Hucha extends JFrame implements ActionListener {

    private String nombreHucha;
    private float cantidadObjetivo;
    private int ritmoAhorro; // Cada cuantos minutos hay que mandar notificación
    private int[] date = new int[3]; // dd/mm/yyyy
    private float ahorroAcumulado;

    private JTextField insertName, insertCount;
    private JTextField insertYear, insertMonth, insertDay;
    private JLabel label1, labelLogo;
    private JButton buttonRitmoAhorro, buttonAhorroMas, buttonAceptarConfirmacion, buttonRechazarConfirmacion, botonHome;
    private Choice choice;

    public Hucha() {
        llenarVariables();
        recordatorio();
        //////////////////////////////////////////////// INTERFAZ ///////////////////////////////////////////////////

        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Se cerrará solo esta ventana
        // La interfaz gr�fica tiene 3 caracter�sticas fuera de los componentes: el título, un icono y el background. Estos componentes se indican en el constructor
        setTitle("Hucha"); //Inserta el t�tulo
        getContentPane().setBackground(Principal.layoutColor); //Selecciona el color del background con un RGB
        setIconImage(new ImageIcon(getClass().getResource("./images/enigma-icon.png")).getImage()); //Coloca icono


        // Label: Nombre hucha
        label1 = new JLabel(nombreHucha);
        label1.setBounds(160,50,300,50);
        label1.setFont(new Font("Andale Mono", 3, 30)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label1);

        // Image: hucha cerdo
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("./images/enigma-hucha.png"));
        Image image = imageIcon.getImage();//1
        Image newimage = image.getScaledInstance(120,90, java.awt.Image.SCALE_SMOOTH);//2
        imageIcon = new ImageIcon(newimage);//3 estas tres lÃ­neas sirven para que la imagen se cargue correctamente
        labelLogo = new JLabel(imageIcon);  
        labelLogo.setBounds(20,10,115,115);
        add(labelLogo);

        // Label: Cantidad Objetivo
        label1 = new JLabel("Cantidad Objetivo: ");
        label1.setBounds(20,125,300,30);
        label1.setFont(new Font("Andale Mono", 3, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label1);
        // Label: Valor Cantidad objetivo
        label1 = new JLabel(String.valueOf(cantidadObjetivo));
        label1.setBounds(170,125,300,30);
        label1.setFont(new Font("Andale Mono", 3, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label1);


        // Label: Fecha Límite
        label1 = new JLabel("Fecha Límite: ");
        label1.setBounds(20,145,300,30);
        label1.setFont(new Font("Andale Mono", 3, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label1);
        // Label: Valor fecha limite
        String fecha = date[0] + "/" + date[1] + "/" + date[2] ;
        label1 = new JLabel(fecha);
        label1.setBounds(170,145,300,30);
        label1.setFont(new Font("Andale Mono", 3, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label1);


        // Label: Ritmo ahorro
        label1 = new JLabel("Ritmo de ahorro: ");
        label1.setBounds(20,165,300,30);
        label1.setFont(new Font("Andale Mono", 3, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label1);
        // Label: Valor Cantidad objetivo
        label1 = new JLabel(String.valueOf(ritmoAhorro) + " €");
        label1.setBounds(170,165,300,30);
        label1.setFont(new Font("Andale Mono", 3, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label1);

        ImageIcon iconHome = new ImageIcon(getClass().getResource("./images/casa(1)(2).png"));
        botonHome =  new JButton(iconHome);
        botonHome.setBorder(BorderFactory.createEmptyBorder());
        botonHome.setContentAreaFilled(false);
        botonHome.setBounds(145,350,40,40);
        botonHome.setFont(new Font("Andale Mono", 1, 26));
        botonHome.addActionListener(this); 
        add(botonHome);


       
        
        //////////////////////////////////////////// NOTIFICAICON ///////////////////////////////////////////////////////
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botonHome){
            Principal menu = new Principal();
            menu.setBounds(0,0,640,535);
            menu.setVisible(true);
            menu.setResizable(false);
            menu.setLocationRelativeTo(null);
        }
    }
  // NOTIFICACIÓN
    public void recordatorio() {
        Timer timer = new Timer (ritmoAhorro*60*1000, new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showConfirmDialog(null, "Desea ahorrar " + porTiempo(ritmoAhorro),
                "Confirmación de ahorro", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
            }
        });
        timer.start();
    }

    private String porTiempo(int ritmoAhorro) {
        LocalDateTime dateTime = LocalDateTime.now();
        int yearRes = date[2] - dateTime.getYear();
        int monthRes = date[1] - dateTime.getMonthValue();
        int dayRes = date[0] - dateTime.getDayOfMonth();
        int hourRes = 24 - dateTime.getHour();
        int minRes = 60 - dateTime.getMinute();

        int minTotales =    yearRes*525600 + 
                            monthRes*43800 +
                            dayRes*1440 +
                            hourRes*60 +
                            minRes;
        DecimalFormat decimalFormat = new DecimalFormat("#.########");
        return decimalFormat.format((cantidadObjetivo - ahorroAcumulado) / minTotales);
    }

    private void confirmacion() {

    }

    // Metodo para modificar el valor ahorroAcumulado en la base de datos y en la cuenta
    private void recalcular(int cantidad) {
        ahorroAcumulado += cantidad;
        
    }

    // Se borran los datos del databaseHucha y se cierra la ventana Hucha
    private void quitarHucha() {
        try (Scanner sc = new Scanner(new File("databaseHucha.txt"))) {
            sc.useDelimiter("[:]");
            while (sc.hasNext()) {
                sc.remove();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.dispose();

    }

    private void llenarVariables() {
        try (Scanner sc = new Scanner(new File("databaseHucha.txt"))) {
            sc.useDelimiter("[:]");
                nombreHucha = sc.next();
                cantidadObjetivo = sc.nextFloat();
                ritmoAhorro = sc.nextInt();
                date[0] = sc.nextInt();
                date[1] = sc.nextInt();
                date[2] = sc.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (Scanner sc = new Scanner(new File("ahorroAcumuladoHucha.txt"))) {
            ahorroAcumulado = sc.nextFloat();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Hucha ventanaH = new Hucha();
        ventanaH.setBounds(0,0,350,450);
        ventanaH.setVisible(true);
        ventanaH.setResizable(false);  
        ventanaH.setLocationRelativeTo(null); // cuando se inicie la interfaz aparecerá en el centro de la pantalla
    }

}
