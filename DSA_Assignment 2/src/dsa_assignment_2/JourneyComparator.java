/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa_assignment_2;

import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.Comparator;

/**
 *
 * @author Callum
 */
public class JourneyComparator implements Comparator<BusJourney> {

    @Override
    public int compare(BusJourney o1, BusJourney o2) {
        return (int) o1.getDestinationTime().until(o2.getDestinationTime(), MINUTES);
    }
    
}
