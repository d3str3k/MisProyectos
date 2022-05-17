import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.*;


public class Hucha extends JFrame implements ActionListener {

    private String nombreHucha;
    private float cantidadObjetivo;
    private int ritmoAhorro; // Cada cuantos minutos hay que mandar notificación
    private int[] date = new int[3]; // dd/mm/yyyy
    private float ahorroAcumulado;


    private Color layoutColor =new Color(232,248, 245);
    private Color buttonColor =new Color(23, 165, 137);
    private Color wordBlack =new Color(0, 0, 0);            // Para las letras sobre fondo layoutColor
    private Color wordWhite = new Color(255, 255, 255);     // Para las letras sobre botones buttonColor


    private JTextField insertName, insertCount;
    private JTextField insertYear, insertMonth, insertDay;
    private JLabel label1;
    private JButton buttonNext;
    private Choice choice;

    public Hucha() {
        llenarVariables();
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Se cerrará solo esta ventana
        // La interfaz gr�fica tiene 3 caracter�sticas fuera de los componentes: el título, un icono y el background. Estos componentes se indican en el constructor
        setTitle("Hucha"); //Inserta el t�tulo
        getContentPane().setBackground(layoutColor); //Selecciona el color del background con un RGB
        setIconImage(new ImageIcon(getClass().getResource("./images/enigma-icon.png")).getImage()); //Coloca icono
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

    // NOTIFICACIÓN
    private void recordatorio() {

    }

    private void confirmación() {

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
