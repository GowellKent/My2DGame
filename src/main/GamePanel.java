/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author gowell
 */
public class GamePanel extends JPanel implements Runnable{
    //screen settings
    final int oriTileSize = 16; //16x16 tile size
    final int scale = 3;
    public final int tileSize = oriTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768px
    public final int screenHeight = tileSize * maxScreenRow; //576px

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxWorldWidth = tileSize * maxScreenCol;
    public final int maxWorldHeight = tileSize * maxScreenRow;


    //setting FPS variable
    int FPS = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);
    TileManager tileM = new TileManager(this);
    public CollisionChecker cCheck = new CollisionChecker(this);
    public SuperObject obj[] = new SuperObject[10];
    public AssetSetter aSetter = new AssetSetter(this);

    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
    }

    public void setUpGame(){
        aSetter.setObject();
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    // THIS IS SLEEP GAME LOOP METHOD

//    @Override
//    public void run() {
//
//        //if the game runs on 30fps = loops 30times per second
//        //if the game runs on 60fps = loops 60times per second
//
////        getting current system time in nano seconds
////        long currentTime = System.nanoTime();
//
//        double drawInterval = 1000000000/FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while (gameThread != null){
//            //this loop is used for :
//            //1. UPDATE : such as updating character positions
//            update();
//            //2. DRAW : drawing the screen with updated information
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 1000000;
//
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime); //sleeping thread for remaining time to limit loops
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//    }

    //THIS IS DELTA GAME LOOP METHOD
    @Override
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/ drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000){
                //System.out.println(drawCount + "FPS");
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

        player.update();

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        // Background before Character Sprite

        //Drawing object
        for (int i = 0 ; i < obj.length; i++){
            if (obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        player.draw(g2);

        g2.dispose();
    }
}
