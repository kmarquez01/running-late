//import static org.junit.jupiter.api.Assertions;

package states;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.*;

import javax.swing.*;

import java.awt.*;


import game.states.GameState;
import game.states.TitleScreenPanel;
import game.stats.Score;
import game.stats.Stopwatch;
import game.utils.KeyHandler;
import game.utils.MouseInput;

import game.characters.*;


import game.objects.*;

import game.GameScreen;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class GameStateTest
   
{

    
    /**
     * Rigorous Test :-)
     */

     private GameScreen game;

     private Player player;

     private Enemy enemy;

     private Stopwatch timer;

     private Score score;

     private int numb = 2;

     public boolean hurt = false;

     public boolean enemyCollision = false;

     public int x = 100;

     public int y = 100;

     public Point startpoint = new Point(100, 100);

     public Exit exit = new Exit();

     public int endpointx = exit.worldX;

     public int endpointy = exit.worldY;

     public Point endpoint = new Point(endpointx, endpointy);

     private MouseInput mouseInput;

     private KeyHandler input ;

     



     @BeforeEach

     void setup(){

        game = new GameScreen();


        timer = new Stopwatch();

        score = new Score();

        mouseInput = new MouseInput();

        input = new KeyHandler();


        player = new Player(game, input, score, x, y);

        
     }
     
    @Test


    void stateCheck(/*Graphics graphic*/)
    {
        /*
        super.stateCheck(graphic);
        Graphics2D G2D = (Graphics2D)graphic;

        titleScreenPanel.draw(G2D);
        */

        /*

       MouseInput mouseInput = new MouseInput();

       KeyHandler input = new KeyHandler();


       player = new Player(game, input, score, x, y);

       game.collisionHandler.checkEnemy(player);

       */
        
       GameState.gameState = GameState.MENU;

       assertEquals(GameState.gameState, GameState.MENU);
       /*

       assertEquals(score.currentScore, 0);

       System.out.println("MENU state test succeeded");

       assertEquals(timer.sec, 0);

       assertEquals(timer.min, 0);
       */

       GameState.gameState = GameState.PLAYING;

       //assertEquals(player, player.position == new Point(100,100));

       assertEquals(GameState.gameState, GameState.PLAYING);

       /*

       double randomScore = Math.random();

       assertTrue(score.currentScore < randomScore);

       assertEquals(player.enemyCollision, false);

       */
           
       GameState.gameState = GameState.RESTART;

       /*

       assertEquals(timer.sec, 0);

       assertEquals(timer.min, 0);

       assertEquals(player.position, startpoint);

       System.out.println("Restart state test succeeded");

       */

       assertEquals(GameState.gameState, GameState.RESTART);


       GameState.gameState = GameState.EXIT;

      // player.position = new Point(endpointx, endpointy);

       assertEquals(GameState.gameState, GameState.EXIT);

       GameState.gameState = GameState.PAUSED;

       assertEquals(GameState.gameState, GameState.PAUSED);

       GameState.gameState = GameState.GAMEOVER;

       assertEquals(GameState.gameState, GameState.GAMEOVER);

       GameState.gameState = GameState.GAMEWIN;

       assertEquals(GameState.gameState, GameState.GAMEWIN);



    }


    @Test 

    void stateWinCheck(){

       player.position = new Point(912, 864);

       GameState.gameState = GameState.GAMEWIN;

       assertEquals(GameState.gameState, GameState.GAMEWIN);

       assertEquals(player.position, endpoint);
    }

    @Test 

    void stateLoseCheck(){

        player.enemyCollision = true;

        GameState.gameState = GameState.GAMEOVER;

       assertEquals(GameState.gameState, GameState.GAMEOVER);

       assertEquals(player.enemyCollision, true);


    }

    @Test
    
    void statePlayingCheck(){


        game.collisionHandler.checkEnemy(player);

        GameState.gameState = GameState.PLAYING;

       //assertEquals(player, player.position == new Point(100,100));

       assertEquals(GameState.gameState, GameState.PLAYING);

       double randomScore = Math.random();

       assertTrue(score.currentScore < randomScore);

       assertEquals(player.enemyCollision, false);


    }

    @Test

    void statePausedCheck(){
        GameState.gameState = GameState.PAUSED;

        assertEquals(GameState.gameState, GameState.PAUSED);

        assertTrue(score.currentScore == score.currentScore);

        assertEquals(player.enemyCollision, false);
    }

    @Test 
    
    void stateMenuCheck(){

        GameState.gameState = GameState.MENU;

        assertEquals(score.currentScore, 0);

        System.out.println("MENU state test succeeded");
 
        assertEquals(timer.sec, 0);
 
        assertEquals(timer.min, 0);

        assertEquals(GameState.gameState, GameState.MENU);
    }

    @Test
    
    void stateRestartCheck(){

        GameState.gameState = GameState.RESTART;

        assertEquals(timer.sec, 0);
 
        assertEquals(timer.min, 0);
 
        assertEquals(player.position, startpoint);
 
        System.out.println("Restart state test succeeded");

        assertEquals(GameState.gameState, GameState.RESTART);
        
    }

    @Test

    void stateExitCheck(){
        GameState.gameState = GameState.EXIT;

        assertEquals(GameState.gameState, GameState.EXIT);

    }



    
    
  


}
