package game.stats;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import game.states.GameState;

import java.text.DecimalFormat;


public class Stopwatch {

    Timer timer;
    String timeLabel;
    int sec;
    int min;
    String strSec;
    String strMin;
    DecimalFormat dFormat = new DecimalFormat("00");


    public Stopwatch() {
        sec = 0;
        min = 0;
        stopwatch();
        String timeLabel = "00:00";
        timer.stop();
    }

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

    public void stopTimer(){
        timer.stop();
    }

    public void startTimer(){
        timer.start();
    }

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
}

