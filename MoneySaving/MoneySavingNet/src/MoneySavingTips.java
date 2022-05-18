import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Maximo
 */
public class MoneySavingTips {  
    private String tip;
    public int numPhrases = 5;
    
    public MoneySavingTips () {
        numPhrases = 0;
        try (Scanner nometoquesloshuevos = new Scanner(new File("savingtips.txt"))) {
            
            for(int i = 0; nometoquesloshuevos.hasNext(); i++) {
                numPhrases++;
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
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
