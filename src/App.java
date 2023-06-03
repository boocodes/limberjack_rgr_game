import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class App extends JPanel implements ActionListener {



    private Graphics2D g;
    private BufferedImage image;



    private int score = 0;

    private static int WIDTH = 600;
    private static int HEIGHT = 944;


    private static int mouseX;
    private static int mouseY;


    public static enum STATES {MENU, PLAY}
    public static STATES state = STATES.PLAY;



    Timer timer = new Timer(30, this);


    Back back = new Back();
    Land land = new Land();

    Player player = new Player();

    Wood wood = new Wood();


    JFrame externalFrame;

    public App(JFrame frame){
        super();
        setFocusable(true);
        requestFocus();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        externalFrame = frame;

        MyKeyBoardHandler myKeyBoardHandler = new MyKeyBoardHandler();
        this.addKeyListener(myKeyBoardHandler);



        timer.start();
    }


    public void actionPerformed(ActionEvent e) {


        if(state.equals(STATES.MENU)){
            back.setFrame(externalFrame);
            back.draw(g);
            gameDraw();

        }
        else{
            gameRender();
            gameDraw();
        }


    }




    public void gameRender(){

        back.draw(g);
        land.draw(g);
        player.draw(g);
        wood.setStartWoodsPositionsElem();
        wood.draw(g);

        if(score == 0){

        }

    }

    public void gameDraw(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();

    }


    private class MyKeyBoardHandler implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent event){
            if(state.equals(STATES.PLAY)){
                if(event.getKeyCode() == 37){

                    player.setHorizont_position("left");
                    player.setImageLink("limberjackCutLeft.png");
                    player.draw(g);

                    int delay = 100;
                    ActionListener listener = new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            player.setImageLink("limberjackStayLeft.png");
                            player.draw(g);
                        }
                    };
                    Timer timer = new Timer(delay, listener);
                    timer.setRepeats(false);
                    timer.start();
                    boolean cutResult = wood.removeLastWoodsElemAndAddNew("leftPlant.png");
                    if(cutResult){
                        wood.draw(g);
                        state = STATES.PLAY;
                    }
                    else{
                        state = STATES.MENU;
                    }

                }
                else if(event.getKeyCode() == 39){

                    player.setHorizont_position("right");
                    player.setImageLink("limberjackCutRight.png");

                    player.draw(g);

                    int delay = 100;
                    ActionListener listener = new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            player.setImageLink("limberjackStayRight.png");
                            player.draw(g);
                        }
                    };
                    Timer timer = new Timer(delay, listener);
                    timer.setRepeats(false);
                    timer.start();
                    boolean cutResult = wood.removeLastWoodsElemAndAddNew("rightPlant.png");
                    if(cutResult){
                        wood.draw(g);
                        state = STATES.PLAY;
                    }
                    else{
                        state = STATES.MENU;
                    }

                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }


}
