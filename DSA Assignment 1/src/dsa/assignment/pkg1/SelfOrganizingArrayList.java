/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa.assignment.pkg1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;



/**
 *
 * @author Callum
 */
public class SelfOrganizingArrayList<E> implements List<E> {
    
    public static void main(String[] args) {
        SelfOrganizingArrayList list = new SelfOrganizingArrayList();
        
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(12);
        list.add(null);
        list.add(2);
        list.add(":)");
        list.add(":)");
        list.add(":)");
        list.add(":)");
        list.add(":)");
        list.add(":)");
        list.add(":)");
        list.add(":)");
        list.add(":)");
        list.add(":)");
        list.add(":)");
        list.add(":)");
        list.add('x');
        
        System.out.println(list);
        
        System.out.println("contains 5: " + list.contains(5));
        System.out.println(list);
        System.out.println("indexOf value 7: " + list.indexOf(7)); // value = 7, index = 6
        System.out.println(list);
        System.out.println("contains null: " + list.contains(null));
        System.out.println("size: " + (list));
        Integer removeInt = 6;
        System.out.println(list.size());
        System.out.println("remove 6: " + list.remove(removeInt)); // remove value
        System.out.println("size: " + (list));
        System.out.println("remove index 3: " + list.remove(3)); // remove index
        System.out.println("remove :): " + list.remove(":)")); // remove value
        System.out.println("indexOf value x: " + list.indexOf('x')); // value = 7, index = 6
        
        System.out.println(list);
        System.out.println(list.size());
    }
    
    private Element<E>[] organizingArrayList;
    private int size;
    
    public SelfOrganizingArrayList() {
        this.organizingArrayList = new Element[20];
        this.size = 0;
    }

    private void expandCapacity() {
        this.organizingArrayList = Arrays.copyOf(this.organizingArrayList, this.organizingArrayList.length*2);
    }
    
    private class Element<E> {
        private E value;
        private int counter;
        
        private Element(E value) {
            this.value = value;
            this.counter = 1;
        }
    }
    
    public String toString() {
        String output = "";
        
        output += "[" + this.organizingArrayList[0].value + "(" + this.organizingArrayList[0].counter + ")";
        
        for (int i = 1; i < this.size; i++) {
            output += ", " + this.organizingArrayList[i].value + "(" + this.organizingArrayList[i].counter + ")";
        }
        output += "]";
        
        return output;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public boolean add(E e) {
        if (this.size+1 == this.organizingArrayList.length)
            expandCapacity();
        
        this.organizingArrayList[this.size++] = new Element(e);
        return true;
        /*
        if(this.size == 0)
            this.array[0] = new Element(e);
        else {
            for (int i = 0; i < this.size; i++) {
                if (this.array[i].value.equals(e))
                    if
                    i = this.size;
            }
        }*/
    }
    
    /**
     * @return the array
     */
    
    public Element<E>[] getOrganizingArrayList() {
        return this.organizingArrayList;
    }

    /**
     * @param organizingArrayList the array to set
     */
    public void setOrganizingArrayList(Element<E>[] organizingArrayList) {
        this.organizingArrayList = organizingArrayList;
    }

    @Override
    public boolean remove(Object o) {
        String output = "";
        
        if(contains(o)) {
            if (o == null) {
                for (int i = 0; i < this.size; i++)
                    if (organizingArrayList[i].value == null) {
                        for (int j = indexOf(organizingArrayList[i]); j < this.size;j++ ) {
                            this.organizingArrayList[i].value = this.organizingArrayList[i+1].value;
                            i++;
                        }
                        this.organizingArrayList[i].counter--;
                    }
            } 
            else {
                for (int i = 0; i < this.size; i++)
                    if (o.equals(this.organizingArrayList[i].value)) {
                        for (int j = indexOf(this.organizingArrayList[i].value); j < this.size;j++ ) {
                            this.organizingArrayList[j] = this.organizingArrayList[j+1];
                        }
                        size--;
                        i = this.size;
                    }
            }
            
            return true;
        }
        else
            return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++)
            this.organizingArrayList[i] = null;

        this.size = 0;
    }

    @Override
    public E get(int index) {
        return this.organizingArrayList[index].value;
    } 

    @Override
    public E remove(int index) {
        E x = null;
        if(contains(get(index))) {
            for (int i = 0; i < this.size; i++)
                if (i == index) {
                    x = this.organizingArrayList[index].value;
                    for (int j = indexOf(this.organizingArrayList[i].value); j < this.size;j++ ) {
                        this.organizingArrayList[j] = this.organizingArrayList[j+1];
                    }
                    this.size--;
                    i = this.size;
                }
            
            return (E) x;
        }            
        else
            return null;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < this.size; i++)
                if (this.organizingArrayList[i].value == null) {
                    this.organizingArrayList[i].counter++;
                    return i;
                }
        } else {
            for (int i = 0; i < this.size; i++)
                if (o.equals(this.organizingArrayList[i].value)) {
                    this.organizingArrayList[i].counter++;
                    return i;
                }
            }
        
        return -1;
    }
    

    
    // Unsupported Operations

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //this.organizingArrayList(5);
        //return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
    
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
