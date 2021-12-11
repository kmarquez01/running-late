package game.states;

import java.awt.*;
import java.io.*;

import game.GameScreen;

import javax.imageio.ImageIO;

/**
 * The menu screen
 */
public class TitleScreenPanel{

    Color myGreen = new Color(189, 221, 114);
    Image img = null;

    public TitleScreenPanel(GameScreen board){
        getImage();
        return;
    }

    /**
     * Draws the title screen onto the game screen
     * @param g - graphics
     */
    public void draw(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(img, 425, 150, 550, 100, null );

        Font fnt = new Font("helvetica", Font.BOLD, 30);
        g.setFont(fnt);
        g.setColor(myGreen);
        g.drawString("START GAME", 600, 400); 
        g.drawString("EXIT", 665, 525); 


    }

    /**
     * Gets the Title Image
     */
    public void getImage(){

        try {
            img = ImageIO.read(getClass().getResource("/title.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}