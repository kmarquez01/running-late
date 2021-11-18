package game.stats;

import java.awt.*;

import game.states.GameState;

/**
 * Responsible for displaying the score
 */
public class Score {

    public int currentScore;

    /**
     * Constructor
     */
    public Score() {
        currentScore = 0;
    }

    /**
     * Draws the score on the game screen
     * @param graphic - graphics
     */
    public void draw(Graphics2D graphic){
        if(GameState.gameState != GameState.GAMEOVER){
        Font font = new Font("Verdana", Font.BOLD, 30);
        graphic.setFont(font);
        graphic.setColor(Color.white);
        graphic.drawString("Score: " + currentScore, 10, 30);
        }
        else{
            Color myGreen = new Color(189, 221, 114);
            Font font = new Font("Verdana", Font.BOLD, 30);
            graphic.setFont(font);
            graphic.setColor(Color.white);
            Font fnt = new Font("helvetica", Font.BOLD, 30);
            graphic.drawString("Score: " + currentScore, 10, 30);
            graphic.setFont(fnt);
            graphic.setColor(myGreen);

            graphic.drawString("FINAL SCORE: " + currentScore , 500, 500);
            graphic.drawString("REWARDS: " + currentScore, 500, 600);
            graphic.drawString("ENEMIES HIT: Enough to lose", 500, 700);
        }

    }

    /**
     * @return - the current score
     */
    public int getScoreNum() {
        return currentScore;
    }
}
