/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab3_1;

import java.util.Set;

/**
 *
 * @author Callum
 */
public class ArraySetTest {
    public static void main(String[] args) {
        Set<Integer> testSet = new ArraySet<Integer>();
    
        int n = 100; 
        
        System.out.println(System.nanoTime());
        
        for (int i = 0;i <= n;i++)
            testSet.add(i);
        
        System.out.println(System.nanoTime());        
        testSet.contains(n/2);
        System.out.println(System.nanoTime());
        testSet.remove(n/2);
        System.out.println(System.nanoTime());
        
        Set<Integer> testSet2 = new ArraySet<Integer>();
    
        System.out.println("\n\n\n");
        
        n = 500;
        
        System.out.println(System.nanoTime());
        
        for (int i = 0;i <= n;i++)
            testSet.add(i);
        
        System.out.println(System.nanoTime());        
        testSet2.contains(n/2);
        System.out.println(System.nanoTime());
        testSet2.remove(n/2);
        System.out.println(System.nanoTime());
        
        // uhhh plot a graph apparently? - might be able to do this with code 
        // but manually doing it wont be too tedious
    }    
}
