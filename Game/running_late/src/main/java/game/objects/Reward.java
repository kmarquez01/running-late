package game.objects;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The class for rewards
 */
public class Reward extends Object {

    public Reward(){

        name = "Coin";
        try {
            image = ImageIO.read(getClass().getResource("/rewards/assignment_paper.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        collision = true;
    }
}
