package game.utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.states.GameState;
import game.sound.Sound;


/**
 * Responsible for handling all mouse inputs and events
 */
public class MouseInput implements MouseListener{
    Sound effects; // = new Sound(); // for playing sound effects when buttons pressed

    private void mouseChecker(boolean check, MouseEvent e){
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
                effects.setFile(5);
                effects.play();
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

    if(GameState.gameState == GameState.GAMEWIN){  

        if(mousex >= 1100 && mousex <= 1275)
        {
            if(mousey >= 825 && mousey <= 860)
            {
            
                GameState.gameState = GameState.MENU;
            }
        }
        
        if(mousex >= 125 && mousex <= 275 )
        {
            if(mousey >= 820 && mousey <= 875)
            {
            
                GameState.gameState = GameState.RESTART;
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

            if(mousex >= 125 && mousex <= 275 )
            {
                if(mousey >= 820 && mousey <= 875)
                {

                    GameState.gameState = GameState.RESTART;
                }
            }

         }
    }


   
    @Override
    public void mouseClicked(MouseEvent e) {

        effects = new Sound();
    }

    @Override
    public void mousePressed(MouseEvent e) {
            mouseChecker(true, e);

        }
  

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseChecker(false, e);
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseChecker(true, e);
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseChecker(false, e);
        
    }
    
}
