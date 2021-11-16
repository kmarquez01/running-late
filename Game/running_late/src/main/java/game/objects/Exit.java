package game.objects;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Exit extends Object{

    public Exit(){
        name = "Exit";
        worldX = 19*48; //19*gs.tileSize
        worldY = 18*48; //18*gs.tileSize
        isExit = true;
        try {
            image = ImageIO.read( (new FileInputStream("resources/exit2.png") ) );
        }
        catch(IOException e){
            e.printStackTrace();
        }

        collision = true;
    }

}
