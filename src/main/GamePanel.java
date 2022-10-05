/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author gowell
 */
public class GamePanel extends JPanel{
    //screen settings
    final int oriTileSize = 16; //16x16 tile size
    final int scale = 3;
    
    final int tileSize = oriTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    
    final int screenWidth = tileSize * maxScreenCol; //768px
    final int screenHeight = tileSize * maxScreenRow; //576px
    
    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        
    }
    
}
