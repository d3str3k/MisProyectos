import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.*;
import java.io.BufferedWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
/*
To-do list:
- Ingreso variable/ingreso fijo
- Estilo
- Scroll view
*/
public class Cuenta extends JFrame implements ActionListener{
    private JButton btn_ing, btn_gasto, btn_volver, botonHome; //botones
    private JLabel lab_cuenta, lab_cant, lab_movimientos, lab_movs; //texto
    private double cant = 0.0; //variable donde almaceno cantidad total
    private double cant_ing, cant_gas; //ingresos y gastos
    private String conc_ing, conc_gas; //conceptos
    //ArrayLists para almacenar todos los movimientos
    private ArrayList<String> lista_conceptos = new ArrayList<>();
    private ArrayList<String> lista_cantidades = new ArrayList<>();
    //ScrollPane que aun nose como funciona
   private JScrollPane scroll;

     public Cuenta(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Cuenta");
        getContentPane().setBackground(Principal.layoutColor);
        setIconImage(new ImageIcon(getClass().getResource("./images/enigma-icon.png")).getImage());
        
        //Textos
        lab_cuenta = new JLabel("Cuenta");
        lab_cuenta.setBounds(105,20,300,30);
        lab_cuenta.setFont(new Font("Andale Mono", 3, 18)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        lab_cuenta.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(lab_cuenta);
        
        lab_cant = new JLabel(cant + "€");
        lab_cant.setBounds(105,50,300,30);
        lab_cant.setFont(new Font("Andale Mono", 3, 18)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        lab_cant.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(lab_cant);

        lab_movimientos = new JLabel("Movimientos:\n");
        lab_movimientos.setBounds(105,240,300,30);
        lab_movimientos.setFont(new Font("Andale Mono", 3, 18)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        lab_movimientos.setForeground(Principal.wordBlack); //Seleccionar color texto
        add(lab_movimientos);
        
        lab_movs = new JLabel("");
        lab_movs.setBounds(105,250,300,30);
        lab_movs.setFont(new Font("Andale Mono", 3, 18)); //Seleccionar la fuente, estilo (cursiva,...), tamaño (en píxeles)
        lab_movs.setForeground(Principal.wordBlack); //Seleccionar color texto
       // scroll = new JScrollPane(lab_movs);
       // scroll.setBounds(105,250,300,300);
       // add(scroll);
        add(lab_movs);
        
        //Botones
        btn_ing =  new JButton("Realizar ingreso");
        btn_ing.setBounds(125,100,100,30);
        btn_ing.setBackground(Principal.ButtonColor);
        btn_ing.setFont(new Font("Andale Mono", 1, 14));
        btn_ing.setForeground(Principal.wordWhite);
        btn_ing.addActionListener(this); //El componente al que se le va a agregar el evento es a este botón
        add(btn_ing);

        btn_gasto =  new JButton("Realizar gasto");
        btn_gasto.setBounds(125,150,100,30);
        btn_gasto.setBackground(Principal.ButtonColor);
        btn_gasto.setFont(new Font("Andale Mono", 1, 14));
        btn_gasto.setForeground(Principal.wordWhite);
        btn_gasto.addActionListener(this); //El componente al que se le va a agregar el evento es a este botón
        add(btn_gasto);
        
        btn_volver =  new JButton("Volver");
        btn_volver.setBounds(125,200,100,30);
        btn_volver.setBackground(Principal.ButtonColor);
        btn_volver.setFont(new Font("Andale Mono", 1, 14));
        btn_volver.setForeground(Principal.wordWhite);
        btn_volver.addActionListener(this); //El componente al que se le va a agregar el evento es a este botón
        add(btn_volver);
        
        ImageIcon iconHome = new ImageIcon(getClass().getResource("./images/casa(1)(2).png"));
        botonHome =  new JButton(iconHome);
        botonHome.setBorder(BorderFactory.createEmptyBorder());
        botonHome.setContentAreaFilled(false);
        botonHome.setBounds(145,350,40,40);
        botonHome.setFont(new Font("Andale Mono", 1, 26));
        botonHome.addActionListener(this); 
        add(botonHome);
     }
     
        private void nuevoIngreso() throws IOException {
            cant_ing = Double.parseDouble(JOptionPane.showInputDialog("Cantidad a ingresar"));
            if(cant_ing<=0){
                JOptionPane.showMessageDialog(this, "Cantidad incorrecta");
            }
            else{
            conc_ing=JOptionPane.showInputDialog("Introduzca el concepto");
            cant += cant_ing;
            //Añado a los arrays este nuevo movimiento
            lista_conceptos.add(conc_ing);
            lista_cantidades.add(String.valueOf(cant_ing));
            //Estos dos metodos son para almacenar estas variables en los archivos txt
            aniadirCantidadTotal();
            aniadirMovimiento();
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
            cant -= cant_gas;
            lista_conceptos.add(conc_gas);
            lista_cantidades.add("-" + String.valueOf(cant_gas));
            aniadirCantidadTotal();
            aniadirMovimiento();
            }
        }
        
        private String imprimirMovimientos(){
            String res = "";
            for(int i=0; i<lista_conceptos.size(); i++){
                res += lista_conceptos.get(i) + " " + lista_cantidades.get(i) + "\n";
            }
            return res;
        }
        
        //Para aniadir a los txt los valores que se van introduciendo
        public void aniadirCantidadTotal() throws IOException{
            File archivo = new File("CantidadTotal.txt");
            BufferedWriter bw;
            //Si no existe el archivo, se crea
            if(!archivo.exists()){
                archivo.createNewFile();
            }
            //Si existe, se intenta almacenar el valor deseado
            else{
        try (PrintWriter pw = new PrintWriter("CantidadTotal.txt")) {
            pw.print(String.valueOf(cant));
        }catch(IOException e){
                System.out.println(e.getMessage());
            }
            }
        }
        
        //Muy parecido a aniadirCantidadTotal()
        public void aniadirMovimiento() throws IOException{
            File archivo = new File("Movimientos.txt");
            BufferedWriter bw;
            if(!archivo.exists()){
                archivo.createNewFile();
            }
            else{
        try (PrintWriter pw = new PrintWriter("Movimientos.txt")) {
            pw.print(imprimirMovimientos());
        }catch(IOException e){
                System.out.println(e.getMessage());
            }
            }
        }
        
    //Sinceramente los try y catch no los entiendo pero si los quito me da error
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn_volver) {
            Principal ventanaPrincipal = new Principal();
            ventanaPrincipal.setBounds(0,0,640,535);
            ventanaPrincipal.setVisible(true);
            ventanaPrincipal.setResizable(false);
            ventanaPrincipal.setLocationRelativeTo(null);
        }
         if(e.getSource() == btn_ing){
            try {
                nuevoIngreso();
            } catch (IOException ex) {
                Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            lab_cant.setText(cant + "€");
            lab_movs.setText(imprimirMovimientos());
        }
        if(e.getSource() == btn_gasto){
            try {
                nuevoGasto();
            } catch (IOException ex) {
                Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            lab_cant.setText(cant + "€");
            lab_movs.setText(imprimirMovimientos());
        }
        if(e.getSource() == botonHome){
            Principal menu = new Principal();
            menu.setBounds(0,0,640,535);
            menu.setVisible(true);
            menu.setResizable(false);
            menu.setLocationRelativeTo(null);
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