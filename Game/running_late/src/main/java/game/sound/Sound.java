package game.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

/**
 * Responsible for all sound in the game
 */
public class Sound {

    public Clip clip;
    URL soundURL[] = new URL[9];

    /**
     * Stores all the different sounds and music
     */
    public Sound(){
        soundURL[0] = getClass().getResource("/sounds/MainMenu.wav");
        soundURL[1] = getClass().getResource("/sounds/InGame.wav");
        soundURL[2] = getClass().getResource("/sounds/Reward.wav");
        soundURL[3] = getClass().getResource("/sounds/Hurt.wav");
        soundURL[4] = getClass().getResource("/sounds/PauseGame.wav");
        soundURL[5] = getClass().getResource("/sounds/UnpauseGame.wav");
        soundURL[6] = getClass().getResource("/sounds/Loose.wav"); //not in use
        soundURL[7] = getClass().getResource("/sounds/GameOver.wav");
        soundURL[8] = getClass().getResource("/sounds/GameWin.wav");

    }
    /**
     * Gets the audio files
     * @param index - which audio file to get
     */
    public void setFile(int index){
        try{
            clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);
            clip.open(ais);

        } catch(Exception e){
            //handle exception
        }
    }

    /**
     * Plays the sound and music
     */
    public void play(){
        clip.start();
    }

    /**
     * Loops the sound and music
     */
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops playing the sound and music
     */
    public void stop(){
        clip.stop();
    }
}