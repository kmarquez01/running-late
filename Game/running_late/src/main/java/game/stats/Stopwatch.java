package game.stats;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import game.states.GameState;

import java.text.DecimalFormat;

/**
 * Responsible for displaying the timer at the top of the screen
 */
public class Stopwatch {

    Timer timer;
    String timeLabel;
    public int sec;
    public int min;
    String strSec;
    String strMin;
    DecimalFormat dFormat = new DecimalFormat("00");

    /**
     * Stopwatch constructor
     */
    public Stopwatch() {
        sec = 0;
        min = 0;
        stopwatch();
        String timeLabel = "00:00";
        timer.stop();
    }

    /**
     * Draws the stopwatch on the screen
     * @param graphic - graphics
     */
    public void draw(Graphics2D graphic){

        if(GameState.gameState != GameState.GAMEOVER){
            Font font = new Font("Verdana", Font.BOLD, 30);
            graphic.setFont(font);
            graphic.setColor(Color.white);
            if (timeLabel != null) {
                graphic.drawString("Time: " + timeLabel, 10, 70);
            } else {
                graphic.drawString("Time: 00:00", 10, 70);
            }
        }
        else{
            Color myGreen = new Color(189, 221, 114);
            Font font = new Font("Verdana", Font.BOLD, 30);
            graphic.setFont(font);
            graphic.setColor(Color.white);
            graphic.drawString("Time: " + timeLabel, 10, 70);
            Font fnt = new Font("helvetica", Font.BOLD, 30);
            graphic.setFont(fnt);
            graphic.setColor(myGreen);
            graphic.drawString("TIME: " + timeLabel, 500, 400);
        }

    }

    /**
     * Stops the timer
     */
    public void stopTimer(){
        timer.stop();
    }

    /**
     * Starts the timer
     */
    public void startTimer(){
        timer.start();
    }

    /**
     * Increments the stop watch every second
     */
    public void stopwatch() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sec++;
                strSec = dFormat.format(sec);
                strMin = dFormat.format(min);
                timeLabel = (strMin + ":" + strSec);
                if (sec == 60) {
                    sec = 0;
                    min++;
                    strSec = dFormat.format(sec);
                    strMin = dFormat.format(min);
                    timeLabel = (strMin + ":" + strSec);
                }
            }
        }
        );
    }

    /**
     * Resets the stop watch (for every new game)
     */
    public void resetClock(){
        sec = 0;
        min = 0;
        timeLabel = "00:00";
    }
}

