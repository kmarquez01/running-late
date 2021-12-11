package game.objects;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The class for punishments
 */
public class Punishment extends Object {

    public Punishment(){

        name = "Spike";
        hurtful = true;
        try {
            image = ImageIO.read(getClass().getResource("/punishments/StatEn_idle.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        collision = true;
    }
}
