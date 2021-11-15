package game.characters;

import game.GameScreen;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Enemy {
    public BufferedImage image;
    public boolean collision = false;
    public int points;

//    public String name;
    public int x;
    public int y;

    //constructor
    public Enemy(){
//        name = "movingEnemy";
        points = (-10); //change #
        try {
            //update image later
            image = ImageIO.read((new FileInputStream("resources/rewards/redstar.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //draw the image on screen
    public void draw(Graphics2D G2D, GameScreen screen){
        G2D.drawImage(image, x, y, screen.tileSize, screen.tileSize, null);
    }

    //only appears at random times - do in EnemySetter



    //detect collision w/ player & **kill player**
    //death fxn in gameScreen?




    //if no collision, disappears after certain amt of time & replaced with tile:



}
