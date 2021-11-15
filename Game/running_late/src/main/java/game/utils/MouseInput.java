package game.utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.states.GameState;

public class MouseInput implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int mousex = e.getX();
        int mousey = e.getY();

        if(mousex >= 600 && mousex <= 800)
        {
            if(mousey >= 375 && mousey <= 405)
            {
                GameState.gameState = GameState.PLAYING;
            }
        }

        if(mousex >= 660 && mousex <= 735)
        {
            if(mousey >= 500 && mousey <= 530)
            {
                GameState.gameState = GameState.EXIT;
            }
        }

        /*
        public Rectangle playB = new Rectangle(600, 375, 200, 30);
        public Rectangle exitB = new Rectangle(660, 500, 75, 30);
        g.drawString("START GAME", 600, 400); 
        g.drawString("EXIT", 665, 525);
        // TODO Auto-generated method stub
        */
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
