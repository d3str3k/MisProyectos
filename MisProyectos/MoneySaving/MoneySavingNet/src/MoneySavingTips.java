import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Maximo
 */
public class MoneySavingTips {  
    private String tip;
    public static final int numPhrases = 5;
    
    public MoneySavingTips () {
        // try (Scanner nometoquesloshuevosporfavor = new Scanner(new File("savingtips.txt"))) {
        //     nometoquesloshuevosporfavor.useDelimiter("[\n]");
        //     for(int i = 0; nometoquesloshuevosporfavor.hasNext(); i++) {
        //         numPhrases++;
        //     }
        //     nometoquesloshuevosporfavor.split();
        // } catch(Exception e) {
        //     e.printStackTrace();
        // }
        tip = "";
    }
    
    public String getTips (int phrase) {
        try (Scanner sc = new Scanner(new File("savingtips.txt"))) {
            sc.useDelimiter("[\n]");
            for(int i = 0; i < phrase; i++) {
                sc.next();
            }
            tip = sc.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tip;
    }
}
