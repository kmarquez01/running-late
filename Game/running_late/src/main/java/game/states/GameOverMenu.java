package game.states;

import game.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The Game Over Screen
 */
public class GameOverMenu {

    Color myGreen = new Color(189, 221, 114);
    Image img = null;


    public GameOverMenu(GameScreen board){
        getImage();
        return;
    }


    /**
     * Draws the game over screen onto the game screen
     * @param g - graphics
     */
    public void draw(Graphics g){


        Graphics2D g2d = (Graphics2D) g;
        Font fnt = new Font("helvetica", Font.BOLD, 30);
        g.setFont(fnt);
        g2d.drawImage(img, 425, 150, 550, 100, null );
        g.setColor(myGreen);

//      g.drawString("NEW GAME", 640, 400);
        g.drawString("MAIN MENU", 1100, 850);
        g.drawString("NEW GAME", 125, 850);


    }

    /**
     * Gets the Game Over Image
     */
    public void getImage(){

        try {
            img = ImageIO.read(getClass().getResource("/gameover.png"));
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
