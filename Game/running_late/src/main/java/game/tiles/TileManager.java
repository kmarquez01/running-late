package game.tiles;

import game.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

/**
 * Responsible for drawing the tiles and managing the map layout
 */
public class TileManager {

    GameScreen screen;

    public int gameCol;
    public int gameRow;

    public Tiles[] tile;
    public int[][] MapTileNum;

    /**
     * Creates a maptile
     * @param screen - the game screen
     */
    public TileManager(GameScreen screen){
        this.screen = screen;
        this.gameCol = screen.gameCol;
        this.gameRow = screen.gameRow;
        tile = new Tiles[10];
        MapTileNum = new int[this.screen.tileSize * gameCol][this.screen.tileSize * gameRow];
        getTitleImage();
        loadMap();
    }

    /**
     * Sets the sprites of the tiles
     */
    public void getTitleImage() {
        try{
            tile[0] = new Tiles();
            tile[0].image = ImageIO.read(getClass().getResource("/tiles/WallTile.png"));
            tile[0].collision = true;

            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getResource("/tiles/FloorTile.png"));

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Draws the tiles
     * @param G2D - graphics
     */
    public void draw(Graphics2D G2D){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gameCol && row < gameRow){
            int tileNum = MapTileNum[col][row];
            if (tileNum != 10) {
                G2D.drawImage(tile[tileNum].image, x, y, screen.tileSize, screen.tileSize, null);
            }
            col++;
            x += screen.tileSize;

            if(col == gameCol){
                col = 0;
                x = 0;
                row++;
                y += screen.tileSize;
            }
        }
    }

    /**
     * Reads schoolmap.txt and draws the tiles
     */
    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/tiles/schoolmap.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gameCol && row < gameRow) {
                String line = br.readLine();

                while (col < gameCol) {
                    String numbers[] = line.split(" ");

                    MapTileNum[col][row] = Integer.parseInt(numbers[col]);
                    col++;
                }
                if (col == gameCol) {
                    col = 0;
                    row++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}