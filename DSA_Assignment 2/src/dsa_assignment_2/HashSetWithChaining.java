/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsa_assignment_2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author callumclow
 * @param <E> allows dupes currently
 */
public class HashSetWithChaining<E> implements Set<E> {

    private final int INITIAL_CAPACITY = 10;
    private double loadFactor;
    private Node<E>[] table;
    private int numElements;

    public HashSetWithChaining() {
        this.table = new Node[INITIAL_CAPACITY];
        this.loadFactor = 0.75;
        this.numElements = 0;
    }

    public HashSetWithChaining(double loadFactor) {
        this.table = new Node[INITIAL_CAPACITY];
        this.loadFactor = loadFactor;
        this.numElements = 0;
    }

    private static class Node<E> {

        protected E element;
        protected Node next;

        public Node(E element) {
            this.element = element;
            this.next = null;
        }

        public String toString() {
            String output = "";

            Node currentNode = this;
            while (currentNode != null) {
                output += String.valueOf(currentNode.element.toString()) + " ";
                currentNode = currentNode.next;
            }

            return output;
        }
    }

    private boolean expandCapacity() {
        System.out.println("LOAD FACTOR EXCEEDED, EXPANDING CAPACITY");
        System.out.println("Size is: " + size());
        Node<E>[] oldTable = this.table;
        this.table = new Node[this.table.length * 2];

        int realSize = size();

        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                Node currentNode = oldTable[i];
                while (currentNode != null) {
                    this.add((E) currentNode.element);
                    currentNode = currentNode.next;
                }
                this.numElements = realSize;
            }
        }

        return true;
    }

    public int getArraySize() {
        return this.table.length;
    }

    @Override
    public int size() {
        return this.numElements;
    }

    @Override
    public boolean isEmpty() {
        return this.numElements == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node currentNode = this.table[Math.abs(o.hashCode() % this.table.length)];

        if (currentNode == null) {
            return false;
        }

        while (currentNode != null) {
            if (currentNode.element.equals(o)) {
                return true;
            }
            currentNode = currentNode.next;
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new HashSetWithChainingIterator(this.table, this.numElements);
    }

    @Override
    public Object[] toArray() {
        return this.table;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) this.table;
    }

    @Override
    public boolean add(E e) {
        if (e != null && !contains(e)) {
            if (this.loadFactor * this.getArraySize() < this.numElements) {
                expandCapacity();
            }
            Node newNode = new Node(e);
            Node currentNode = this.table[Math.abs(e.hashCode() % this.table.length)];
            if (currentNode != null) {
                while (currentNode.next != null) {
                    currentNode = currentNode.next;
                }
                currentNode.next = newNode;
            }
            else {
                this.table[Math.abs(e.hashCode() % this.table.length)] = newNode;
            }

            this.numElements++;

            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            Node previousNode = this.table[Math.abs(o.hashCode() % this.table.length)];
            Node currentNode = this.table[Math.abs(o.hashCode() % this.table.length)].next;
            if (previousNode.element.equals(o)) {
                this.table[Math.abs(o.hashCode() % this.table.length)] = currentNode;
                this.numElements--;
                return true;
            }

            while (currentNode != null) {
                if (currentNode.element.equals(o)) {
                    if (currentNode != null) {
                        previousNode.next = currentNode.next;
                        this.numElements--;
                        return true;
                    }
                    else {
                        previousNode.next = null;
                        this.numElements--;
                        return true;
                    }
                }
                else {
                    previousNode = currentNode;
                    currentNode = currentNode.next;
                }
            }
            this.table[Math.abs(o.hashCode() % this.table.length)] = null;
            this.numElements--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof HashSetWithChaining)) {
            return false;
        }

        HashSetWithChaining test = (HashSetWithChaining) o;

        return Double.compare(Arrays.hashCode(this.table), Arrays.hashCode(test.table)) == 0
                && Double.compare(this.loadFactor, test.loadFactor) == 0
                && Double.compare(this.numElements, test.numElements) == 0;
    }

    public int hashCode(Object o) {
        return o.hashCode();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (Object e : c) {
            add((E) e);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Node<E>[] newTable = new Node[table.length];
        int i = 0;
        for (Object e : c) {
            if (contains(e)) {
                newTable[i] = (Node) e;
                i++;
            }
        }

        this.table = newTable;

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

        for (int i = 0; i < this.table.length; i++) {
            output += "row " + i + ": ";
            if (this.table[i] != null) {
                Node currentNode = this.table[i];
                while (currentNode != null) {
                    output += currentNode.element;
                    if (currentNode.next != null) {
                        output += "-->";
                    }
                    currentNode = currentNode.next;
                }
            }
            output += "\n";
        }

        return output;
    }

    private class HashSetWithChainingIterator implements Iterator<E> {

        private int index = -1;
        private Node<E>[] table;
        private int numElements;

        public HashSetWithChainingIterator(Node<E>[] table, int numElements) {
            this.table = table;
            this.numElements = numElements;
        }

        @Override
        public boolean hasNext() {
            return this.index < this.numElements;
        }

        @Override
        public E next() {
            return (E) this.table[++this.index];
        }

        public void reset() {
            this.index = 0;
        }
    }
}
