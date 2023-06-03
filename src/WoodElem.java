import javax.swing.*;
import java.awt.*;




public class WoodElem {

    private String imageLink;
    private String woodType;
    private int position;

    public WoodElem (String imageLink){
        this.imageLink = imageLink;
    }
    public void setPosition(int position){
        this.position = position;
    }
    public void setWoodType(String woodType){
        this.woodType = woodType;
    }

    Image img;



    public void draw (Graphics2D g){
        img = new ImageIcon("source/" + imageLink).getImage();
        if(woodType == "startTree.png"){
            g.drawImage(img, (int)265, (int)460, null);
        }
        else if(woodType == "simpleWoodElem.png"){
            g.drawImage(img, (int)280, (int)470 - (position * 145), null);
        }
        else if(woodType == "leftPlant.png"){
            g.drawImage(img, (int)115, (int)460 - (position * 145), null);
        }
        else if(woodType == "rightPlant.png"){
            g.drawImage(img, (int)281, (int)460 - (position * 145), null);
        }

    }


}
