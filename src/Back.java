import javax.swing.*;
import java.awt.*;

public class Back {

    Image img = new ImageIcon("source/sceneBack.jpg").getImage();


    JFrame externalFrame;

    public void setFrame(JFrame frame){
        externalFrame = frame;
    }


    public void draw (Graphics2D g, App frame){
        g.fillRect(0,0,600,944);
        if(App.state.equals(App.STATES.MENU)){
            g.setColor(Color.CYAN);
            Menu menu = new Menu(g, frame);

        }
        else if (App.state.equals(App.STATES.PLAY)){
            g.drawImage(img, (int)0, (int)0, null);

        }

    }

}
