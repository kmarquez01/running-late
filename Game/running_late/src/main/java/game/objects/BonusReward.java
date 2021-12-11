package game.objects;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The class for bonus rewards
 */
public class BonusReward extends Object {

    public BonusReward(){

        name = "Gold";
        try {
            image = ImageIO.read(getClass().getResource("/rewards/BonusReward.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        collision = true;
    }
}
