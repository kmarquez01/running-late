package game.objects;

import game.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Wall {

    private GameScreen screen;
    private BufferedImage sprite;

    // the character's position
    public Point position;

    public Wall(GameScreen screen, int xPosition, int yPosition) {
        this.screen = screen;
        position = new Point(xPosition, yPosition);
        setWallImage();
    }

    public void setWallImage(){
        try {
            sprite = ImageIO.read( (new FileInputStream("resources/tiles/wall.png") ) );
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D G2D){
        G2D.drawImage(sprite,position.x, position.y, screen.tileSize, screen.tileSize, null);
    }


}