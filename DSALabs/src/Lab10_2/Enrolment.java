/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab10_2;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author callumclow
 */
public class Enrolment{
    
    private Map<Integer, Student> roll;
    private int numStudents;
    
    public Enrolment() {
        this.roll = new LinkedHashMap<>();
        this.numStudents = 0;
    }
    
    public Student find(int ID) {
        
        
        return this.roll.get(ID);
    }
    
    
    public static void main(String[] args) {
        Enrolment roll = new Enrolment();
        
        roll.add(new Student("Bob", "123 Place Street", "3/11/2007"
                , roll.getNumStudents()+1));
        roll.add(new Student("Jane", "1 Street Place", "11/5/2007"
                , roll.getNumStudents()+1));
        roll.add(new Student("Min", "445 Place Street", "29/9/2007"
                , roll.getNumStudents()+1));
        
        System.out.println(roll.find(1001));
        System.out.println(roll.find(1003));
        System.out.println(roll.find(1004));

        System.out.println(roll.getRoll()); // feature for listing all the 
                                            // students in the order in which 
                                            // they enrolled.
    }

    /**
     * @return the roll
     */
   

    /**
     * @return the numStudents
     */
    public int getNumStudents() {
        return numStudents;
    }

    /**
     * @param numStudents the numStudents to set
     */
    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    private void add(Student student) {
        this.getRoll().put(++numStudents+1000, student);
    }

    /**
     * @return the roll
     */
    public Map<Integer, Student> getRoll() {
        return roll;
    }

    /**
     * @param roll the roll to set
     */
    public void setRoll(Map<Integer, Student> roll) {
        this.roll = roll;
    }
    
}