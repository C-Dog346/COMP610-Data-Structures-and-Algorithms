/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2_1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


/**
 *
 * @author Callum
 */
public class Ball implements Runnable {

    private int World_W;
    private int World_H;
    private int x;
    private int y;
    private int xv;
    private int yv;
    private Color color;
    private Random rand;
    private boolean stopRequested;
    private int size;
    private final int speed;
    
    public Ball(int width, int height) {
       this.World_W = width;
       this.World_H = height;
       this.rand = new Random();
       this.speed = rand.nextInt(1000)+100; 
       this.size = rand.nextInt(100)+10;
       stopRequested = false;
       this.x = 0;
       this.y = 0;
       this.xv = rand.nextInt(10)+1;
       this.yv = rand.nextInt(10)+1;
       this.color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }
    @Override
    public void run() {
        while (!stopRequested) {
            moveBall();
            try {
                Thread.sleep(10000/speed);
            } catch (InterruptedException ex) {

            }
        }
    }
    
    public void moveBall() {
        if (x + xv < 0 || x + size + xv > this.World_W)
            xv *= -1;
        if (y + yv < 0 || y + size + yv > this.World_H)
            yv *= -1;
        
        x += xv;
        y += yv;
    }
    
    public void drawBall(Graphics g) {
        g.setColor(this.color);
        g.fillOval(x, y, size, size);
    }

    // Timer timer = new Timer()
    // timer.schedule(thread, start in this time, repeat after this time on loop)
    // ^ this isnt accurate and isnt good for GUI's, use an actionlistener 
    // instead of a thread with timer.start and the listener and last parameter of 
    // schedule in the constructor
}