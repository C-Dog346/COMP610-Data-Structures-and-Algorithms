/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Lab4_1;

import Lab4_2.ArrayQueue;

/**
 *
 * @author callumclow
 */
public class ArrayStackTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       
        ArrayStack2<Integer> raystack = new ArrayStack2<>();
       
        System.out.println("raystack test");
        
        System.out.println(raystack.toString());
        raystack.push(1);
        System.out.println(raystack.toString());
        raystack.push(2);
        System.out.println(raystack.toString());
        raystack.push(3);
        System.out.println(raystack.toString());
        System.out.println(raystack.peek());
        System.out.println(raystack.pop());
        System.out.println(raystack.toString());
        System.out.println(raystack.pop());
        System.out.println(raystack.toString());
        System.out.println(raystack.peek());
        
        // yeah this thing is much slower than the OG ArrayStack, way slower
        // instead of O(1) its O(n) gg so bad
    }
    
}
