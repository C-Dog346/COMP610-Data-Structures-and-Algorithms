/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa_assignment_2;

import java.util.Iterator;

/**
 *
 * @author Callum
 */
public class HashSetWithChainingTest {

    public static void main(String[] args) {
        HashSetWithChaining<String> set = new HashSetWithChaining<>();

        System.out.println("Creating Set, initial capacity = " + set.getArraySize()
                + "... Adding Seth, Bob, Adam, Ian");
        set.add("Seth");
        set.add("Bob");
        set.add("Adam");
        set.add("Ian");

        System.out.println(set.toString());

        System.out.println("Size is: " + set.size());

        System.out.println("Adding Jill, Amy, Nat, Seth, Bob, Simon");
        set.add("Jill");
        set.add("Amy");
        set.add("Nat");
        set.add("Seth");
        set.add("Bob");
        set.add("Simon");

        System.out.println(set.toString());

        System.out.println("Contains Seth? " + set.contains("Seth"));
        System.out.println("Contains Nat? " + set.contains("Nat"));
        System.out.println("Contains Gary? " + set.contains("Gary"));

        Iterator it = set.iterator();
        System.out.print("Iterating! ");
        while (it.hasNext()) {
            Object s = it.next();
            if (s != null) {
                System.out.print(s);
            }
        }

        System.out.println("\nREMOVING Seth, Adam, Bob");
        set.remove("Seth");
        set.remove("Bob");
        set.remove("Nat");
        System.out.println("Size is: " + set.size());

        //System.out.println(set.toString());
        System.out.println("Adding Mary, George, Serena, Jack, Waldo, "
                + "Hubert Blaine Wolfeschlegelsteinhausenbergerdorff Sr.");
        set.add("Mary");
        set.add("George");
        set.add("Middle Test");
        set.add("Serena");
        set.add("Jack");
        set.add("Waldo");
        set.add("Wolfeschlegelsteinhausenbergerdorff");

        System.out.println(set.toString());

        System.out.println("\nREMOVING Middle Test");
        set.remove("Middle Test");
        System.out.println("Size is: " + set.size());

        System.out.println(set.toString());
        System.out.println("Testing equals method");

        System.out.println("Test 1: " + set.equals(set));
        System.out.println("Test 2: " + set.equals(new HashSetWithChaining()));
        System.out.println("Comparing HashCodes");
        System.out.println("Hashcode Naive: " + set.hashCode());
        System.out.println("Hashcode Non-Native: " + set.hashCode(set));
    }

}
