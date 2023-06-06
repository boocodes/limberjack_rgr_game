import javax.swing.*;
import java.awt.*;

public class Score extends JPanel {

    private static Graphics2D g;

    public Score(){

    }
    public void draw(Graphics2D gArg, App app){
        g = gArg;
        g.setColor(Color.BLACK);
        g.drawString("Ваш счет: " + App.getScore(), 70, 70);

        Font scoreFont = new Font("Arial", Font.BOLD, 24);

        g.setFont(scoreFont);
    }

    public void hide(){
    }

}
