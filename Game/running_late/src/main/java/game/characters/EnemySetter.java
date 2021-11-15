package game.characters;

import game.GameScreen;
import game.objects.Punishment;
import game.objects.Reward;

public class EnemySetter {

    GameScreen gs;
    Player player;

    public EnemySetter(GameScreen gs, Player player){
        this.gs = gs;
        this.player = player;
    }

    public void setEnemy(){

        gs.enemy[0] = new Enemy(gs, gs.input, 500, 500, player);
//        gs.obj[0].worldX = 5 * gs.tileSize;
//        gs.obj[0].worldY = 5 * gs.tileSize;
//
//        gs.obj[1] = new Reward();
//        gs.obj[1].worldX = 3 * gs.tileSize;
//        gs.obj[1].worldY = 3 * gs.tileSize;
//
//        gs.obj[2] = new Punishment();
//        gs.obj[2].worldX = 7 * gs.tileSize;
//        gs.obj[2].worldY = 3 * gs.tileSize;
    }
}
