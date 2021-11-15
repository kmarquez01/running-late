package game.stats;

import java.awt.*;

public class Score {

    public int currentScore;

    public Score() {
        currentScore = 0;
    }

    public void draw(Graphics2D graphic){
        Font font = new Font("Verdana", Font.BOLD, 30);
        graphic.setFont(font);
        graphic.setColor(Color.white);
        graphic.drawString("Score: " + currentScore, 10, 30);
//        graphic.drawString("00:00", 10, 70);
    }
}
