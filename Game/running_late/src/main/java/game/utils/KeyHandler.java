package game.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.states.GameState;

public class KeyHandler implements KeyListener {

    public boolean up, down, left, right;

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