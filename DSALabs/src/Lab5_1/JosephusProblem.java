/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5_1;


import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Callum
 */
public class JosephusProblem {

    private Queue<Integer> q;
    private int numberOfSoldiers;
    private int gap;
    
    public JosephusProblem (int gap, int numberOfSoldiers){
        this.q = new LinkedList<>();
        this.gap = gap;
        this.numberOfSoldiers = numberOfSoldiers;
        for (int i = 1; i < numberOfSoldiers+1; i++)
            this.q.add(i);
    }
    
    public Integer[] execute() {
        Integer[] killOrder = new Integer[this.numberOfSoldiers];
        
        int count = 0;
        
        while(!this.q.isEmpty()) {
            for (int i = 1; i <= this.gap; i++) {
                Integer x = this.q.poll();
                
                if (i == this.gap) {
                    killOrder[count] = x;
                    count++;
                }
                else {
                    this.q.add(x);
                }
            }
        }
       
        return killOrder;
    }
    
    public static void main(String[] args) {
        JosephusProblem test = new JosephusProblem(3, 8);
        System.out.println(Arrays.toString(test.execute()));
        
    }
    
}

/*
    In the ArrayList implementation of the Josephus Problem, all of the remove 
    operations that are done to indicies that aren't the final index have a 
    complexity of O(n) as they need to shift each of the following indicies down 
    to fill in the gap.
    
    In the Queue implementation of the Joesephus Problem, all of the remove 
    operations that are done to indicies that aren't the final index have a 
    complexity of O(n) also, as the loop depends on the number of objects
    */