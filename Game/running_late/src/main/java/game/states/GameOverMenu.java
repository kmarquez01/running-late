package game.states;

import game.GameScreen;
import game.stats.Score;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class GameOverMenu {

    Color myGreen = new Color(189, 221, 114);
    Image img = null;


    public GameOverMenu(GameScreen board){
        getImage();
//        public Score score = new Score();
//        // Draws the score
//        score.draw(G2D);
        return;
    }

    String title = "Running Late";

    String developers = "Kirby, Johann, Adrian, Monica";

    int buttonWidth = 200;

    int spacing = 90;

    public Score currentScore = new Score();
    

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

    public void getImage(){

        try {
            img = ImageIO.read(new FileInputStream("resources/gameover.png"));
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
