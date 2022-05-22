package proyecto;

import java.util.Timer;
import java.util.TimerTask;

public class StopWatch {
    Timer timer;

    public StopWatch(int minutes) {
        timer = new Timer();
        timer.schedule(new StopTask(), minutes * 1000);
    }

    public static void main(String[] args) {
        new StopWatch(1);
        System.out.println("El tiempo comienza");
    }

    class StopTask extends TimerTask {
        public void run() {
            System.out.println("Time Up!");
            
        }
    }
}