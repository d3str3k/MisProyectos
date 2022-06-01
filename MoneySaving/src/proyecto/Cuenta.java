package proyecto;

import javax.swing.*;

import java.awt.event.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.util.Timer;
import java.util.TimerTask;

public class Cuenta extends JFrame implements ActionListener {

    private Color layoutColor =new Color(232,248, 245);
    private Color buttonColor =new Color(23, 165, 137);
    private Color wordBlack =new Color(0, 0, 0);
    private Color wordWhite = new Color(255, 255, 255);

    private JButton btn_ing, btn_gasto, botonHome, btn_fijo; //botones

    private JLabel lab_cuenta, lab_cant, lab_movimientos, lab_movs; //texto

    private double cant = 0.0; //variable donde almaceno cantidad total
    public double cant_ing, cant_gas; //ingresos y gastos
    public String conc_ing, conc_gas; //conceptos


    //ScrollPane
    private JScrollPane scroll;

     public Cuenta(){
    	 
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE); //se cierra solo esta ventana
        setTitle("Cuenta");
        getContentPane().setBackground(layoutColor);
        setIconImage(new ImageIcon(getClass().getResource("/images/enigma-icon.png")).getImage());

        //Textos
        lab_cuenta = new JLabel("Cuenta");
        lab_cuenta.setBounds(140,20,300,30);
        lab_cuenta.setFont(new Font("Andale Mono", 3, 18)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        lab_cuenta.setForeground(wordBlack); //Seleccionar color texto
        add(lab_cuenta);

        lab_cant = new JLabel(recuperarCantTotal() + " €");
        lab_cant.setBounds(150,50,300,30);
        lab_cant.setFont(new Font("Andale Mono", 3, 18)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        lab_cant.setForeground(wordBlack); //Seleccionar color texto
        add(lab_cant);

        lab_movimientos = new JLabel("Movimientos:\n");
        lab_movimientos.setBounds(20,230,300,30);
        lab_movimientos.setFont(new Font("Andale Mono", 3, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        lab_movimientos.setForeground(wordBlack); //Seleccionar color texto
        add(lab_movimientos);

        lab_movs = new JLabel(recuperarMovimientos());
        lab_movs.setBounds(20,260,300,30);
        lab_movs.setFont(new Font("Andale Mono", 3, 14)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        lab_movs.setForeground(wordBlack); //Seleccionar color texto
        scroll = new JScrollPane(lab_movs);
        scroll.setBounds(20,260,300,50);
        add(scroll);

        //Botones
        btn_ing =  new JButton("Introducir ingreso");
        btn_ing.setBounds(65,90,200,30);
        btn_ing.setBackground(buttonColor);
        btn_ing.setFont(new Font("Andale Mono", 1, 14));
        btn_ing.setForeground(wordWhite);
        btn_ing.addActionListener(this); //El componente al que se le va a agregar el evento es a este botón
        add(btn_ing);

        btn_fijo =  new JButton("Introducir ingreso fijo");
        btn_fijo.setBounds(65,140,200,30);
        btn_fijo.setBackground(buttonColor);
        btn_fijo.setFont(new Font("Andale Mono", 1, 14));
        btn_fijo.setForeground(wordWhite);
        btn_fijo.addActionListener(this); //El componente al que se le va a agregar el evento es a este botón
        add(btn_fijo);        

        btn_gasto =  new JButton("Introducir gasto");
        btn_gasto.setBounds(65,190,200,30);
        btn_gasto.setBackground(buttonColor);
        btn_gasto.setFont(new Font("Andale Mono", 1, 14));
        btn_gasto.setForeground(wordWhite);
        btn_gasto.addActionListener(this); //El componente al que se le va a agregar el evento es a este botón
        add(btn_gasto);

        ImageIcon iconHome = new ImageIcon(getClass().getResource("/images/casa(1)(2).png"));
        botonHome =  new JButton(iconHome);
        botonHome.setBorder(BorderFactory.createEmptyBorder());
        botonHome.setContentAreaFilled(false);
        botonHome.setBounds(145,350,40,40);
        botonHome.setFont(new Font("Andale Mono", 1, 26));
        botonHome.addActionListener(this); 
        add(botonHome);   
        
        

     }

     public void auxiliar() throws IOException {
    	 int tiempo = 0;
         String[] options = new String[] {"1", "2", "5"};
         cant_ing = Double.parseDouble(JOptionPane.showInputDialog("Introduzca ingreso"));
         if(cant_ing<=0){
             JOptionPane.showMessageDialog(this, "Cantidad incorrecta");
         }
         else{
             conc_ing = JOptionPane.showInputDialog("Introduzca el concepto");
             cant += cant_ing;
             
             int response = JOptionPane.showOptionDialog(null, "¿Cada cuántos minutos desea actualizarlo?", "Nuevo ingreso fijo",
             JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
             null, options, options[0]);
             //en response se guarda la posicion del array que se ha escogido,
             //pero nosotros queremos los minutos, asi que eso es lo que vamos a almacenar
             if (response == 0){
                 tiempo = 1;
             }
             else if(response == 1){
                 tiempo = 2;
             }
             else if(response == 2){
                 tiempo = 5;
             }
             this.nuevoIngresoFijo(tiempo);
         }
     }

     public void nuevoIngresoFijo(int tiempo) throws FileNotFoundException {
        
            //aqui guardaremos concepto, cantidad y minutos (cada uno en una linea distinta)
            File file = new File("databaseIngresoFijo.txt");
            if (!file.exists()){
                crearFicheroFijo();
            }
            
            PrintWriter fw = new PrintWriter(file.getAbsoluteFile());
            fw.print(conc_ing);
            fw.print("\n");
            fw.print("+" + String.valueOf(cant_ing));
            fw.print("\n");
            fw.print(String.valueOf(tiempo));
            fw.print("\n");
            fw.close();

            actualizarLabelMovs();
            
            cant = recuperarCantTotal();
            ejecutarIngresoFijo(tiempo*1000*30);            
     }
     
     public void ejecutarIngresoFijo(int minutos){
     	Timer timer = new Timer();
     	TimerTask task = new TimerTask() {
     		@Override
     		public void run(){
     			File file = new File("movimientos.txt");
     	        if (!file.exists()){
     	            crearFichero();
     	        }
     	        FileWriter fw;
 				try {
 					fw = new FileWriter(file.getAbsoluteFile(), true);
 					fw.append(conc_ing + " (Fijo)");
 					fw.append("\n");
 					fw.append("+" + String.valueOf(recuperarCantidadFija()));
 					fw.append("\n");
 					fw.close();
 				} catch (IOException e) {
 					e.printStackTrace();
 				}
     	       
     	        
     			JOptionPane.showMessageDialog(null, "Se ha actualizado un ingreso fijo de " + 
     			cant_ing + "€ por " + conc_ing);
     			cant = recuperarCantTotal();
     			
     	        remove(scroll);
     	        lab_movs = new JLabel(recuperarMovimientos());
     	        scroll = new JScrollPane(lab_movs);
     	        scroll.setBounds(20,260,300,50);
     	        add(scroll);
     	        revalidate();
     	        repaint();
     	        
     	        lab_cant.setText(Double.toString(recuperarCantTotal()) + "€");
     			
     		}
     		
     	};
     	timer.scheduleAtFixedRate(task, 0, minutos);
     }
     

    private void nuevoIngreso() throws IOException {
        cant_ing = Double.parseDouble(JOptionPane.showInputDialog("Introduzca ingreso"));
        if(cant_ing<=0){
            JOptionPane.showMessageDialog(this, "Cantidad incorrecta");
        }
        else{
        conc_ing = JOptionPane.showInputDialog("Introduzca el concepto");
        //Almacenar valores en txt
        File file = new File("movimientos.txt");
        if (!file.exists()){
            crearFichero();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        fw.append(conc_ing);
        fw.append("\n");
        fw.append("+" + String.valueOf(cant_ing));
        fw.append("\n");
        fw.close();
        
        actualizarLabelMovs();
        cant = recuperarCantTotal();

        }
    }
    
    private void cantidadNegativa() {
    	if(cant<0) {
    		JOptionPane.showMessageDialog(this, "¡Ojo: estás en números rojos!");
    	}
    }
    
    //Funciona exactamente igual que nuevoIngreso()
    private void nuevoGasto() throws IOException {
        cant_gas = Double.parseDouble(JOptionPane.showInputDialog("Ingrese gasto"));
        if(cant_gas<=0){
            JOptionPane.showMessageDialog(this, "Cantidad incorrecta");
        }
        else{
        conc_gas = JOptionPane.showInputDialog("Introduzca el concepto");
        //Almacenar valores en txt
        File file = new File("movimientos.txt");
        if (!file.exists()){
            crearFichero();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        fw.append(conc_gas);
        fw.append("\n");
        fw.append("-" + String.valueOf(cant_gas));
        fw.append("\n");
        fw.close();
        
        actualizarLabelMovs();
        
        cant = recuperarCantTotal();
        cantidadNegativa();
        }
    }
    
    private void actualizarLabelMovs() {
        
        this.remove(scroll);
        lab_movs = new JLabel(recuperarMovimientos());
        scroll = new JScrollPane(lab_movs);
        scroll.setBounds(20,260,300,50);
        add(scroll);
        revalidate();
        repaint();
        
    }
    public double recuperarCantTotal(){
        double cant_total = 0.0;
        String ing;
        try (Scanner sc = new Scanner(new File("movimientos.txt"))) {
        while(sc.hasNext()){
            ing = sc.nextLine();
            cant_total += Double.parseDouble(sc.nextLine());
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }            
        return cant_total;
    }
        
    public String recuperarMovimientos(){
        StringBuilder sw = new StringBuilder(1000);
    try (Scanner sc = new Scanner(new File("movimientos.txt"))) {
        sc.useDelimiter(":");
        while(sc.hasNext()){
            sw.append("Concepto: ");
            sw.append(sc.nextLine());
            sw.append(" ---> ");
            sw.append("Cantidad: " );
            sw.append(sc.nextLine());
            sw.append("    ");
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    return sw.toString();
    }

    public double recuperarCantidadFija() {
        double cant_fija = 0.0;
        String ing;
        try (Scanner sc = new Scanner(new File("databaseIngresoFijo.txt"))) {
            ing = sc.nextLine();
            cant_fija = Double.parseDouble(sc.nextLine());
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }            
        return cant_fija;
    }
    
    private void crearFichero(){
        File file= new File("movimientos.txt");
        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void crearFicheroFijo(){
        File file= new File("databaseIngresoFijo.txt");
        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }        
    
   
    /*Funciona como cualquier otro gasto pero en lugar de un concepto
    se va a indicar que es un gasto que viene de parte de hucha.
    Asi que lo unico que hace es restar esa cantidad a la total
    y guardarlo en "movimientos.txt"*/
    public void gastoHucha(double gasto) throws IOException{
        File file = new File("movimientos.txt");
        if (!file.exists()){
            crearFichero();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        fw.append("Gasto hucha");
        fw.append("\n");
        fw.append("-" + String.valueOf(gasto));
        fw.append("\n");
        fw.close();
        
        this.remove(scroll);
        lab_movs = new JLabel(recuperarMovimientos());
        scroll = new JScrollPane(lab_movs);
        scroll.setBounds(20,260,300,50);
        add(scroll);
        revalidate();
        repaint();
        
        cant = recuperarCantTotal();            
    }
    
    /*Por un lado tengo el timer que es el "reloj" y por otro el timerTask
     que es donde se va a ejecutar la tarea.
     */
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botonHome) {
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
        else if(e.getSource() == btn_ing){
            try {
                nuevoIngreso();
            } catch (IOException ex) {
                Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
                lab_cant.setText(cant + "€");
        }
        else if(e.getSource() == btn_gasto){
            try {
                nuevoGasto();
            } catch (IOException ex) {
                Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
                lab_cant.setText(cant + "€");
        }
        else if(e.getSource() == btn_fijo){
            try {
                auxiliar();
            } catch (IOException ex) {
                Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            lab_cant.setText(cant + "€");
        }
    }
    
	public static void main(String[] args){
	    Cuenta c = new Cuenta();
	        c.setBounds(0,0,350,450);
	        c.setVisible(true);
	        c.setResizable(false);  
	        c.setLocationRelativeTo(null);
	}
}