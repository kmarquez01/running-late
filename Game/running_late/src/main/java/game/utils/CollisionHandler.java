package game.utils;

import game.GameScreen;
import game.characters.Enemy;
import game.characters.Player;

public class CollisionHandler {

    GameScreen screen;

    public CollisionHandler(GameScreen screen) {
        this.screen = screen;
    }

    public void checkTile(Player entity) {

        int entityLeftWorldX = entity.position.x + entity.solidArea.x;
        int entityRightWorldX = entity.position.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.position.y + entity.solidArea.y;
        int entityBottomWorldY = entity.position.y + entity.solidArea.y + entity.solidArea.height;

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

    public void checkTile(Enemy entity) {

        int entityLeftWorldX = entity.position.x + entity.solidArea.x;
        int entityRightWorldX = entity.position.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.position.y + entity.solidArea.y;
        int entityBottomWorldY = entity.position.y + entity.solidArea.y + entity.solidArea.height;

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

    public int checkObject(Player entity, boolean player){

        int index = 999;

        for( int i = 0; i < screen.obj.length; i++){
            if( screen.obj[i] != null){
                //Get entity's solid area position
                entity.solidArea.x = entity.position.x + entity.solidArea.x;
                entity.solidArea.y = entity.position.y + entity.solidArea.y;
                //Get the object's solid area position
                screen.obj[i].solidArea.x = screen.obj[i].worldX + screen.obj[i].solidArea.x;
                screen.obj[i].solidArea.y = screen.obj[i].worldY + screen.obj[i].solidArea.y;

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(screen.obj[i].solidArea)){
                            if(screen.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(screen.obj[i].solidArea)){
                            if(screen.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(screen.obj[i].solidArea)){
                            if(screen.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(screen.obj[i].solidArea)){
                            if(screen.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;

                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                screen.obj[i].solidArea.x = screen.obj[i].solidAreaDefaultX;
                screen.obj[i].solidArea.y = screen.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }

    // add collision with enemy
    public int checkEnemy(Player entity, boolean player) {

        // if enemy collision, set player.enemyCollision = true;
        // if enemy collision == true, move to game over state

        int index = 999;

        for( int i = 0; i < screen.enemy.length; i++){
            if( screen.enemy[i] != null){

                //Get entity's solid area position
                entity.solidArea.x = entity.position.x + entity.solidArea.x;
                entity.solidArea.y = entity.position.y + entity.solidArea.y;

                //Get the enemy's solid area position
                screen.enemy[i].solidArea.x = screen.enemy[i].position.x + screen.enemy[i].solidArea.x;
                screen.enemy[i].solidArea.y = screen.enemy[i].position.y + screen.enemy[i].solidArea.y;

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(screen.enemy[i].solidArea)){
                            //if(screen.enemy[i].collision){
                                entity.enemyCollision = true;
                            //}
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(screen.enemy[i].solidArea)){
                            //if(screen.enemy[i].collision){
                                entity.enemyCollision = true;
                            //}
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(screen.enemy[i].solidArea)){
                            //if(screen.enemy[i].collision){
                                entity.enemyCollision = true;
                            //}
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(screen.enemy[i].solidArea)){
                            //if(screen.enemy[i].collision){
                                entity.enemyCollision = true;
                           // }
                            if (player){
                                index = i;
                            }
                        }
                        break;

                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                screen.enemy[i].solidArea.x = screen.enemy[i].solidAreaDefaultX;
                screen.enemy[i].solidArea.y = screen.enemy[i].solidAreaDefaultY;
            }
        }

        return index;

    }





}
