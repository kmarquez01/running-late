package game.utils;

import game.GameScreen;
import game.characters.Player;

public class CollisionHandler {

    GameScreen screen;

    public CollisionHandler(GameScreen screen) {
        this.screen = screen;
    }

    public void checkTile(Player entity) {

        int entityLeftWorldX = entity.playerPosition.x + entity.solidArea.x;
        int entityRightWorldX = entity.playerPosition.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.playerPosition.y + entity.solidArea.y;
        int entityBottomWorldY = entity.playerPosition.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/screen.tileSize;
        int entityRightCol = entityRightWorldX/screen.tileSize;
        int entityTopRow = entityTopWorldY/screen.tileSize;
        int entityBottomRow = entityBottomWorldY/screen.tileSize;

        int tileNum1, tileNum2;

        if (entity.direction == "up") {
            entityTopRow = (entityTopWorldY - entity.speed) / screen.tileSize;
            tileNum1 = screen.tileManager.MapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = screen.tileManager.MapTileNum[entityRightCol][entityTopRow];
            if (screen.tileManager.tile[tileNum1].collision || screen.tileManager.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        } else if (entity.direction == "down") {
            entityBottomRow = (entityBottomWorldY + entity.speed) / screen.tileSize;
            tileNum1 = screen.tileManager.MapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = screen.tileManager.MapTileNum[entityRightCol][entityBottomRow];
            if (screen.tileManager.tile[tileNum1].collision || screen.tileManager.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        } else if (entity.direction == "left") {
            entityLeftCol = (entityLeftWorldX - entity.speed) / screen.tileSize;
            tileNum1 = screen.tileManager.MapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = screen.tileManager.MapTileNum[entityLeftCol][entityBottomRow];
            if (screen.tileManager.tile[tileNum1].collision || screen.tileManager.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        } else if (entity.direction == "right") {
            entityRightCol = (entityRightWorldX + entity.speed) / screen.tileSize;
            tileNum1 = screen.tileManager.MapTileNum[entityRightCol][entityTopRow];
            tileNum2 = screen.tileManager.MapTileNum[entityRightCol][entityBottomRow];
            if (screen.tileManager.tile[tileNum1].collision || screen.tileManager.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }

    }


}
