package game.utils;

import game.GameScreen;
import game.characters.Punishment;

import java.awt.*;

public class PunishmentSetter {
    GameScreen game;

    public PunishmentSetter(GameScreen game){ //constructor
        this.game = game;
    }

    public void setPunishments(){ //place default punishments on map

        //TODO add more spots later

        game.punishments[0] = new Punishment();
        game.punishments[0].x = 15 * game.tileSize;
        game.punishments[0].y = 15 * game.tileSize;

        game.punishments[1] = new Punishment();
        game.punishments[1].x = 7 * game.tileSize;
        game.punishments[1].y = 7 * game.tileSize;
    }
}
