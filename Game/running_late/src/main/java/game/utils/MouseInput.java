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

    if(GameState.gameState == GameState.MENU){

        if(mousex >= 600 && mousex <= 800)
        {
            if(mousey >= 375 && mousey <= 405)
            {
                GameState.gameState = GameState.RESTART;
            }
        }

        if(mousex >= 660 && mousex <= 735)
        {
            if(mousey >= 500 && mousey <= 530)
            {
                GameState.gameState = GameState.EXIT;
            }
        }
    }

    if(GameState.gameState == GameState.PAUSED){

        if(mousex >= 600 && mousex <= 800)
        {
            if(mousey >= 375 && mousey <= 405)
            {
                GameState.gameState = GameState.PLAYING;
            }
        }

        if(mousex >= 660 && mousex <= 790)
        {
            if(mousey >= 500 && mousey <= 530)
            {
                GameState.gameState = GameState.RESTART;
            }
        }

        if(mousex >= 75 && mousex <= 275 )
        {
            if(mousey >= 820 && mousey <= 875)
            {
            
                GameState.gameState = GameState.MENU;
            }
        }
        
    }

    if(GameState.gameState == GameState.GAMEOVER){

        if(mousex >= 1100 && mousex <= 1275)
        {
            if(mousey >= 825 && mousey <= 860)
            {
            
                GameState.gameState = GameState.MENU;
            }
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
