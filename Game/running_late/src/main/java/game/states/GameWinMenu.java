package game.states;

import game.GameScreen;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The Victory Screen
 */
public class GameWinMenu {

    Color myGreen = new Color(189, 221, 114);
    Image img = null;

    public GameWinMenu(GameScreen board){
        getImage();
        return;
    }


    /**
     * Draws the victory screen onto the game screen
     * @param g - graphics
     */
    public void draw(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(img, 425, 150, 550, 100, null );

        Font fnt = new Font("helvetica", Font.BOLD, 30);
        g.setFont(fnt);
        g.setColor(myGreen);
        g.drawString("Congratulations, you did the impossible!", 450, 500);
        g.drawString("MAIN MENU", 1100, 850);
//        g.drawString("NEW GAME", 640, 400);
//        g.drawString("MAIN MENU", 640, 525);


    }

    /**
     * Gets the Victory title
     */
    public void getImage(){

        try {
            img = ImageIO.read(getClass().getResource("/win.png"));
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
