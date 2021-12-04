package game.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.states.GameState;

/**
 * Responsible for handling all key inputs and events
 */
public class KeyHandler implements KeyListener {

    public boolean up, down, left, right, esc;

    private void keyChecker(boolean check, int key){
    
    if(GameState.gameState == GameState.PLAYING){
        if (key == KeyEvent.VK_UP) {
            up = check;
        }
        if (key == KeyEvent.VK_DOWN) {
            down = check;
        }
        if (key == KeyEvent.VK_LEFT) {
            left = check;
        }
        if (key == KeyEvent.VK_RIGHT) {
            right = check;
        }
        if(key == KeyEvent.VK_ESCAPE){
            esc = check;
            GameState.gameState = GameState.PAUSED;
        }
       
    }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keyChecker(true, e.getKeyCode());
    }
    @Override
    public void keyPressed(KeyEvent e) {
        keyChecker(true, e.getKeyCode());
    }
    @Override
    public void keyReleased(KeyEvent e) {
        keyChecker(false, e.getKeyCode());
    }

}