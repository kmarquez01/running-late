package game.objects;

import game.GameScreen;

public class ObjectSetter {

    GameScreen gs;

    public ObjectSetter(GameScreen gs){
        this.gs = gs;
    }

    public void setObject(){

        // Rewards
        gs.obj[0] = new Reward();
        gs.obj[0].worldX = 1 * gs.tileSize;
        gs.obj[0].worldY = 4 * gs.tileSize;

        gs.obj[1] = new Reward();
        gs.obj[1].worldX = 3 * gs.tileSize;
        gs.obj[1].worldY = 8 * gs.tileSize;

        gs.obj[2] = new Reward();
        gs.obj[2].worldX = 13 * gs.tileSize;
        gs.obj[2].worldY = 7 * gs.tileSize;

        gs.obj[3] = new Reward();
        gs.obj[3].worldX = 21 * gs.tileSize;
        gs.obj[3].worldY = 1 * gs.tileSize;

        gs.obj[4] = new Reward();
        gs.obj[4].worldX = 27 * gs.tileSize;
        gs.obj[4].worldY = 4 * gs.tileSize;

        gs.obj[5] = new Reward();
        gs.obj[5].worldX = 11 * gs.tileSize;
        gs.obj[5].worldY = 16 * gs.tileSize;

        gs.obj[6] = new Reward();
        gs.obj[6].worldX = 25 * gs.tileSize;
        gs.obj[6].worldY = 16 * gs.tileSize;

        // Punishments
        gs.obj[7] = new Punishment();
        gs.obj[7].worldX = 19 * gs.tileSize;
        gs.obj[7].worldY = 2 * gs.tileSize;

        gs.obj[8] = new Punishment();
        gs.obj[8].worldX = 9 * gs.tileSize;
        gs.obj[8].worldY = 6 * gs.tileSize;

        gs.obj[9] = new Punishment();
        gs.obj[9].worldX = 17 * gs.tileSize;
        gs.obj[9].worldY = 9 * gs.tileSize;

        gs.obj[10] = new Punishment();
        gs.obj[10].worldX = 28 * gs.tileSize;
        gs.obj[10].worldY = 15 * gs.tileSize;

        gs.obj[11] = new Punishment();
        gs.obj[11].worldX = 15 * gs.tileSize;
        gs.obj[11].worldY = 15 * gs.tileSize;

        gs.obj[12] = new Punishment();
        gs.obj[12].worldX = 10 * gs.tileSize;
        gs.obj[12].worldY = 12 * gs.tileSize;

        gs.obj[13] = new Punishment();
        gs.obj[13].worldX = 28 * gs.tileSize;
        gs.obj[13].worldY = 8 * gs.tileSize;

        gs.obj[14] = new Punishment();
        gs.obj[14].worldX = 20 * gs.tileSize;
        gs.obj[14].worldY = 15 * gs.tileSize;

        gs.obj[15] = new Punishment();
        gs.obj[15].worldX = 1 * gs.tileSize;
        gs.obj[15].worldY = 11 * gs.tileSize;

        gs.obj[16] = new Punishment();
        gs.obj[16].worldX = 7 * gs.tileSize;
        gs.obj[16].worldY = 16 * gs.tileSize;

        gs.obj[17] = new Punishment();
        gs.obj[17].worldX = 16 * gs.tileSize;
        gs.obj[17].worldY = 4 * gs.tileSize;

        gs.obj[19] = new Exit();

    }

    public void setBonusReward(){

        int x = (int)Math.floor(Math.random()*(28)+1); // gets random x,y coords
        int y = (int)Math.floor(Math.random()*(18)+1);

        boolean tileAvailable = false; //checks if tile is available (not a wall or already has an object)

        int isWallTile; // checks if tile is a wall
        boolean isObjectTile = false; //checks if tile already contains an object

        while(tileAvailable == false){ //loops until chosen tile is plausible
            isWallTile = gs.tileManager.MapTileNum[x][y];
            if(!gs.tileManager.tile[isWallTile].collision){
                for(int i=0; i<gs.obj.length-2; i++){
                    // checks if reward is already on tile
                    if( (gs.obj[i].worldX == x * gs.tileSize) && (gs.obj[i].worldY == y * gs.tileSize) ){
                        isObjectTile = true;
                    }
                }
                if(!isObjectTile){
                    tileAvailable = true;
                }
                else{
                    y = (int)Math.floor(Math.random()*(18)+1);
                }
            }
            else{
                y = (int)Math.floor(Math.random()*(18)+1);
            }
        }

        gs.obj[18] = new BonusReward(); // sets bonus reward at random generated x,y coords
        gs.obj[18].worldX = x * gs.tileSize;
        gs.obj[18].worldY = y * gs.tileSize;
    }
}
