import java.util.Timer;
import java.util.TimerTask;

public class SmoothCounterForTimerScoreBar {


    private static int value = 400;


    Timer timer;

    TimerTask timerTask;


    public int getValue(){
        return value;
    }

    public void setValue(int newValue){
        value = newValue;
    }



    public SmoothCounterForTimerScoreBar(){

    }

    public void start(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if(value == 0){
                    timer.cancel();
                    timerTask.cancel();
                }
                if(value >= 400){
                    setValue(400);
                }
                setValue((int) (value - 5 * (App.getGameSecDuration() * 0.4)));
            }
        };

        timer = new Timer("Smooth counter for timer score bar");
        new Timer().schedule(timerTask, 0, 100);

    }

    public void addValueAfterSuccessCut(int argValue){
        value += argValue;
    }

    public void stop(){
        timer.cancel();
        timerTask.cancel();
    }


}
