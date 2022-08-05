/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa.assignment.pkg1;

import java.util.Collection;

/**
 *
 * @author Callum
 */
public class LinkRetainRemoveSet<E extends Comparable<E>> extends LinkedSet<E> {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("==============REMOVE==============\n\n");
        LinkRetainRemoveSet removeSet1 = new LinkRetainRemoveSet();
        LinkRetainRemoveSet removeSet2 = new LinkRetainRemoveSet();
        LinkRetainRemoveSet removeSet3 = new LinkRetainRemoveSet();
        LinkRetainRemoveSet removeSet4 = new LinkRetainRemoveSet();     
        
        removeSet1.add('J');
        removeSet1.add('H');
        removeSet1.add('B');
        removeSet1.add('M');
        removeSet1.add('M');
        
        removeSet2.add(1);
        removeSet2.add(7);
        removeSet2.add(2);
        removeSet2.add(6);
        removeSet2.add(3);
        removeSet2.add(5);
        removeSet2.add(4);
        
        removeSet3.add(1);
        removeSet3.add(7);
        removeSet3.add(2);
        removeSet3.add(6);
        removeSet3.add(3);
        removeSet3.add(5);
        removeSet3.add(4);
        
        removeSet4.add(1);
        removeSet4.add(7);
        removeSet4.add(2);
        removeSet4.add(6);
        removeSet4.add(3);
        removeSet4.add(5);
        removeSet4.add(4);

        System.out.println("Test 1");
        System.out.println("Set: " + removeSet1);
        
        try {
            System.out.println("Remove set 1 returned: " + removeSet1.remove(null, null));
        }
        catch (Exception RuntimeException) {
            System.err.println("Remove set 1 returned: " + RuntimeException);
        }
        
        System.out.println("Set: " + removeSet1 + "\n");
        
        System.out.println("Test 3");
        System.out.println("Set: " + removeSet2);
        
        try {
            System.out.println("Remove set 2 returned: " + removeSet2.remove(2, 6));
        }
        catch (Exception RuntimeException) {
            System.err.println("Remove set 2 returned: " + RuntimeException);
        }
        
        System.out.println("Set: " + removeSet2 + "\n");
        
        System.out.println("Test 4");
        System.out.println("Set: " + removeSet3);
        
        try {
            System.out.println("Remove set 3 returned: " + removeSet3.remove(4, 5));
        }
        catch (Exception RuntimeException) {
            System.err.println("Remove set 3 returned: " + RuntimeException);
        }
        
        // will return a set with 7 - the example does not but I belive that the example should
        System.out.println("Set: " + removeSet3 + "\n"); 
        
        System.out.println("Test 8");
        System.out.println("Set: " + removeSet4);
        
        try {
            System.out.println("Remove set 4 returned: " + removeSet4.remove(4, null));
        }
        catch (Exception RuntimeException) {
            System.err.println("Remove set 4 returned: " + RuntimeException);
        }
        
        System.out.println("Set: " + removeSet4 + "\n");
        
        System.out.println("\n\n==============RETAIN==============\n\n");
        
        LinkRetainRemoveSet retainSet1 = new LinkRetainRemoveSet();
        LinkRetainRemoveSet retainSet2 = new LinkRetainRemoveSet();
        LinkRetainRemoveSet retainSet3 = new LinkRetainRemoveSet();
        LinkRetainRemoveSet retainSet4 = new LinkRetainRemoveSet();
        
        retainSet1.add(1);
        retainSet1.add(7);
        retainSet1.add(2);
        retainSet1.add(6);
        retainSet1.add(3);
        retainSet1.add(5);
        retainSet1.add(4);
        
        retainSet2.add(1);
        retainSet2.add(7);
        retainSet2.add(2);
        retainSet2.add(6);
        retainSet2.add(3);
        retainSet2.add(5);
        retainSet2.add(4);
        
        retainSet3.add(1);
        retainSet3.add(7);
        retainSet3.add(2);
        retainSet3.add(6);
        retainSet3.add(3);
        retainSet3.add(5);
        retainSet3.add(4);
        
        retainSet4.add(1);
        retainSet4.add(7);
        retainSet4.add(2);
        retainSet4.add(6);
        retainSet4.add(3);
        retainSet4.add(5);
        retainSet4.add(4);
        
        System.out.println("Test 2");
        System.out.println("Set: " + retainSet1);
        
        try {
            System.out.println("Retain returned: " + retainSet1.retain(2, 6));
        }
        catch (Exception RuntimeException) {
            System.err.println("Retain returned: " + RuntimeException);
        }
        
        System.out.println("Set: " + retainSet1  + "\n");
        
        System.out.println("Test 5");
        System.out.println("Set: " + retainSet2);
        
        try {
            System.out.println("Retain returned: " + retainSet2.retain(6, 7));
        }
        catch (Exception RuntimeException) {
            System.err.println("Retain returned: " + RuntimeException);
        }
        
        System.out.println("Set: " + retainSet2  + "\n");
        
        System.out.println("Test 6");
        System.out.println("Set: " + retainSet3);
        
        try {
            System.out.println("Retain returned: " + retainSet3.retain(null, 4));
        }
        catch (Exception RuntimeException) {
            System.err.println("Retain returned: " + RuntimeException);
        }
        
        System.out.println("Set: " + retainSet3  + "\n");
        
        System.out.println("Test 7");
        System.out.println("Set: " + retainSet4);
        
        try {
            System.out.println("Retain returned: " + retainSet4.retain(4, null));
        }
        catch (Exception RuntimeException) {
            System.err.println("Retain returned: " + RuntimeException );
        }
        
        System.out.println("Set: " + retainSet4  + "\n");       
    }
    
    public LinkRetainRemoveSet() {
        super();
    }
    
    public LinkRetainRemoveSet(Collection<? extends E> c) {
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
    
    public LinkRetainRemoveSet<E> retain(E start, E end) {
        LinkRetainRemoveSet<E> retainSet = remove(start, end);
        Node<E> tempNode = firstNode;
        firstNode = retainSet.firstNode;
        retainSet.firstNode = tempNode;
        
        return retainSet;
    }
    
    public LinkRetainRemoveSet<E> remove(E start, E end) {  
        LinkRetainRemoveSet<E> removeSet = new LinkRetainRemoveSet<>();

        Node<E> previousNode = firstNode;
        Node<E> currentNode = firstNode.next;
        
        if(!contains(start) && start != null)
            throw new RuntimeException("ERROR: One or more elements not in set!");
        else if (!contains(end) && end != null)
                throw new RuntimeException("ERROR: One or more elements not in set!");           
        
        if (start != null && start != previousNode.element)
            while (currentNode != null && start.compareTo(currentNode.element) > 0) {
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
        else {
            removeSet.add(previousNode.element);
        }
        
        if (end != null) {   
            while (currentNode != null && end.compareTo(currentNode.element) > 0) {
                removeSet.add(currentNode.element);
                currentNode = currentNode.next;
                numElements--;
            }
            if (start == null || start == previousNode.element)
                firstNode = currentNode;

        }
        
        else {
            if (start == null)
                removeSet.add(previousNode.element);
            while (currentNode != null){
                removeSet.add(currentNode.element);
                currentNode = currentNode.next;
                numElements--;
            }
            if (start == null || start == firstNode.element)
                firstNode = null;
        }
            
        previousNode.next = currentNode;
        
        return removeSet;
    }
}
