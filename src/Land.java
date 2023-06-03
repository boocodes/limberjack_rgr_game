import javax.swing.*;
import java.awt.*;

public class Land {


    Image img = new ImageIcon("source/floor.png").getImage();

    public void draw (Graphics2D g){
        g.drawImage(img, (int)0, (int)560, null);
        g.setBackground(Color.BLUE);
    }

}
