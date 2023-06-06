import java.util.Timer;
import java.util.TimerTask;

public class Secondomer {

    private static int sec = 0;






    Timer timer;

    TimerTask timerTask;


    public void setTime(int value){
        sec = value;
    }

    public int getSec(){
        return sec;
    }

    public Secondomer(){

    }


    public void start(){
         timerTask = new TimerTask() {
            @Override
            public void run() {
                setTime(getSec() + 1);
                App.setGameSecDuration(getSec() + 1);

            }
        };

        timer = new Timer("Sec counter");
        new Timer().schedule(timerTask, 0, 1000);
    }


    public void addTime(int value){

    }


    public void stop(){
        timer.cancel();
        timerTask.cancel();
    }

}
