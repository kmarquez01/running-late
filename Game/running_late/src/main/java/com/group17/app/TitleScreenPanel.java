package com.group17.app;

import java.awt.*;

public class TitleScreenPanel extends Canvas{

    String title = "Running Late";

    String developers = "Kirby, Johann, Adrian, Monica";

    int buttonWidth = 200;

    int spacing = 90;

    public Rectangle playB = new Rectangle(200, 150, 100, 50);
    public Rectangle exitB = new Rectangle(200, 250, 100, 50);

    public void render(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        Font fnt = new Font("helvetica", Font.BOLD, 50);
        g.setFont(fnt);
        g.setColor(Color.black);
        g.drawString("RUNNING LATE", 200, 100);

        g2d.draw(playB);
        g2d.draw(exitB);
    }


}