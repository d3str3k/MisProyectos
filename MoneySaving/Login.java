import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 *
 * @author maximo
 */
public class Login extends JFrame implements ActionListener {
    private JTextField textfield1; //JTextField es el campo donde se introducirá el Pin
    private JLabel label1, label2, label3, label4; //Variables
    private JButton boton1; // Botón para ingresar el PIN
    public static String pin = "";
    public static String nombre ="Máximo";
    
    public Login () {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE); //El programa no quedará en segundo plano (muere) cuando se cierra la interfaz
        // La interfaz gráfica tiene 3 características fuera de los componentes: el título, un icono y el background. Estos componentes se indican en el constructor
        setTitle("Bienvenido"); //Inserta el título
        getContentPane().setBackground(new Color(0, 0, 0)); //Selecciona el color del background con un RGB
        setIconImage(new ImageIcon(getClass().getResource("./images/enigma-icon.png")).getImage()); //Coloca icono
        
        /*
        getImage() indica que es una imagen
        getResource() carga la imagen
        getClass() obtiene el nombre de nuestra clase y la muestra en ella
        */
        
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("./images/enigma-icon.png"));
        Image image = imageIcon.getImage();//1
        Image newimage = image.getScaledInstance(120,105, java.awt.Image.SCALE_SMOOTH);//2
        imageIcon = new ImageIcon(newimage);//3 estas tres líneas sirven para que la imagen se cargue correctamente
        label1 = new JLabel(imageIcon); //carga la imagen cargada en al varaible imagen en la label1
        label1.setBounds(60,0, 229, 200); //Seleccionar dimensiones y coordenadas en píxeles (derecha, abajo, ancho, alto)
        add(label1); //todo lo anterior se añade a label1
        
        label2 = new JLabel("MoneySaving");
        label2.setBounds(105,175,300,30);
        label2.setFont(new Font("Andale Mono", 3, 18)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label2.setForeground(new Color(255,255,255)); //Seleccionar color texto
        add(label2);
        
        label3 = new JLabel("Ingrese su PIN:");
        label3.setBounds(42,212,200,30);
        label3.setFont(new Font("Andale Mono", 1, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(new Color(255,255,255)); //Seleccionar color texto
        add(label3);
        
        label4 = new JLabel("©Enigma");
        label4.setBounds(150,375,300,30);
        label4.setFont(new Font("Andale Mono", 1, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label4.setForeground(new Color(0,0,0)); //Seleccionar color texto
        add(label4);
        
        textfield1 = new JTextField();
        textfield1.setBounds(45,240,255,25);
        textfield1.setBackground(new Color(0,0,0));
        textfield1.setFont(new Font("Andale Mono", 1, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        textfield1.setForeground(new Color(0,255,0)); //Seleccionar color texto
        add(textfield1);
        
        boton1 =  new JButton("Ingresar");
        boton1.setBounds(125,280,100,30);
        boton1.setBackground(new Color(255,255,255));
        boton1.setFont(new Font("Andale Mono", 1, 14));
        boton1.setForeground(new Color(0,0,0));
        boton1.addActionListener(this); //El componente al que se le va a agregar el evento es a este botón
        add(boton1);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == boton1) {
            pin = textfield1.getText().trim(); //trim hace que elimina los espacios anteriores al texto
            if(pin.equals("")) {
                JOptionPane.showMessageDialog(null, "Debes de ingresar tu PIN", "Error Login", JOptionPane.ERROR_MESSAGE);
            } else if (pin.equals("1234")){
                Licencia ventanaLicencia = new Licencia();
                ventanaLicencia.setBounds(0,0,600,360);
                ventanaLicencia.setVisible(true);
                ventanaLicencia.setResizable(false);
                ventanaLicencia.setLocationRelativeTo(null);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "El PIN introducido es inválido", "Error Login", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void main(String[] args) {
        Login ventanaLogin = new Login();
        ventanaLogin.setBounds(0,0,350,450);
        ventanaLogin.setVisible(true);
        ventanaLogin.setResizable(false);  
        ventanaLogin.setLocationRelativeTo(null); // cuando se inicie la interfaz aparecerá en el centro de la pantalla
    }
}