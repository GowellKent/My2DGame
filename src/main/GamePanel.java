/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

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
    
    final int tileSize = oriTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    
    final int screenWidth = tileSize * maxScreenCol; //768px
    final int screenHeight = tileSize * maxScreenRow; //576px

    Thread gameThread;

    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        //if the game runs on 30fps = loops 30times per second
        //if the game runs on 60fps = loops 60times per second
        while (gameThread != null){
//            System.out.println("The Game Loop is Running!");
            //this loop is used for :
            //1. UPDATE : such as updating character positions
            update();
            //2. DRAW : drawing the screen with updated information
            repaint();

        }
    }

    public void update() {

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(100, 100, tileSize, tileSize);
        g2.dispose();
    }
}
