package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10]; //declaring number of tile types
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String mapName){
        try {
            InputStream is = getClass().getResourceAsStream(mapName); //importing text file
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); //reading imported text file

            int col = 0;
            int row = 0;


            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();
                while (col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }

            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
//        g2.drawImage(tile[0].image, 0,0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 48,0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96,0, gp.tileSize, gp.tileSize, null);

        int col = 0, row = 0, x = 0, y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tilenum = mapTileNum[col][row];

            g2.drawImage(tile[tilenum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if (col == gp.maxScreenCol){
                //resets column number and x pos
                col = 0;
                x = 0;
                // increases row number and y pos
                row += 1;
                y += gp.tileSize;
            }
        }

    }

}
