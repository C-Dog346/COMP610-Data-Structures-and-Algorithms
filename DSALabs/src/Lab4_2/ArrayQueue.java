/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab4_2;

/**
 *
 * @author callumclow
 */
import java.util.Collection;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements QueueADT<E> {

    private final int INITIAL_CAPACITY = 5;
    private int numElements;
    private E[] elements;
    private int head;
    private int tail;

    public ArrayQueue() {
        numElements = 0;
        elements = (E[]) (new Object[INITIAL_CAPACITY]);
        head = 0;
        tail = 0;
    }

    public ArrayQueue(Collection<? extends E> c) {
        this();
        for (E element : c) {
            enqueue(element);
        }
    }

    @Override
    public void enqueue(E element) {

        // add the new node to the end of the list
        if (numElements >= elements.length)
            expandCapacity();
        if (tail == 0 && numElements != 0) {
            elements[++tail] = element;
        } else if (tail == elements.length) {
            tail = 0;
            elements[tail] = element;
        } else {
            elements[tail++] = element;
        }
        numElements++;
    }

    @Override
    public E dequeue() throws NoSuchElementException {
        if (numElements > 0) {
            if (head == elements.length)
                head = 0;
            E front = elements[head];
            elements[head] = null;
            head++;
            numElements--;
            return front;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public E first() throws NoSuchElementException {
        E front = elements[head];
        return front;
    }

    @Override
    public boolean isEmpty() {
        return (numElements == 0);
    }

    @Override
    public int size() {
        return numElements;
    }
    
    public String toString() {
        String output = "[";
        
        for (E e: elements)
            if (e != null) {
                output += e;
                if (e != elements[numElements-1])
                    output += ",";   
            }
        output += "]";
        return output;
    }
    
    private void expandCapacity() {
        E[] largerArray = (E[]) (new Object[elements.length * 2]);
        boolean check = false;
        int cursor = 0;
        int total = numElements;
        while(!check) {
            if (largerArray[numElements-1] == null) {
                try {
                    for (int i = 0; i < total; i++) {
                        largerArray[i+cursor] = this.elements[head+i];
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    cursor = head+1;
                    total -= head+1;
                    head = 0;
                }
            }
            else
                check = true;
        }
        
        this.tail = cursor + total;
        this.elements = largerArray;
    }

}
