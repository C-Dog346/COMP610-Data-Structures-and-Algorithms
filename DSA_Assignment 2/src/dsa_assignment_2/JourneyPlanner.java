/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa_assignment_2;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Callum
 */
public class JourneyPlanner {

    Map<String, Set<BusTrip>> locationMap;

    public JourneyPlanner() {
        this.locationMap = new HashMap();
    }

    public boolean add(BusTrip bus) {
        if (this.locationMap.containsKey(bus.getDepartLocation())) {
            Set<BusTrip> newSet = locationMap.get(bus.getDepartLocation());
            newSet.add(bus);
            this.locationMap.put(bus.getDepartLocation(), newSet);

            return true;
        }
        else {
            Set<BusTrip> set = new HashSet<>();
            set.add(bus);
            this.locationMap.put(bus.getDepartLocation(), set);

            return true;
        }
    }

    public List<BusJourney> getPossibleJourneys(String startLocation, LocalTime startTime,
            String endLocation, LocalTime endTime) {
        List<BusJourney> journeyList = new ArrayList<>();

        findPaths(startLocation, startTime, endLocation, endTime, new BusJourney(), journeyList);

        return journeyList;
    }

    private void findPaths(String currentLocation, LocalTime currentTime, String endLocation,
            LocalTime endTime, BusJourney currentJourney, List<BusJourney> journeyList) {

        for (BusTrip b : locationMap.get(currentLocation)) {
            if (currentJourney.addBus(b)) {
                if (endLocation.equals(b.getArrivalLocation())) {
                    journeyList.add(currentJourney.cloneJourney());
                }
                findPaths(b.getArrivalLocation(), b.getArrivalTime(), endLocation, endTime, currentJourney, journeyList);
                currentJourney.removeLastTrip();
            }
        }
    }
}
