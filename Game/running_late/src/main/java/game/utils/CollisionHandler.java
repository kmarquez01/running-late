package game.utils;

import game.GameScreen;
import game.characters.Enemy;
import game.characters.Player;

/**
 * Responsible for handling all collisions in game
 */
public class CollisionHandler {

    GameScreen screen;

    public CollisionHandler(GameScreen screen) {
        this.screen = screen;
    }


    /**
     * This method checks for collisions between rewards and the player
     * @param entity - the player character
     * @return - the index of the reward the player collided with
     */
    public int checkReward(Player entity){

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
                                index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(screen.obj[i].solidArea)){
                            if(screen.obj[i].collision){
                                entity.collisionOn = true;
                            }
                                index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(screen.obj[i].solidArea)){
                            if(screen.obj[i].collision){
                                entity.collisionOn = true;
                            }
                                index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(screen.obj[i].solidArea)){
                            if(screen.obj[i].collision){
                                entity.collisionOn = true;
                            }
                                index = i;
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

    /**
     * This method checks for collisions between enemy and player
     * @param entity - the player character
     */
    public void checkEnemy(Player entity) {

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
                                entity.enemyCollision = true;
                                entity.hurt = true;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(screen.enemy[i].solidArea)){
                                entity.enemyCollision = true;
                                entity.hurt = true;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(screen.enemy[i].solidArea)){
                                entity.enemyCollision = true;
                                entity.hurt = true;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(screen.enemy[i].solidArea)){
                                entity.enemyCollision = true;
                                entity.hurt = true;
                        }
                        break;

                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                screen.enemy[i].solidArea.x = screen.enemy[i].solidAreaDefaultX;
                screen.enemy[i].solidArea.y = screen.enemy[i].solidAreaDefaultY;
            }
        }

    }

}
