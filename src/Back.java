import javax.swing.*;
import java.awt.*;

public class Back {

    Image img = new ImageIcon("source/sceneBack.jpg").getImage();


    JFrame externalFrame;

    public void setFrame(JFrame frame){
        externalFrame = frame;
    }


    public void draw (Graphics2D g){

        if(App.state.equals(App.STATES.MENU)){
            Menu menu = new Menu();
            menu.start(g);

        }
        else if (App.state.equals(App.STATES.PLAY)){
            g.drawImage(img, (int)0, (int)0, null);
        }

    }

}
