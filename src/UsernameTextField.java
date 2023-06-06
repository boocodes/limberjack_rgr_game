import javax.swing.*;
import java.awt.*;

public class UsernameTextField{


    private static String username = "";

    public static void setUsername(String sumbol){
        username += sumbol;
        username = username.trim();
    }


    public static String  getUsername(){
        return username;
    }

    public static void removeLastChar(){
        username = username.substring(0, username.length() - 1);
    }

   public UsernameTextField(){

   }

    public  void addAskUsername(Graphics2D g){
        g.setColor(Color.white);
        g.fillRect(170, 50, 300, 50);

        if(username.length() == 0){
            g.setColor(Color.BLACK);
            g.drawString("Введите имя",210, 80);
        }
        else{
            g.setColor(Color.BLACK);
            g.drawString(username,210, 80);
        }

    }
}
