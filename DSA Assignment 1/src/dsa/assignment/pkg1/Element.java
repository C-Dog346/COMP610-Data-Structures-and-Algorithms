/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa.assignment.pkg1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Callum
 */
public class Element implements Runnable{
    private List<Element> neighbours;
    private double currentTemp;
    private double heatConstant;
    private boolean stopRequested;
    
    
    public Element(double startTemp, double heatConstant) {
        if (heatConstant <= 0.0 || heatConstant > 1.0) {
            System.err.println("ERROR: Heat constant is not between 0.0 (exclusive) and 1.0. Setting heat constant to 0.5");
            this.heatConstant = 0.5;
        }
        this.currentTemp = startTemp;
        this.heatConstant = heatConstant;
        this.neighbours = new ArrayList<>();
    }
    
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
    
    public synchronized double getTemperature() {
        return this.currentTemp;
    }
    
    public void requestStop() {
        this.stopRequested = true;
    }
    
    @Override
    public void run() {
        this.stopRequested = false;
        while(!this.stopRequested) {
            
            double averageTemps = 0.0;
            
            for (Element element : this.neighbours) {
                averageTemps += element.getTemperature();
            }
            averageTemps /= neighbours.size();
            applyTempToElement(averageTemps);
            
            System.out.println(getTemperature());
        }
    }
    
    public void addNeighbour(Element element) {
        this.neighbours.add(element);
    }
    
    /**
     *
     * @param appliedTemp
     */
    public synchronized void applyTempToElement(double appliedTemp) {
        this.currentTemp += (appliedTemp - this.currentTemp) * this.heatConstant;
    }
    
    public static void main(String[] args) {
        Element element1 = new Element(100, 0.5);
        Element element2 = new Element(0, 0.5);
        Element element3 = new Element(0, 0.5);
        Element element4 = new Element(25, 0.5);
        
        element1.addNeighbour(element2);
        element1.addNeighbour(element3);
        element1.addNeighbour(element4);
        
        element2.addNeighbour(element1);
        element2.addNeighbour(element3);
        element2.addNeighbour(element4);
        
        element3.addNeighbour(element1);
        element3.addNeighbour(element2);
        element3.addNeighbour(element4);
        
        element4.addNeighbour(element1);
        element4.addNeighbour(element2);
        element4.addNeighbour(element3);
        
        element1.start();
        element2.start();
        element3.start();
        element4.start();
    }
}