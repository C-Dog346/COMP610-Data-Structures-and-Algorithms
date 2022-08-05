/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab9_2;


/**
 *
 * @author callumclow
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<Task> q = new PriorityQueue<>();
        
        q.enqueue(new Task(1, 3));
        q.enqueue(new Task(2, 4));
        q.enqueue(new Task(3, 8));
        q.enqueue(new Task(4, 2));
        q.enqueue(new Task(5, 1));
        
        System.out.println(q.findMin().getElement());
        System.out.println(q.dequeueMin().getElement());
        System.out.println(q.dequeueMin().getElement());
        System.out.println(q.dequeueMin().getElement());
        System.out.println(q.dequeueMin().getElement());
        System.out.println(q.dequeueMin().getElement());
    }
}