/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
/**
 *
 * @author David
 */
public class Notificaciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        
        DesktopNotify.showDesktopMessage(
        "Se trata de una notificación,",
        "con DS Desktop Notify, la visualización de notificaciones en la pantalla es rápida y sencilla",
        DesktopNotify.SUCCESS);  
        
        
        
        DesktopNotify.showDesktopMessage("¿No pasa nada con los clics?", "Nótese que esta notificación no se puede cerrar con el ratón, esto se debe a que no todas las notificaciones se cierran con un clic. Se puede optar por darles un tiempo de expiración en milisegundos, de modo que las notificaciones permanezcan un tiempo determinado en la pantalla.", DesktopNotify.TIP, 14000L);
        
        
        
//        DesktopNotify.showDesktopMessage("Mensaje de Información", "Este es un mensaje de información, para propósito general. Se brinda un ícono por defecto para este tipo de mensajes, pero puede usar el que usted prefiera en su lugar.", DesktopNotify.INFORMATION);
//        
//
//        
//        DesktopNotify.showDesktopMessage("Mensaje de Advertencia", "Este es un mensaje de advertencia. Se brinda un ícono por defecto para este tipo de mensajes, pero puede usar el que usted prefiera en su lugar.", DesktopNotify.WARNING);
//        
//        
//        
//        DesktopNotify.showDesktopMessage("Mensaje de Error", "Este es un mensaje de error. Se brinda un ícono por defecto para este tipo de mensajes, pero puede usar el que usted prefiera en su lugar.", DesktopNotify.ERROR);
//        
//        
//        
//        DesktopNotify.showDesktopMessage("Mensaje de Éxito", "Este es un mensaje de éxito, útil para informar que un proceso o tarea se ha concluido sin problemas. Se brinda un ícono por defecto para este tipo de mensajes, pero puede usar el que usted prefiera en su lugar.", DesktopNotify.SUCCESS);
//        
//        
//        
//        DesktopNotify.showDesktopMessage("Mensaje de Fallo", "Este es un mensaje de fallo, útil para informar que un proceso o tarea se ha concluido con un resultado desalentador. Se brinda un ícono por defecto para este tipo de mensajes, pero puede usar el que usted prefiera en su lugar.", DesktopNotify.FAIL);
//        
//        
//        
//        DesktopNotify.showDesktopMessage("Mensaje de Ayuda", "Este es un mensaje de ayuda. Se brinda un ícono por defecto para este tipo de mensajes, pero puede usar el que usted prefiera en su lugar.", DesktopNotify.HELP);
//        
//        
//        
//        DesktopNotify.showDesktopMessage("Mensaje de Tip", "Este es un tip. Se brinda un ícono por defecto para este tipo de mensajes, pero puede usar el que usted prefiera en su lugar.", DesktopNotify.TIP);
//        
//        
//        
//        DesktopNotify.showDesktopMessage("Mensaje de Pedido de Entrada", "Este es un mensaje de pedido de entrada, úselo para solicitar datos (redirigiendo a algún formulario de ingreso, por supuesto). Se brinda un ícono por defecto para este tipo de mensajes, pero puede usar el que usted prefiera en su lugar.", DesktopNotify.INPUT_REQUEST, new ActionListener(){@Override public void actionPerformed(ActionEvent evt){
            //Podemos utilizar un formulario Frame o cualquier otro
            //new Formulario().setVisible(true);
            
            //Tambien podemos mostrar mensajes simples, sin iconos
            //DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            //DesktopNotify.showDesktopMessage("", "También puede mostrar mensajes sin un título, sin un ícono, con un ícono personalizado, un tema de color diferente, o con la combinación de elementos que desee.", DesktopNotify.INFORMATION);
            
            //Tambien puede mostrar un Mensaje de Dialogo
            //String texto = JOptionPane.showInputDialog(null, "Ingrese Texto");
            //System.out.println(texto);
       // }});
        
        
        
//        DesktopNotify.showDesktopMessage("Un mensaje sin ícono", "Mensaje", DesktopNotify.TIP, new ActionListener(){
//            @Override 
//            public void actionPerformed(ActionEvent evt){
//            //Eventos al hacer click
//            DesktopNotify.showDesktopMessage("Mensaje de Ayuda", "Este es un mensaje de ayuda. Se brinda un ícono por defecto para este tipo de mensajes, pero puede usar el que usted prefiera en su lugar.", DesktopNotify.HELP, new ActionListener(){
//            @Override 
//            public void actionPerformed(ActionEvent evt){
//            //Eventos al hacer click
//
//            System.out.println("Notificacion 2");
//        }});
//            
//        }});
//        
//        
//        
//        DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
//        DesktopNotify.showDesktopMessage("Eventos de Acción", "También puede añadir un ActionListener para especificar una acción a llevarse a cabo en caso el usuario haga clic sobre la notificación. Por ejemplo, esta notificación trae un evento. Haga clic para ejecutarlo.", DesktopNotify.TIP, new ActionListener(){
//            @Override public void actionPerformed(ActionEvent evt){
//        JOptionPane.showMessageDialog(null, "Este es un mensaje de JOptionPane, creado como resultado\ndel evento de la notificación en la que hizo clic, y con esto\nconcluye la demostración.\nEn futuras versiones se pueden incluir nuevas funciones y\nopciones para personalizar aún más las notificaciones.\n\nPuede enviar sugerencias a: the.drag.shot@gmail.com\n\n¡Gracias por descargar este software!", "Acción", 1);
//        }
//        }
//        );
//       
//        
//        
//        DesktopNotify.showDesktopMessage("¿No pasa nada con los clics?", "Nótese que esta notificación no se puede cerrar con el ratón, esto se debe a que no todas las notificaciones se cierran con un clic. Se puede optar por darles un tiempo de expiración en milisegundos, de modo que las notificaciones permanezcan un tiempo determinado en la pantalla.", DesktopNotify.TIP, 10000L);
//        
//        
//        
//        DesktopNotify.showDesktopMessage("Mensaje de Información", "Cuerpo del texto", DesktopNotify.INFORMATION, new ActionListener(){
//        @Override
//        public void actionPerformed(ActionEvent evt){
//            System.out.println("Hicistes clic en la Notificación de Información");
//        }
//        }
//        );
        
    }
    
}
