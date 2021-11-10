package com.group17.app;

import java.awt.Canvas;

import javax.swing.JFrame;

public class GUI extends Canvas {

    public GUI(int width, int height, String title){
        
        JFrame frame = new JFrame(title);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
   
}
