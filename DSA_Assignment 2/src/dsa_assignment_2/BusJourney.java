/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa_assignment_2;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Callum
 */
public class BusJourney {

    private List<BusTrip> busList;

    public BusJourney() {
        this.busList = new ArrayList();
    }

    public BusJourney(List<BusTrip> list) {
        this.busList = list;
    }

    public boolean addBus(BusTrip bus) {
        if (this.busList.isEmpty())
            return this.busList.add(bus);
        
        if (!this.busList.get(this.busList.size()-1).getArrivalLocation().equals(bus.getDepartLocation()))
            return false;
        if (!this.busList.get(this.busList.size()-1).getArrivalTime().isBefore(bus.getDepartTime()))
            return false;
        if (!containsLocation(bus.getArrivalLocation())) {
            return this.busList.add(bus);
        }
            
        return false;
    }

    public boolean removeLastTrip() {
        if (!this.busList.isEmpty())
            this.busList.remove(this.busList.size() - 1);
        else
            return false;
    
        return true;
    }

    public boolean containsLocation(String location) {
        for (int i = 0; i < this.busList.size(); i++) {
            if (this.busList.get(i).getDepartLocation().equals(location)) {
                return true;
            }
        }

        return this.busList.get(this.busList.size() - 1).getArrivalLocation().equals(location);
    }

    public String getOrigin() {
        return this.busList.get(0).getDepartLocation();
    }

    public String getDestination() {
        return this.busList.get(busList.size() - 1).getArrivalLocation();
    }

    public LocalTime getOriginTime() {
        return this.busList.get(0).getDepartTime();
    }

    public LocalTime getDestinationTime() {
        return this.busList.get(this.busList.size()-1).getArrivalTime();
    }

    public BusJourney cloneJourney() {
        return new BusJourney(this.busList);
    }

    public int getNumberOfBusTrips() {
        return busList.size();
    }

    public double getTotalCost() {
        double totalCost = 0.0;

        for (int i = 0; i < this.busList.size(); i++) {
            totalCost += this.busList.get(i).getCost();
        }

        return totalCost;
    }

    public String toString() {
        String output = "";

        for (int i = 0; i < this.busList.size(); i++) {
            output += this.busList.get(i).toString() + " " + this.busList.get(i).getCost() + "\n";
        }

        return output;
    }
}
