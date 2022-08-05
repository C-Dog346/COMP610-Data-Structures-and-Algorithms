/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa.assignment.pkg1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;


/**
 *
 * @author Callum
 */ 
public class HotplateGUI extends JPanel implements ActionListener{
    
    private final Timer timer;
    private final JSlider temperatureSlider,heatConstantSlider;
    private final JLabel temperatureLabel,heatConstantLabel;
    private final DrawPanel drawPanel;
    private final Element[][] grid;

    public HotplateGUI() {   
        super(new BorderLayout());
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e){}
    
        grid = new Element[20][20];
        drawPanel = new DrawPanel();
        temperatureSlider = new JSlider(0, 1000, 0);
        heatConstantSlider = new JSlider(1, 100, 1);
        temperatureLabel = new JLabel();
        heatConstantLabel = new JLabel();
        
        temperatureLabel.setText("Temperature 0");
        heatConstantLabel.setText("Heat constant 0");
        
        for (int i = 0;i < 20; i++)
            for (int j = 0; j < 20; j++)
                grid[i][j] = new Element(0, 0.5);
       
        temperatureSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                temperatureLabel.setText("Temperature " + ((JSlider)e.getSource()).getValue());
            }
        });
        heatConstantSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                heatConstantLabel.setText("Heat constant " + (double) ((JSlider)e.getSource()).getValue()/100);
            }
        });
        
        JPanel panel = new JPanel();
        panel.add(temperatureSlider);
        panel.add(temperatureLabel);
        
        panel.add(heatConstantSlider);
        panel.add(heatConstantLabel);
        


        super.add(panel,BorderLayout.SOUTH);
        super.add(drawPanel,BorderLayout.CENTER);
        
        timer = new Timer(20,this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
    }


    public class DrawPanel extends JPanel {
        public DrawPanel()
        {
            super.setPreferredSize(new Dimension(600,600));
            super.setBackground(Color.RED);
            super.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {                  
                    int x = e.getX();
                    int y = e.getY();
                    
                    int i = x*20/600;
                    int j = y*20/600;
                    
                    System.out.println(i + "\t" + j);
                    
                    Element element = grid[i][j];
                    element.applyTempToElement(temperatureSlider.getValue());
                    
                }
            });
            
        }
        
        

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            final int WIDTH = 30;
            final int HEIGHT = WIDTH;           

            for (int i = 0;i < 20; i++)
                for (int j = 0; j < 20; j++) {
                    grid[i][j] = new Element(500, 0.5);
                    g.setColor(new Color(0, 0, 255));
                    g.fillRect(0 + j * WIDTH, 0 + i * HEIGHT, WIDTH, HEIGHT);
                    g.setColor(new Color(255, 0, 0));
                    g.drawRect(0 + j * WIDTH, 0 + i * HEIGHT, WIDTH, HEIGHT);
            }
        }
    }
    
    public static void main(String[] args) {   
        JFrame frame = new JFrame("HOT PLATE GUI, ASSIGNMENT 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new HotplateGUI());
        frame.pack();  
        frame.setLocation(600, 200);
        frame.setVisible(true);                                      //show the frame
        
        
    }


}

