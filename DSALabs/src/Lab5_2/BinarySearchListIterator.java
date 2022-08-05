/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5_2;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Callum
 * @param <E>
 */
public class BinarySearchListIterator<E extends Comparable> {
    
    private List<E> list;
    protected int lastSearch;
    
    public BinarySearchListIterator(List<E> list) {
        this.list = list;
    }
    
    public int binarySearch(E target) {
        lastSearch = 0;
        if (target == null)
            throw new NullPointerException("search target is null");

        return search(target, 0, list.size());
    }
    
    private int search(E target, int start, int end) {
        if (start >= end)
            return -start-1;
        else {
            ListIterator iterator = list.listIterator(start);
            int midpoint = (start+end)/2; // midpoint of search region
            for (int i = start; i < midpoint; i++) {
                lastSearch++;
                iterator.next();
            }
            int comparison = target.compareTo(iterator.next());
            if (comparison == 0)
                return midpoint;
            else if (comparison < 0)
                return search(target, start, midpoint);
            else // comparison > 0
                return search(target, midpoint+1, end);
        }
    }

    public static void main(String[] args) {
        LinkedList listInt = new LinkedList();
        LinkedList listString = new LinkedList();
        
        for (int i = 1; i <= 25; i++)
            listInt.add(i);
        
        listInt.add(60);

        listString.add("a");
        listString.add("b");
        listString.add("cat");
        listString.add("dog");
        listString.add("e");
        listString.add("fish");
        listString.add("g");
        listString.add("horse");
        
        BinarySearchListIterator BLSIInt = new BinarySearchListIterator(listInt);
        BinarySearchListIterator BLSIString = new BinarySearchListIterator(listString);
        
        System.out.println(BLSIInt.binarySearch(22));
        System.out.println("Last search: " + BLSIInt.lastSearch);
        System.out.println(BLSIInt.binarySearch(60));
        System.out.println("Last search: " + BLSIInt.lastSearch);
        System.out.println(BLSIInt.binarySearch(1));
        System.out.println("Last search: " + BLSIInt.lastSearch);
        System.out.println(BLSIString.binarySearch("c"));
        System.out.println("Last search: " + BLSIString.lastSearch);
        System.out.println(BLSIString.binarySearch("fish"));
        System.out.println("Last search: " + BLSIString.lastSearch);
        System.out.println(BLSIString.binarySearch("cat"));
        System.out.println("Last search: " + BLSIString.lastSearch);
    }
}
