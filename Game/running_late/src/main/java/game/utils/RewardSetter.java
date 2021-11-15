package game.utils;

import game.GameScreen;
import game.objects.Reward;

import java.awt.*;

public class RewardSetter {
    GameScreen game;

    public RewardSetter(GameScreen game){ //constructor
        this.game = game;
    }

    public void setRewards(){ //place default rewards on map

        //TODO add more spots later

        game.rewards[0] = new Reward();
        game.rewards[0].x = 10 * game.tileSize;
        game.rewards[0].y = 10 * game.tileSize;

        game.rewards[1] = new Reward();
        game.rewards[1].x = 5 * game.tileSize;
        game.rewards[1].y = 5 * game.tileSize;
    }


}
