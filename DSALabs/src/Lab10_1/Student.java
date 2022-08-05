/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Lab10_1;

import java.util.Iterator;

/**
 *
 * @author callumclow
 */
public class Student {
    
    private String name;
    private String address;
    private String birthDate;
    private int ID;
    
    public Student(String name) {
        this.name = name;
    }
    
    public Student(String name, String address, String birthDate, int ID) {
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.ID = 1000+ID;
    }

    @Override
    public int hashCode() {
        int output = 0;
        for (char c: this.name.toCharArray()) {
            output += c;
        }
        return output;
    }
    
    @Override
    public String toString() {
        if (this.ID != 0)
            return this.name + " " + this.address + " " +
                this.birthDate + " " + this.ID;
        else
            return this.name;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashTable<Student> table = new HashTable<>();
        
        Student removeTest = new Student("Remove me please");
        Student removeTest2 = new Student("Lia");
        Student removeTest3 = new Student("Ldf");
        
        System.out.println(table.arraySize());
        table.add(new Student("Ian"));
        System.out.println(table.toString());
        table.add(new Student("Dan"));
        table.add(new Student("Tim"));
        table.add(new Student("Lou"));
        System.out.println(table.toString());
        table.add(new Student("Lee"));
        System.out.println(table.toString());
        table.add(new Student("Lee"));
        System.out.println(table.toString());
        table.add(new Student("Lee"));
        table.add(new Student("Keetharnraj"));
        table.add(removeTest);
        table.add(new Student("Gre"));
        table.add(new Student("Nie"));
        table.add(new Student("Ben"));
        table.add(new Student("Kid"));
        table.add(removeTest2);
        table.add(new Student("Abe"));
        table.add(new Student("Tom"));
        table.add(new Student("Mar")); 
        table.add(new Student("Jan")); 
        table.add(removeTest3);
        
        
        System.out.println(table.toString());
        
        System.out.println(table.contains(removeTest));
        table.remove(removeTest);
        table.remove(removeTest2);
        System.out.println(table.contains(removeTest));
        
        System.out.println(table.toString());
        System.out.println(table.arraySize());
        
//        Iterator it = table.iterator();
//        
//        while (it.hasNext())
//            System.out.println(it.next()); 
    }
}