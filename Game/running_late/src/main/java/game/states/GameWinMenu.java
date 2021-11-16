package game.states;

import game.GameScreen;
import game.stats.Score;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class GameWinMenu {

    Color myGreen = new Color(189, 221, 114);
    Image img = null;

    public GameWinMenu(GameScreen board){
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

        Font fnt = new Font("helvetica", Font.BOLD, 30);
        g.setFont(fnt);
        g.setColor(myGreen);
        g.drawString("you won! (make new label)", 150, 500);
//        g.drawString("NEW GAME", 640, 400);
//        g.drawString("MAIN MENU", 640, 525);


    }

    public void getImage(){

        try {
            img = ImageIO.read(new FileInputStream("resources/paused.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
