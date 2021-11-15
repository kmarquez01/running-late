package game.objects;

import game.GameScreen;

public class ObjectSetter {

    GameScreen gs;

    public ObjectSetter(GameScreen gs){
        this.gs = gs;
    }

    public void setObject(){

        gs.obj[0] = new Reward();
        gs.obj[0].worldX = 5 * gs.tileSize;
        gs.obj[0].worldY = 5 * gs.tileSize;

        gs.obj[1] = new Reward();
        gs.obj[1].worldX = 3 * gs.tileSize;
        gs.obj[1].worldY = 3 * gs.tileSize;

        gs.obj[2] = new Punishment();
        gs.obj[2].worldX = 7 * gs.tileSize;
        gs.obj[2].worldY = 3 * gs.tileSize;
    }
}
