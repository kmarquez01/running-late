package game.characters;

import game.GameScreen;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Punishment {
    public BufferedImage image;
    public boolean collision = false;
    public int points;

    public int x;
    public int y;


    //constructor
    public Punishment(){
        points = (-5);
        try {
            //change image name
            image = ImageIO.read((new FileInputStream("resources/punishments/bluestar.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    //draw punishments on screen
    public void draw(Graphics2D G2D, GameScreen screen){
        G2D.drawImage(image, x, y, screen.tileSize, screen.tileSize, null);
    }

    //detect collision with player: (also add points to player's score)
    //TODO



    //disappears & replaced with tile:
    //TODO

}
