package game.states;

import java.awt.*;
import java.io.*;

import game.GameScreen;

import javax.imageio.ImageIO;




public class GamePauseMenu{

    Color myGreen = new Color(189, 221, 114);
    Image img = null;
    Image img1 = null;

    public GamePauseMenu(GameScreen board){
        getImage();
        return;
    }

    String title = "Running Late";

    String developers = "Kirby, Johann, Adrian, Monica";

    int buttonWidth = 200;

    int spacing = 90;

   

    public void draw(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(img, 425, 150, 550, 100, null );
        g2d.drawImage(img1, 75, 820, 50, 50, null );

        Font fnt = new Font("helvetica", Font.BOLD, 30);
        g.setFont(fnt);
        g.setColor(myGreen);
        g.drawString(" RESUME", 640, 400);
        g.drawString("RESTART", 640, 525);
        g.drawString("BACK", 175, 850);


    }

    public void getImage(){

        try {
            img = ImageIO.read(new FileInputStream("resources/paused.png"));
            img1 = ImageIO.read(new FileInputStream("resources/backbutton.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}