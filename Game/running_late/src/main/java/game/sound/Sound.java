package game.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {

    Clip clip;
    File soundURL[] = new File[9];

    public Sound(){

        soundURL[0] = new File("resources/sounds/MainMenu.wav");
        soundURL[1] = new File("resources/sounds/InGame.wav");
        soundURL[2] = new File("resources/sounds/Reward.wav");
        soundURL[3] = new File("resources/sounds/Hurt.wav");
        soundURL[4] = new File("resources/sounds/PauseGame.wav");
        soundURL[5] = new File("resources/sounds/UnpauseGame.wav");
        soundURL[6] = new File("resources/sounds/Loose.wav"); //not in use
        soundURL[7] = new File("resources/sounds/GameOver.wav");
        soundURL[8] = new File("resources/sounds/GameWin.wav");
    }

    public void setFile(int index){

        try{
            clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);
            clip.open(ais);

        }catch(Exception e){
            //handle exception
        }
    }

    public void play(){

        clip.start();
    }

    public void loop(){

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){

        clip.stop();
    }
}