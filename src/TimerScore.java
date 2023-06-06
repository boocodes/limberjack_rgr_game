import javax.swing.*;
import java.awt.*;
import java.sql.Time;

public class TimerScore {

    private int  currentValue = 200;

    private int maximumValue = 400;

    private int minimumValue = 0;

    public void setCurrentValue(int new_currentValue){
        currentValue = new_currentValue;
    }



    public TimerScore(int maximumValue, int minimumValue){
        maximumValue = maximumValue;
        minimumValue = minimumValue;
    }


    public void deleteTime(int value){
        currentValue -= value;
    }

    public void addTime(int value){
        currentValue += value;
    }


    public TimerScore(){

    };

    public void draw(Graphics2D g, int currentValue){
        if(currentValue <= 0){
            App.state = App.STATES.MENU;
            App.setGameSecDuration(0);
        }

        g.setColor(Color.WHITE);
        g.fillRect(100, 20, 400, 20);
        g.setColor(Color.green);
        g.fillRect(100, 20, currentValue, 20);


    }




}
