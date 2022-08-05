/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab10_2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author callumclow
 * @param <E>
 * allows dupes currently
 */
public class HashTable<E> implements Set<E> {
    
    private final int INITIAL_CAPACITY = 10;
    protected Node<E>[] table;
    protected int numElements;
    
    public HashTable() {
        this.table = new Node[INITIAL_CAPACITY];
    }
    
    private static class Node<E> {
        
        protected E element;
        protected Node next; 

        public Node(E element) {
            this.element = element;
            this.next = null;
        }
    }
    
    private boolean expandCapacity() {
        Node<E>[] oldTable = this.table;
        this.table = new Node[this.table.length * 2];
        
        for (int i = 0; i < oldTable.length; i++)
            if (oldTable[i] != null) {
                Node currentNode = oldTable[i];
                while (currentNode != null)  {
                    this.add((E) currentNode.element);
                    currentNode = currentNode.next;
                }
            }
                
        
        return true;
    }
    
    public int arraySize() {
        return table.length;
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public boolean isEmpty() {
        return numElements == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Node currentNode = table[i];
                while (currentNode != null)  {
                    if (o.equals(currentNode.element))
                        return true;
                    currentNode = currentNode.next;
                }
            }
        }
        
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        return table;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) table;
    }

    @Override
    public boolean add(E e) {
        if (e != null) {
            if (table.length*0.75 < numElements)
                expandCapacity();
            Node newNode = new Node(e);
            Node currentNode = this.table[e.hashCode()%this.table.length];
            if (currentNode != null) {
                while (currentNode.next != null)
                    currentNode = currentNode.next;
                currentNode.next = newNode;
            }
            else
                this.table[e.hashCode()%this.table.length] = newNode;
            
            numElements++;
            
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            Node previousNode = this.table[o.hashCode()%this.table.length];
            Node currentNode = this.table[o.hashCode()%this.table.length].next;
            while (currentNode != null)  {
                if (currentNode.element.equals(o)) {
                    if (currentNode != null) {
                        previousNode.next = currentNode.next;
                        return true;
                    }
                    else {
                        previousNode.next = null;
                        return true;
                    }
                }
                else {
                    previousNode = currentNode;
                    currentNode = currentNode.next;
                }
            }
            this.table[o.hashCode()%this.table.length] = null;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (Object e : c)
            add((E) e);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Node<E>[] newTable = new Node[table.length];
        int i = 0;
        for (Object e : c)
            if (contains(e)) {
                newTable[i] = (Node) e;
                i++;
            }
        
        table = newTable;
        
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        this.table = new Node[INITIAL_CAPACITY];
        return true;
    }

    @Override
    public void clear() {
        this.table = new Node[INITIAL_CAPACITY];
    }    
    
    @Override
    public String toString() {
        String output = "";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Node currentNode = table[i];
                while (currentNode != null)  {
                    output += currentNode.element;
                    output += ", ";
                    currentNode = currentNode.next;
                }
                output += "\n";
            }
        }

        return output;
    }

    //DOESN'T REALLY WORK LOL
    private class HashTableIterator implements Iterator<E> {

        private int index = 0;
        
        public HashTableIterator() {
            
        }

        @Override
        public boolean hasNext() {
            return index < numElements;
        }

        @Override
        public E next() {
            return (E) table[index++];
        }
    }
}
