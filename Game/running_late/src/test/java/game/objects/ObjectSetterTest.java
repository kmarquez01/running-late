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
    void setUp() {this.gs = new GameScreen();}

    @Test
    @DisplayName("Setting Reward Object should work properly")
    public void testSetRewardObject(){
        setUp();
        gs.setupGame();
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
        gs.setupGame();
        // Testing if Object has correct object type, booleans and position
        assertEquals("Spike", gs.obj[7].name);
        assertEquals(true, gs.obj[7].collision);
        assertEquals(true, gs.obj[7].hurtful);
        assertEquals(false, gs.obj[7].isExit);
        assertEquals(912, gs.obj[7].worldX);
        assertEquals(96, gs.obj[7].worldY);
    }

    @Test
    @DisplayName("Setting Exit Object should work properly")
    public void testSetExitObject(){
        setUp();
        gs.setupGame();
        // Testing if Object has correct object type, booleans and position
        assertEquals("Exit", gs.obj[18].name);
        assertEquals(true, gs.obj[18].collision);
        assertEquals(false, gs.obj[18].hurtful);
        assertEquals(true, gs.obj[18].isExit);
        assertEquals(912, gs.obj[18].worldX);
        assertEquals(864, gs.obj[18].worldY);
    }

    @Test
    @DisplayName("Setting Bonus Reward Object should work properly")
    public void testSetBonusRewardObject(){
        setUp();
        gs.setupGame();
        gs.aSetter.setBonusReward();
        // Testing if Object has correct object type, booleans and position
        assertEquals("Gold", gs.obj[19].name);
        assertEquals(true, gs.obj[19].collision);
        assertEquals(false, gs.obj[19].hurtful);
        assertEquals(false, gs.obj[19].isExit);

        // Testing if chosen position is available for a bonus reward to be spawned at
        // Note: bonus reward NOT allowed to be spawned on wall tile or on top of other objects
        int isWallTile = gs.tileManager.MapTileNum[(gs.obj[19].worldX)/48][(gs.obj[19].worldY)/48]; // checks if tile is a wall
        assertEquals(false, gs.tileManager.tile[isWallTile].collision);

        // checks if tile already has another object on it (should NOT since bonus is on it)
        assertNotEquals(gs.obj[0].worldX, gs.obj[3].worldX); // reward
        assertNotEquals(gs.obj[0].worldY, gs.obj[3].worldY);

        assertNotEquals(gs.obj[1].worldX, gs.obj[3].worldX); // punishment
        assertNotEquals(gs.obj[1].worldY, gs.obj[3].worldY);

        assertNotEquals(gs.obj[2].worldX, gs.obj[3].worldX); // exit
        assertNotEquals(gs.obj[2].worldY, gs.obj[3].worldY);
    }
}