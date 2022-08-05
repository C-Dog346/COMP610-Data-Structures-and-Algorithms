/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab3_2;

import Lab3_2.LinkedSet;
import java.util.Collection;

/**
 *
 * @author Callum
 * @param <E>
 */
public class LinkedSortedSet<E extends Comparable<E>> extends LinkedSet<E>  {

    public LinkedSortedSet() {
        super();
    }
    
    public LinkedSortedSet(Collection<? extends E> c) {
        super(c);
    }
    
    @Override
    public boolean add(E o) {
        if (!(contains(o))) {
            Node<E> newNode = new Node<>(o);
            if (firstNode == null || o.compareTo(firstNode.element) <= 0) {
                newNode.next = firstNode;
                firstNode = newNode;
            }
            else {
                Node<E> currentNode = firstNode;
                while (currentNode.next != null && o.compareTo(currentNode.next.element) >= 0) {
                    currentNode = currentNode.next;
                }   
                newNode.next = currentNode.next;
                currentNode.next = newNode;
            }
            numElements++;
            return true;
        }
        else
            return false;
    }
    
    public static void main(String[] args) {
        LinkedSortedSet set = new LinkedSortedSet();
        
        set.add("yyyY");
        set.add("Aaaa");
        set.add("Bbbbb");
        set.add("Aaaaaa");
        set.add("aaaaa");
        set.add("Abe");
        set.add("AAe");
        set.add("zzz");
        System.out.println(set.toString());
    }
}