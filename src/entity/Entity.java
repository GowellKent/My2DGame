package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

//Stores variables that will be used for player / monster / NPC classes
public class Entity {

    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle hitBox;

    public int hitBoxDefX, hitBoxDefY;
    public boolean collisionOn = false;
}
