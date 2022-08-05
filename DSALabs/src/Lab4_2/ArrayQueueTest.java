/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab4_2;

/**
 *
 * @author Callum
 */
public class ArrayQueueTest {
    public static void main(String[] args) {
        // */
        ArrayQueue<Integer> rayQ = new ArrayQueue<>();
        
        System.out.println("\n\nrayQ Test");
        
       // System.out.println(rayQ.toString());
        rayQ.enqueue(1);
       // System.out.println(rayQ.toString());
        rayQ.enqueue(2);
       // System.out.println(rayQ.toString());
        rayQ.enqueue(3);
        rayQ.enqueue(4);
        rayQ.enqueue(5);
        System.out.println(rayQ.toString());
        System.out.println("DeQ");
        System.out.println(rayQ.dequeue());
        //System.out.println(rayQ.toString());
        System.out.println(rayQ.dequeue());
        rayQ.enqueue(6);
        rayQ.enqueue(7);
        System.out.println(rayQ.toString());
        rayQ.enqueue(100);
        rayQ.enqueue(102);
        rayQ.enqueue(106);
        rayQ.enqueue(133);
        System.out.println(rayQ.toString());
        System.out.println("1st");
        System.out.println(rayQ.first());
        System.out.println("MT");
        System.out.println(rayQ.isEmpty());
        System.out.println("Sighs");
        System.out.println(rayQ.size());
       // */
    }
}
