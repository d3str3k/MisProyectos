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
    private double cantidadObjetivo;
    private int ritmoAhorro; // Cada cuantos minutos hay que mandar notificación
    private int[] date = new int[3]; // dd/mm/yyyy
    private double ahorroAcumulado;

    private JTextField insertName, insertCount;
    private JTextField insertYear, insertMonth, insertDay;
    private JTextField insertMas;
    private JLabel label1, labelLogo;
    private JButton buttonRitmoAhorro, buttonAhorroMas, buttonAceptarConfirmacion, buttonRechazarConfirmacion, botonHome, buttonCambioRitmo;
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
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("./images/enigma-hucha-PhotoRoom.png"));
        Image image = imageIcon.getImage();//1
        Image newimage = image.getScaledInstance(120,90, java.awt.Image.SCALE_SMOOTH);//2
        imageIcon = new ImageIcon(newimage);//3 estas tres lÃ­neas sirven para que la imagen se cargue correctamente
        labelLogo = new JLabel(imageIcon);  
        labelLogo.setBounds(20,5,120,125);
        add(labelLogo);

        // Label: Cantidad Objetivo
        label1 = new JLabel("Cantidad Objetivo: ");
        label1.setBounds(20,125,300,30);
        label1.setFont(new Font("Andale Mono", 3, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label1);
        // Label: Valor Cantidad objetivo
        label1 = new JLabel(String.valueOf(cantidadObjetivo));
        label1.setBounds(170,125,350,30);
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
        label1.setBounds(20,165,150,30);
        label1.setFont(new Font("Andale Mono", 3, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label1);
        // Label: Valor Cantidad objetivo
        label1 = new JLabel(porTiempoS(ritmoAhorro) + " €/" + ritmoAhorro + "min");
        label1.setBounds(170,165,150,30);
        label1.setFont(new Font("Andale Mono", 3, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label1);


        // Label: Modificar ritmo ahorro
        label1 = new JLabel("Modificar Ritmo Ahorro: ");
        label1.setBounds(20,195,170,30);
        label1.setFont(new Font("Andale Mono", 3, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label1);
        // Choice: modificar ritmo ahorro
        choice = new Choice();
        choice.add("1");
        choice.add("2");
        choice.add("3");
        choice.setBounds(200,195,50,25);
        choice.setBackground(Principal.layoutColor);
        choice.setFont(new Font("Andale Mono", 1, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        choice.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(choice);
        // Button: OK
        buttonRitmoAhorro =  new JButton("OK");
        buttonRitmoAhorro.setBounds(255,195,60,25);
        buttonRitmoAhorro.setBackground(Principal.ButtonColor);
        buttonRitmoAhorro.setFont(new Font("Andale Mono", 1, 12));
        buttonRitmoAhorro.setForeground(Principal.wordWhite);
        buttonRitmoAhorro.addActionListener(this); //El componente al que se le va a agregar el evento es a este botón
        add(buttonRitmoAhorro);


        
        // Label: Ahorrar mas
        label1 = new JLabel("Ahorrar más: ");
        label1.setBounds(20,220,170,30);
        label1.setFont(new Font("Andale Mono", 3, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label1);
        // insertField: Cantidad extra para ahorrar
        insertMas = new JTextField();
        insertMas.setBounds(170,225,80,25);
        insertMas.setBackground(Principal.layoutColor);
        insertMas.setFont(new Font("Andale Mono", 1, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        insertMas.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(insertMas);
         // Button: OK
        buttonRitmoAhorro =  new JButton("OK");
        buttonRitmoAhorro.setBounds(255,225,60,25);
        buttonRitmoAhorro.setBackground(Principal.ButtonColor);
        buttonRitmoAhorro.setFont(new Font("Andale Mono", 1, 12));
        buttonRitmoAhorro.setForeground(Principal.wordWhite);
        buttonRitmoAhorro.addActionListener(this); //El componente al que se le va a agregar el evento es a este botón
        add(buttonRitmoAhorro);


        // Label: Ahorro acumulado
        label1 = new JLabel("AHORRO ACUMULADO");
        label1.setBounds(20,270,170,30);
        label1.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label1);
        // Label: Ahorro acumulado cantidad
        label1 = new JLabel(String.valueOf(ahorroAcumulado));
        label1.setBounds(60,300,170,30);
        label1.setFont(new Font("Andale Mono", 3, 20)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.ButtonColor); //Seleccionar color texto
        add(label1);


        // Label: Cantidad Restante
        label1 = new JLabel("RESTANTE");
        label1.setBounds(210,270,170,30);
        label1.setFont(new Font("Andale Mono", 3, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label1);
        // Label: Ahorro acumulado cantidad
        label1 = new JLabel(String.valueOf(cantidadObjetivo-ahorroAcumulado));
        label1.setBounds(215,300,170,30);
        label1.setFont(new Font("Andale Mono", 3, 20)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label1.setForeground(Principal.ButtonColor); //Seleccionar color texto
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
        if (e.getSource() == buttonCambioRitmo) {
            // Codigo para modificar el ritmo en la base de datos
        }
    }
    public void recordatorio() {
        Timer timer = new Timer (10*1000, new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                int si = JOptionPane.showConfirmDialog(null, "Desea ahorrar " + porTiempoS(ritmoAhorro),
                "Confirmación de ahorro", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
                if (si == 0) {
                    // JOptionPane.showMessageDialog(null, "Has pulsado si");
                    recalcular(porTiempoF(ritmoAhorro));
                    // gastoHucha(porTiempoF(ritmoAhorro));
                    ////////////////////////////////////////////
                    // MODIFICAR DINERO EN CUENTA
                    ////////////////////////////////////////////
                }
            }
        });
        timer.start();
    }
    private String porTiempoS(int ritmoAhorro) {
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

    private double porTiempoF(int ritmoAhorro) {
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
        return ((cantidadObjetivo - ahorroAcumulado) / minTotales);
    }

    private void confirmacion() {

    }

    // Metodo para modificar el valor ahorroAcumulado en la base de datos y en la cuenta
    private void recalcular(double d) {
        ahorroAcumulado += d;
        
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
