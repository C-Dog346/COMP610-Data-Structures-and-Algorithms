package Lab4_2;

/**
   An interface that defines the abstract data type
   for a stack collection of elements with type E
*/
import java.util.NoSuchElementException;

public interface QueueADT<E>
{
   // adds one element to the top of this stack
   public void enqueue(E element);
   // removes and returns the top element from this stack
   public E dequeue() throws NoSuchElementException;
   // returns without removing the top element of this stack
   public E first() throws NoSuchElementException;
   // returns true if this stack contains no elements
   public boolean isEmpty();
   // returns the number of elements in this stack
   public int size();
}
