package cs2030.simulator;

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {

    private static final int negative = -1;
    private static final int positive = 1;

    public int compare(Event e1, Event e2) {
        if (e1.getTime() < e2.getTime()) {
            return negative;
        } else if (e1.getTime() > e2.getTime()) {
            return positive;
        } else if (e1.getCustomerID() < e2.getCustomerID()) {
            return negative;
        } else if (e1.getCustomerID() > e2.getCustomerID()) {
            return positive;
        }
        return negative;

    }

}
