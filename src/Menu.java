import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {


    JFrame externalFrame;

    ScoreTable scoreTable = new ScoreTable();

    private final static int startButtonXCoord = 250;

    private final static int startButtonYCoord = 450;


    public static int getStartButtonXCoord(){
        return startButtonXCoord;
    }

    public static int getStartButtonYCoord(){
        return startButtonYCoord;
    }

    static Image startButton;


    App appFrame;

    public static Image getStartButton(){
        return startButton;
    }

    public Menu(){

    }

    public Menu(Graphics2D g, App frame){
        super();

        startButton = new ImageIcon("source/startButton.png").getImage();
        Font newFont = new Font("Arial", Font.BOLD, 30);
        

        g.setColor(Color.CYAN);

        g.fillRect(0, 0, 600, 944);
        g.drawImage(startButton, startButtonXCoord, startButtonYCoord, null);


        g.setFont(newFont);
        g.setColor(Color.BLACK);


        if(App.getScore() > 0){
            g.drawString("Вы проиграли! Ваш счет: " + App.getScore(), 100, 200);
            g.drawString("Начать сначала", 200, 400);

        }
        else{
            g.drawString("Начать играть", 200, 400);
        }

        Font scoreTableFont = new Font("Arial", Font.BOLD, 19);
        g.setFont(scoreTableFont);


        UsernameTextField usernameTextField = new UsernameTextField();
        usernameTextField.addAskUsername(g);


        scoreTable.showBestPlayers(g);

    }




}
