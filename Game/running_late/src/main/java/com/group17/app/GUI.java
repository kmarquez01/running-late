package com.group17.app;

import java.awt.Canvas;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.BorderFactory;

import java.awt.GridLayout;



public class GUI extends Canvas {

    public GUI(){

        
        JFrame frame = new JFrame();

        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(400, 400, 240, 240));
        panel.setLayout(new GridLayout());

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Running Late");
    }

   
}
