import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class App extends JPanel implements ActionListener {



    private Graphics2D g;
    private BufferedImage image;




    private static int WIDTH = 600;
    private static int HEIGHT = 944;


    private static int mouseX;
    private static int mouseY;


    public static boolean pausedFlag = false;

    public static enum STATES {MENU, PLAY}
    public static STATES state = STATES.MENU;



    private static int gameSecDuration = 0;

    public static void setGameSecDuration(int value){
        gameSecDuration = value;
    }

    public static int getGameSecDuration(){
        return gameSecDuration;
    }


    private static String username = "";

    public static void setUsername(String newUsername){
        username = newUsername;
    }

    public static String getUsername(){
        return username;
    }


    Timer timer = new Timer(30, this);


    Back back = new Back();
    Land land = new Land();
    ScoreTable scoreTable = new ScoreTable();
    TimerScore timerScore = new TimerScore();

    Player player = new Player();

    Wood wood = new Wood();

    Score appScoreElem = new Score();

    Demon demon = new Demon();

    BackMusic music = new BackMusic();

    Secondomer secondomer = new Secondomer();

    Menu menu = new Menu();

    SmoothCounterForTimerScoreBar smoothCounterForTimerScoreBar = new SmoothCounterForTimerScoreBar();


    private static int score = 0;

    public static void setScore(int newScore){
        score = newScore;
    }


    public static int getScore(){
        return score;
    }


    Demon backmusicDeamon = new Demon();
    Demon timerSecDeamon = new Demon();

    Demon smoothCounterForTimerBarScoreDeamon = new Demon();




    JFrame externalFrame;

    public App(JFrame frame) throws InterruptedException {
        super();
        setFocusable(true);
        requestFocus();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        gameSecDuration = 0;
        externalFrame = frame;

        MyKeyBoardHandler myKeyBoardHandler = new MyKeyBoardHandler();
        this.addKeyListener(myKeyBoardHandler);
        MyMouseHandler myMouseHandler = new MyMouseHandler();
        this.addMouseListener(myMouseHandler);



        backmusicDeamon.setTask(()->{
            music.play();
        });

        backmusicDeamon.setDaemon(true);

        backmusicDeamon.start();


        timerSecDeamon.setTask(()->{
            secondomer.start();
        });
        timerSecDeamon.setDaemon(true);

        timerSecDeamon.start();




        smoothCounterForTimerBarScoreDeamon.setTask(()->{
            smoothCounterForTimerScoreBar.start();
        });
        smoothCounterForTimerBarScoreDeamon.setDaemon(true);

        smoothCounterForTimerBarScoreDeamon.start();


        timer.start();
    }


    public void actionPerformed(ActionEvent e) {


        if(state.equals(STATES.MENU)){
            back.setFrame(externalFrame);
            back.draw(g, this);
            gameDraw();


            backmusicDeamon = null;


            timerSecDeamon = null;


            smoothCounterForTimerBarScoreDeamon = null;

            secondomer.stop();
            smoothCounterForTimerScoreBar.stop();
            music.stop();


        }
        else{
            gameRender();
            timerScore.draw(g, smoothCounterForTimerScoreBar.getValue());
            appScoreElem.draw(g, this);

            gameDraw();

        }


    }




    public void gameRender(){

        back.draw(g, this);
        land.draw(g);
        player.draw(g);
        wood.setStartWoodsPositionsElem();
        wood.draw(g);

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
            System.out.println(event);
            if(state.equals(STATES.MENU)){
                if(
                        event.getKeyCode() == 18 ||
                        event.getKeyCode() == 16 ||
                        event.getKeyCode() == 17 ||
                        event.getKeyCode() == 0 ||
                        event.getKeyCode() == 27 ||
                        event.getKeyCode() == 20 ||
                        event.getKeyCode() == 37 ||
                        event.getKeyCode() == 38 ||
                        event.getKeyCode() == 39 ||
                        event.getKeyCode() == 40
                ){
                    return;
                }

            }
            if(state.equals(STATES.MENU) && event.getKeyCode() == 8 && UsernameTextField.getUsername().length() > 0){
                UsernameTextField.removeLastChar();
            }

            if(state.equals(STATES.MENU) && event.getKeyCode() != 8){
                if(UsernameTextField.getUsername().length() >= 10) return;
                UsernameTextField.setUsername(String.valueOf(event.getKeyChar()));
            }


            if(state.equals(STATES.PLAY)){
                if(event.getKeyCode() == 81){
                    if(pausedFlag){
                        pausedFlag = false;
                        backmusicDeamon = new Demon();
                        backmusicDeamon.setTask(()->{
                            music.play();
                        });
                        backmusicDeamon.setDaemon(true);
                        backmusicDeamon.start();


                        timerSecDeamon = new Demon();
                        timerSecDeamon.setTask(()->{
                            secondomer.start();
                        });
                        timerSecDeamon.setDaemon(true);
                        timerSecDeamon.start();

                        smoothCounterForTimerBarScoreDeamon = new Demon();
                        smoothCounterForTimerBarScoreDeamon.setTask(()->{
                            smoothCounterForTimerScoreBar.start();
                        });
                        smoothCounterForTimerBarScoreDeamon.setDaemon(true);
                        smoothCounterForTimerBarScoreDeamon.start();
                    }
                    else{
                        pausedFlag = true;
                        backmusicDeamon = null;


                        timerSecDeamon = null;


                        smoothCounterForTimerBarScoreDeamon = null;

                        secondomer.stop();
                        smoothCounterForTimerScoreBar.stop();
                        music.stop();
                    }
                }
                if(event.getKeyCode() == 37){
                    System.out.println(gameSecDuration);
                    if(pausedFlag) return;
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
                        setScore(score+1);
                        smoothCounterForTimerScoreBar.addValueAfterSuccessCut(25);
                    }
                    else{
                        state = STATES.MENU;
                        scoreTable.saveSession(getUsername(), score);
                        System.out.println(score);
                    }

                }
                else if(event.getKeyCode() == 39){
                    System.out.println(gameSecDuration);
                    if(pausedFlag) return;
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
                        setScore(score+1);
                        smoothCounterForTimerScoreBar.addValueAfterSuccessCut(25);
                    }
                    else{
                        state = STATES.MENU;
                        scoreTable.saveSession(getUsername(), score);
                        System.out.println(score);
                    }

                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }


    private class MyMouseHandler implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(
                    e.getX() > Menu.getStartButtonXCoord() &&
                    e.getX() < Menu.getStartButtonXCoord() + Menu.getStartButton().getWidth(menu) &&
                    e.getY() > Menu.getStartButtonYCoord() &&
                    e.getY() < Menu.getStartButtonYCoord() + Menu.getStartButton().getHeight(menu) &&
                    App.state == STATES.MENU &&
                    UsernameTextField.getUsername().length() > 0
            ){
                setUsername(UsernameTextField.getUsername());
                secondomer.setTime(0);
                smoothCounterForTimerScoreBar.setValue(400);

                score = 0;
                backmusicDeamon = new Demon();
                backmusicDeamon.setTask(()->{
                    music.play();
                });
                backmusicDeamon.setDaemon(true);
                backmusicDeamon.start();


                timerSecDeamon = new Demon();
                timerSecDeamon.setTask(()->{
                    secondomer.start();
                });
                timerSecDeamon.setDaemon(true);
                timerSecDeamon.start();

                smoothCounterForTimerBarScoreDeamon = new Demon();
                smoothCounterForTimerBarScoreDeamon.setTask(()->{
                    smoothCounterForTimerScoreBar.start();
                });
                smoothCounterForTimerBarScoreDeamon.setDaemon(true);
                smoothCounterForTimerBarScoreDeamon.start();

                App.state = STATES.PLAY;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }


}
