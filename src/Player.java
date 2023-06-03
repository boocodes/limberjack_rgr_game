import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Player{

    private int health;
    private String horizont_position = "right";

    private Graphics2D g;

    public static enum STATES {leftPosition, rightPosition}

    public static String imageLink = "limberjackStayRight.png";

    Image img;


    public void setHorizont_position(String new_horizont_position){
        horizont_position = new_horizont_position;
    }


    public void setImageLink(String newImageLink){
        imageLink = newImageLink;
    }


    public void draw (Graphics2D g){

        // if left pos x coord = 70
        // if right pos x coord = 400
        if(horizont_position == "left"){
            img  = new ImageIcon("source/" + imageLink).getImage();
            g.drawImage(img, (int)70, (int)400, null);
        }
        else{
            img  = new ImageIcon("source/" + imageLink).getImage();
            g.drawImage(img, (int)400, (int)400, null);
        }

    }

}
