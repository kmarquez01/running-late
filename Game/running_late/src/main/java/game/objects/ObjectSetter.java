package game.objects;

import game.GameScreen;

/**
 * Responsible for creating all the objects on the game screen
 */
public class ObjectSetter {

    GameScreen gs;

    /**
     * Constructor
     * @param gs - the game screen
     */
    public ObjectSetter(GameScreen gs){
        this.gs = gs;
    }

    /**
     * Creates all the rewards
     */
    public void setObject(){

        // Rewards
        makeNewReward(0, 1, 4);
        makeNewReward(1, 3, 8);
        makeNewReward(2, 13, 7);
        makeNewReward(3, 21, 1);
        makeNewReward(4, 27, 4);
        makeNewReward(5, 11, 16);
        makeNewReward(6, 25, 16);

        // Punishments
        makeNewPunishment(7, 19, 2);
        makeNewPunishment(8, 9, 6);
        makeNewPunishment(9, 17, 9);
        makeNewPunishment(10, 28, 15);
        makeNewPunishment(11, 15, 15);
        makeNewPunishment(12, 10, 12);
        makeNewPunishment(13, 28, 8);
        makeNewPunishment(14, 20, 15);
        makeNewPunishment(15, 1, 11);
        makeNewPunishment(16, 7, 16);
        makeNewPunishment(17, 16, 4);

        // Exit
        gs.obj[18] = new Exit();
    }

    // Reward creator, makes new Rew and sets location
    public void makeNewReward(int index, int x, int y){
        gs.obj[index] = new Reward();
        setObjLocation(index, x, y);
    }

    // Punishment creator, makes new Pun and sets location
    public void makeNewPunishment(int index, int x, int y){
        gs.obj[index] = new Punishment();
        setObjLocation(index, x, y);
    }

    // Sets all object locations
    public void setObjLocation(int index, int x, int y){

        gs.obj[index].worldX = x * gs.tileSize;
        gs.obj[index].worldY = y * gs.tileSize;
    }

    /**
     * Creates all the bonus rewards
     */
    // method for game screen to call in order to make bonus rewards spawn at random locations
    public void setBonusReward(){makeNewBonusReward(19);}

    // Bonus Reward creator, makes new BR Obj and sets its location
    public void makeNewBonusReward(int index){
        gs.obj[index] = new BonusReward();
        int x = (int)Math.floor(Math.random()*(28)+1); // gets random x coord for BR
        int y = getBonusRewardYCoord(x); // gets random y coord for BR based on x coord
        setObjLocation(index, x, y);
    }

    // Returns a random y coord for bonus reward based on the x coord parameter
    public int getBonusRewardYCoord(int x){

        int y = (int)Math.floor(Math.random()*(18)+1);

        boolean tileAvailable = false; //checks if tile is available (not a wall or already has an object)

        int isWallTile; // checks if tile is a wall
        boolean isObjectTile = false; //checks if tile already contains an object

        while(!tileAvailable){ //loops until chosen tile is plausible
            isWallTile = gs.tileManager.MapTileNum[x][y];   // grabs wall tile for check up
            if(!gs.tileManager.tile[isWallTile].collision){ // check of tile is wall or not
                for(int i=0; i<gs.obj.length-1; i++){ // checks every object, makes sure none are already on tile
                    if(gs.obj[i] != null){
                        if( (gs.obj[i].worldX == x * gs.tileSize) && (gs.obj[i].worldY == y * gs.tileSize) ){
                            isObjectTile = true;    // detects if the tile already contains an object
                            break;
                        }
                    }
                }
                if(!isObjectTile){tileAvailable = true;}
            }
            if(!tileAvailable){y = (int)Math.floor(Math.random()*(18)+1);} // grabs another y value
        }
        return y;
    }
}
