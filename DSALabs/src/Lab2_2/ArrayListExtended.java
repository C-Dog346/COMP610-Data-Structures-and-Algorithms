/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2_2;

import Lab2_2.RandomObtainable;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 * @author Callum
 */
public class ArrayListExtended extends ArrayList implements RandomObtainable<Object> {

    @Override
    public Object getRandom() throws NoSuchElementException {
        Random rand = new Random();
        return this.get(rand.nextInt(this.size()));
    }

    @Override
    public boolean removeRandom() throws UnsupportedOperationException {
        return this.remove(getRandom());
    }
    
    public static void main(String[] args) {
        ArrayListExtended list = new ArrayListExtended();
        
        list.add("Bob");
        list.add(3);
        list.add(3432423);
        list.add("-");
        
        System.out.println(list.toString());
        System.out.println(list.getRandom());
        list.removeRandom();
        System.out.println(list.toString());
    }
    
}
