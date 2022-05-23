package proyecto;



import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.*;
/*
 * @author maximo
 */
public class Login extends JFrame implements ActionListener {
    private JTextField textfield1; //JTextField es el campo donde se introducirá el Pin
    private JLabel label1, label2, label3, label4; //Variables
    private JButton boton1, boton2; // Botón para ingresar el PIN
    public static String [] database = new String[2];
    private int cnt_intentos = 0;
    
    public Login () {
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
        
        label2 = new JLabel("MoneySaving");
        label2.setBounds(105,175,300,30);
        label2.setFont(new Font("Andale Mono", 3, 18)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label2.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label2);
        
        label3 = new JLabel("Ingrese su PIN:");
        label3.setBounds(42,212,200,30);
        label3.setFont(new Font("Andale Mono", 1, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label3.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label3);
        
        label4 = new JLabel("�Enigma");
        label4.setBounds(150,375,300,30);
        label4.setFont(new Font("Andale Mono", 1, 12)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        label4.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(label4);
        
        textfield1 = new JTextField();
        textfield1.setBounds(45,240,255,25);
        textfield1.setBackground(Principal.ButtonColor);
        textfield1.setFont(new Font("Andale Mono", 1, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        textfield1.setForeground(new Color(255,255,255)); //Seleccionar color texto
        add(textfield1);
        
        boton1 =  new JButton("Ingresar");
        boton1.setBounds(125,280,100,30);
        boton1.setBackground(Principal.ButtonColor);
        boton1.setFont(new Font("Andale Mono", 1, 14));
        boton1.setForeground(Principal.wordWhite);
        boton1.addActionListener(this); //El componente al que se le va a agregar el evento es a este botón
        add(boton1);

        boton2 =  new JButton("�Has olvidado tu contrase�a?");
        boton2.setBounds(38,320,270,30);
        boton2.setBackground(Principal.ButtonColor);
        boton2.setFont(new Font("Andale Mono", 1, 14));
        boton2.setForeground(Principal.wordWhite);
        boton2.addActionListener(this); //El componente al que se le va a agregar el evento es a este botón
        add(boton2);
    }
    
//    public boolean check_registered() {
//    	try(File f1 = new File("database.txt")) {
//    		return f1.exists();
//    	} catch (FileNotFoundException e) {
//    		return false;
//    	}
//    }
    
    private void block_account() throws FileNotFoundException {
    	try (PrintWriter pw = new PrintWriter("./database.txt")) {
            pw.print("/");
            pw.print(":");
            pw.print("/");
            pw.print(":");
            pw.print("false");
            pw.close();
        }
    }
    
    private boolean recoverable() {
    	try (Scanner sc = new Scanner(new File("./database.txt"))) {
    		boolean check = sc.hasNext(); // se checkea si el archivo abierto tiene un int
    		if(check) {
                    String aux = sc.next(); // como lo tiene lo carga en pin valido
                    database = aux.split("[:]");
                    if(Boolean.parseBoolean(database[2])) {
                    	return true;
                    } else {
                    	return false;
                    }
    		} else {
    			JOptionPane.showMessageDialog(null,  "No hay ning�n usuario dado de alta en el sistema", "", JOptionPane.INFORMATION_MESSAGE);
    		}
    	} catch (FileNotFoundException e) { 
			JOptionPane.showMessageDialog(null,  "No hay ning�n usuario dado de alta en el sistema", "", JOptionPane.INFORMATION_MESSAGE);
    	} catch (Exception e1) {
    		JOptionPane.showMessageDialog(null,  "Ha ocurrido un error en la lectura de sus datos", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    	return false;
    }
    
    public void login(String valid_pin, String pin, String name) throws FileNotFoundException {
    	/* Descripción
    	 * El método login va a comprobar 
    	 * 1) si el PIN es vacío, en cuyo caso mostrará un mensaje pop-up de error
    	 * 2) si el PIN introducido es válido, para ello abrirá el archivo donde se supone que se encuentra el PIN almacenado (database.txt)
    	 * 	2.1) Si el archivo existe, carga el PIN válido y lo comprueba con el PIN introducido
    	 * 		2.1.1) Si el PIN introducido es vacío muestra un pop-up de error
    	 * 		2.2.2) Si el PIN introducido es igual al PIN almacenado en database.txt carga la interfaz gráfica siguiente puesto que la fase de loguearse se ha completado con éxito
    	 * 		2.2.3) Si el PIN introducido es correcto pero no es igual al PIN almacenado, entonces muestra un pop-up de error
    	 * 	2.2) Si el archivo no existe entonces llama a registrar indicando por booleano que no existe el fichero
    	 * 	2.3) Si el archivo existe pero no almacena ningún PIN entonces llama a registrar con booleano true para indicar que el fichero sí existe
    	 * 
    	 */
    	if(pin.equals("")) {
            JOptionPane.showMessageDialog(null, "Debes de ingresar tu PIN", "Error Login", JOptionPane.ERROR_MESSAGE);
        } else {
        	boolean registered = false;
        	try (Scanner sc = new Scanner(new File("./database.txt"))) {
        		boolean check = sc.hasNext(); // se checkea si el archivo abierto tiene un int
        		if(check) {
                        String aux = sc.next(); // como lo tiene lo carga en pin valido
                        database = aux.split("[:]");
                        name = database[0];
                        valid_pin = database[1];
        		} else {
        			throw new NotRegisteredException("Usuario no registrado");
        		}
        		
        	} catch (Exception exc1) {
        		try {
                    registered = register(name, pin, false);
                    if(registered) {
                        JOptionPane.showMessageDialog(null, "Registro completado con �xito. Su PIN es: " + pin, "Registro", JOptionPane.INFORMATION_MESSAGE);
                        valid_pin = pin;
                        database[0] = name;
                        database[1] = valid_pin;
                    }
                            
                } catch (FileNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                }
        	}
        	if (pin.equals(valid_pin) && registered == false){
        		Principal ventanaPrincipal = new Principal();
                ventanaPrincipal.setBounds(0,0,640,535);
                ventanaPrincipal.setVisible(true);
                ventanaPrincipal.setResizable(false);
                ventanaPrincipal.setLocationRelativeTo(null);
                this.dispose();            
            } else if (pin.equals(valid_pin) && registered == true) {
                Licencia ventanaLicencia = new Licencia();
                ventanaLicencia.setBounds(0,0,600,360);
                ventanaLicencia.setVisible(true);
                ventanaLicencia.setResizable(false);
                ventanaLicencia.setLocationRelativeTo(null);
                this.dispose();
            }
            else if (!(pin.equals(valid_pin))){
                JOptionPane.showMessageDialog(null, "El PIN introducido es inv�lido", "Error Login", JOptionPane.ERROR_MESSAGE);
                ++cnt_intentos;
                if(cnt_intentos == 3) {
                	block_account();
                }
            } 
        }
    }	

    
    public boolean register(String name, String pin, boolean exists) throws IOException 
    {
    	/*
    	 * El método register:
    	 * 1) Si el booleano check = false entonces el fichero no existe y por lo tanto crea un fichero llamado database.txt
    	 * 2) Luego registra el valor PIN introducido por teclado en el fichero database.txt
    	 */
    	if(!exists) { 
    		File file = new File("./database.txt");
    		file.createNewFile();
                File file1 = new File("./recuperacion.txt");
                file1.createNewFile();
    	}
        name = JOptionPane.showInputDialog("Introduzca su nombre");
        if(name == null || name.isEmpty()) {
            return false;
        } else {
            String respuesta = JOptionPane.showInputDialog("�Cu�l es su profesi�n ideal?");
            try (PrintWriter pw = new PrintWriter("./database.txt")) {
                pw.print(name);
                pw.print(":");
                pw.print(pin);
                pw.print(":");
                pw.print("true");
                pw.close();
            }
            try (PrintWriter pw = new PrintWriter("./recuperacion.txt")) {
                pw.print(respuesta);
                pw.close();
            }
            return true;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == boton1) {
            String valid_pin = null;
            String pin = textfield1.getText().trim(); //trim hace que elimina los espacios anteriores al texto
            String name = "";
            try {
				login(valid_pin, pin, name);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
        }
        if(e.getSource() == boton2){
        	if (recoverable()) {
        		Recuperacion ventanaRecuperacion = new Recuperacion();
                ventanaRecuperacion.setBounds(0,0,350,450);
                ventanaRecuperacion.setVisible(true);
                ventanaRecuperacion.setResizable(false);
                ventanaRecuperacion.setLocationRelativeTo(null);
                this.dispose();
        	} else {
        		JOptionPane.showMessageDialog(null,  "Su cuenta no es recuperable", "Error", JOptionPane.CANCEL_OPTION);
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