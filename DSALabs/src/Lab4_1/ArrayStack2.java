package Lab4_1;

/**
 * A class that implements a stack collection using an array as the underlying
 * data structure
 *
 * @author Andrew Ensor
 */
import java.util.Collection;
import java.util.NoSuchElementException;

public class ArrayStack2<E> implements StackADT<E> {

    private final int INITIAL_CAPACITY = 20;
    protected int numElements;
    protected E[] elements;

    // default constructor that creates a new stack 
    // that is initially empty
    public ArrayStack2() {
        numElements = 0;
        elements = (E[]) (new Object[INITIAL_CAPACITY]); // unchecked
    }

    // constructor for creating a new stack which
    // initially holds the elements in the collection c
    public ArrayStack2(Collection<? extends E> c) {
        this();
        for (E element : c) {
            push(element);
        }
    }

    
    public void push(E element) {
        if (numElements >= elements.length) {
            expandCapacity();
        }
        
        if (numElements != 0) {
            for (int i = numElements - 1;i >= 0;i--) {
                elements[i+1] = elements[i];
            }
            
        }
        elements[0] = element;
        numElements++;
    }

    public E pop() throws NoSuchElementException {
        if (numElements > 0) {
            E topElement = elements[0];
            elements[0] = null;
            numElements--;
            for (int i = 1; i < numElements+1; i++) {
                elements[i-1] = elements[i];
            }
            elements[numElements] = null;
            return topElement;
        } else {
            throw new NoSuchElementException();
        }
    }

    // returns without removing the top element of this stack
    public E peek() throws NoSuchElementException {
        if (numElements > 0) {
            return elements[0];
        } else {
            throw new NoSuchElementException();
        }
    }

    // returns true if this stack contains no elements
    public boolean isEmpty() {
        return (numElements == 0);
    }

    // returns the number of elements in this stack
    public int size() {
        return numElements;
    }

    // returns a string representation of the stack from top to bottom
    public String toString() {
        String output = "[";
        for (int i = numElements - 1; i >= 0; i--) {
            output += elements[i];
            if (i > 0) {
                output += ",";
            }
        }
        output += "]";
        return output;
    }

    // helper method which doubles the current size of the array
    private void expandCapacity() {
        E[] largerArray = (E[]) (new Object[elements.length * 2]);//unchecked
        // copy the elements array to the largerArray
        for (int i = 0; i < numElements; i++) {
            largerArray[i] = elements[i];
        }
        elements = largerArray;
    }
}
