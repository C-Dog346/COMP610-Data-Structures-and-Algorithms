/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab9_2;

/**
 *
 * @author callumclow
 * @param <Task>
 */
public class PriorityQueue<Task> {

    private ArrayHeap tasks;
    private PriorityComparator comparator;

    public PriorityQueue() {
        this.comparator = new PriorityComparator();
        this.tasks = new ArrayHeap(comparator);
    }

    public void enqueue(Task task) {
        
        tasks.add(task);
    }

    public Task dequeueMin() {
        return (Task) tasks.removeMin();
    }

    public Task findMin() {
        return (Task) tasks.getMin();
    }
}