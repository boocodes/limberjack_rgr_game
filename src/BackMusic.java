import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BackMusic {

    private final static String soundPath = "./source/music.wav";

    private boolean playFlag = true;

    private static Clip clip;


    private static BackMusic classOne;
    public static synchronized BackMusic getInstance(){
        if(classOne == null){
            classOne = new BackMusic();
        }
        return classOne;
    }



    public void setPlayFlag(boolean flag){
        playFlag = flag;
    }

    public void stop(){
        clip.stop();
    }

    public void play(){
        try{
            File soundFile = new File(soundPath);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.setFramePosition(0); //устанавливаем указатель на старт


            //Если не запущено других потоков, то стоит подождать, пока клип не закончится
            //В GUI-приложениях следующие 3 строчки не понадобятся

                clip.start(); //Поехали!!!
                Thread.sleep(clip.getMicrosecondLength()/1000);
                clip.stop(); //Останавливаем
                clip.close(); //Закрываем



        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
