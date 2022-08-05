/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab9_2;

import java.util.Comparator;

/**
 *
 * @author callumclow
 */
public class PriorityComparator implements Comparator<Task> {
    
    @Override
    public int compare(Task o1, Task o2) {
        return o1.getPriority() - o2.getPriority();
    }
    
}