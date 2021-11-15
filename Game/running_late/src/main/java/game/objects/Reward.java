package game.objects;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Reward extends Object {

    public Reward(){

        name = "Coin";
        try {
            image = ImageIO.read( (new FileInputStream("resources/rewards/star.png") ) );
        }
        catch(IOException e){
            e.printStackTrace();
        }

        collision = true;
    }
}
