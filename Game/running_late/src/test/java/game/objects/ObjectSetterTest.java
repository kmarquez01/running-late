package game.objects;

import game.GameScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ObjectSetterTest {

    GameScreen gs;
    //tileSize = 48 (just a reminder)

    @BeforeEach
    void setUp() {
        this.gs = new GameScreen();

        gs.obj[0] = new Reward();
        gs.obj[0].worldX = 1 * gs.tileSize;
        gs.obj[0].worldY = 4 * gs.tileSize;

        gs.obj[1] = new Punishment();
        gs.obj[1].worldX = 19 * gs.tileSize;
        gs.obj[1].worldY = 2 * gs.tileSize;

        gs.obj[2] = new Exit();

        gs.obj[3] = new BonusReward();
        gs.obj[3].worldX = 10 * gs.tileSize;
        gs.obj[3].worldY = 1 * gs.tileSize;
    }

    @Test
    @DisplayName("Setting Reward Object should work properly")
    public void testSetRewardObject(){
        setUp();

        // Testing if Object has correct object type, booleans and position
        assertEquals("Coin", gs.obj[0].name);
        assertEquals(true, gs.obj[0].collision);
        assertEquals(false, gs.obj[0].hurtful);
        assertEquals(false, gs.obj[0].isExit);
        assertEquals(48, gs.obj[0].worldX);
        assertEquals(192, gs.obj[0].worldY);
    }

    @Test
    @DisplayName("Setting Punishment Object should work properly")
    public void testSetPunishmentObject(){
        setUp();

        // Testing if Object has correct object type, booleans and position
        assertEquals("Spike", gs.obj[1].name);
        assertEquals(true, gs.obj[1].collision);
        assertEquals(true, gs.obj[1].hurtful);
        assertEquals(false, gs.obj[1].isExit);
        assertEquals(912, gs.obj[1].worldX);
        assertEquals(96, gs.obj[1].worldY);
    }

    @Test
    @DisplayName("Setting Exit Object should work properly")
    public void testSetExitObject(){
        setUp();

        // Testing if Object has correct object type, booleans and position
        assertEquals("Exit", gs.obj[2].name);
        assertEquals(true, gs.obj[2].collision);
        assertEquals(false, gs.obj[2].hurtful);
        assertEquals(true, gs.obj[2].isExit);
        assertEquals(912, gs.obj[2].worldX);
        assertEquals(864, gs.obj[2].worldY);
    }

    @Test
    @DisplayName("Setting Bonus Reward Object should work properly")
    public void testSetBonusRewardObject(){
        setUp();

        // Testing if Object has correct object type, booleans and position
        assertEquals("Gold", gs.obj[3].name);
        assertEquals(true, gs.obj[3].collision);
        assertEquals(false, gs.obj[3].hurtful);
        assertEquals(false, gs.obj[3].isExit);
        assertEquals(480, gs.obj[3].worldX);
        assertEquals(48, gs.obj[3].worldY);

        // Testing if chosen position is available for a bonus reward to be spawned at
        // Note: bonus reward NOT allowed to be spawned on wall tile or on top of other objects
        int isWallTile = gs.tileManager.MapTileNum[10][1]; // checks if tile is a wall
        assertEquals(false, gs.tileManager.tile[isWallTile].collision);

        // checks if tile already has another object on it
        assertNotEquals(gs.obj[0].worldX, gs.obj[3].worldX); // reward
        assertNotEquals(gs.obj[0].worldY, gs.obj[3].worldY);

        assertNotEquals(gs.obj[1].worldX, gs.obj[3].worldX); // punishment
        assertNotEquals(gs.obj[1].worldY, gs.obj[3].worldY);

        assertNotEquals(gs.obj[2].worldX, gs.obj[3].worldX); // exit
        assertNotEquals(gs.obj[2].worldY, gs.obj[3].worldY);

    }
}