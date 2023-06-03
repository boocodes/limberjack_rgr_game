import javax.swing.*;
import java.awt.*;

public class Main {


    private static final int HEIGHT = 600;
    private static final int WIDTH = 944;






    public static void main(String[] args){
        JFrame frame = new JFrame("Limberjack");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(0, 0);
        frame.setSize(new Dimension(HEIGHT, WIDTH));
        frame.add(new App(frame));
        frame.setVisible(true);



    }





}




