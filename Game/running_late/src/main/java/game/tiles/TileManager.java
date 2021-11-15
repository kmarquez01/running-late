package game.tiles;

import game.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    GameScreen screen;

    public int gameCol;
    public int gameRow;

    public Tiles[] tile;
    public int[][] MapTileNum;

    public TileManager(GameScreen board){
        this.screen = board;
        this.gameCol = board.gameCol;
        this.gameRow = board.gameRow;
        tile = new Tiles[10];
        MapTileNum = new int[screen.tileSize * gameCol][screen.tileSize * gameRow];
        getTitleImage();
        loadMap();
    }

    public void getTitleImage() {
        try{
            tile[0] = new Tiles();
            tile[0].image = ImageIO.read( (new FileInputStream("resources/tiles/WallTile.png") ) );
            tile[0].collision = true;

            tile[1] = new Tiles();
            tile[1].image = ImageIO.read( (new FileInputStream("resources/tiles/FloorTile.png") ) );

            tile[2] = new Tiles();
            tile[2].image = ImageIO.read( (new FileInputStream("resources/tiles/water.png") ) );

            tile[3] = new Tiles();
            tile[3].image = ImageIO.read( (new FileInputStream("resources/tiles/tree.png") ) );

            tile[4] = new Tiles();
            tile[4].image = ImageIO.read( (new FileInputStream("resources/tiles/sand.png") ) );
            tile[4].reward = true;
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

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

    // reads the map.txt
    // 0 = wall, 1 = grass, 2 = water, 3 = tree
    public void loadMap() {
        try {
            InputStream is = new FileInputStream("resources/tiles/schoolmap.txt");
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