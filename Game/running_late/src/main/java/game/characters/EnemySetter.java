package game.characters;

import game.GameScreen;


public class EnemySetter {

    GameScreen gs;
    Player player;

    public EnemySetter(GameScreen gs, Player player){
        this.gs = gs;
        this.player = player;
    }

    public void setEnemy(){
        gs.enemy[0] = new Enemy(gs,  50, 800, player);
        gs.enemy[1] = new Enemy(gs,900, 50, player);
    }
}
