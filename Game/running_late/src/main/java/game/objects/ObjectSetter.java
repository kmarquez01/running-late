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
    }
}
