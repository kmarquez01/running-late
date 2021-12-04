package game.characters;

import game.GameScreen;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    GameScreen gameScreen= new GameScreen();
    Player player;

    public void setUp() {
        this.player = gameScreen.player;
    }

    @Test
    public void characterMovementTest() {
        setUp();

        Point pt = player.position;
        Point testPt = new Point(100, 100);
        assertEquals(testPt, pt);

        player.moveDirection("up");
        pt = player.position;
        assertNotEquals(testPt, pt);

        testPt.y += player.speed;
        player.moveDirection("down");
        pt = player.position;
        assertNotEquals(testPt, pt);

        testPt.y -= player.speed;
        player.moveDirection("left");
        pt = player.position;
        assertNotEquals(testPt, pt);

        testPt.x -= player.speed;
        player.moveDirection("right");
        pt = player.position;
        assertNotEquals(testPt, pt);

    }

    @Test
    public void wallCollisionTest() {

        setUp();
        player.position = new Point (0,0);

        Point pt = player.position;
        Point testPt = new Point(0, 0);

        player.moveDirection("up");
        pt = player.position;
        assertEquals(testPt, pt);

        player.moveDirection("down");
        pt = player.position;
        assertEquals(testPt, pt);

        player.moveDirection("left");
        pt = player.position;
        assertEquals(testPt, pt);

        player.moveDirection("right");
        pt = player.position;
        assertEquals(testPt, pt);

    }


}

