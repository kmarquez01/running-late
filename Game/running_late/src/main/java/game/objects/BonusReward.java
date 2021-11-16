package game.objects;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class BonusReward extends Object {

    public BonusReward(){

        name = "Gold";
        try {
            image = ImageIO.read( (new FileInputStream("resources/rewards/test_answer.png") ) );
        }
        catch(IOException e){
            e.printStackTrace();
        }

        collision = true;
    }
}
