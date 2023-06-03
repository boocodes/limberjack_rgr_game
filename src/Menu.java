import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel{


    JFrame externalFrame;


    public void start(Graphics2D g){
        Image img = new ImageIcon("source/startButton.png").getImage();
        g.drawImage(img, (int)100, (int)200, null);
        g.setColor(new Color(0, 247, 255));
        g.fillRect(0, 0, 600, 940);
    }
    public Menu(){

    }

}
