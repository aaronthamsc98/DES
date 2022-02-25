package cs2030.simulator;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class DummyEvent extends Event {

    private final String eventType;

    public DummyEvent(Customer customer, double time, ArrayList<Double> restTimes) {
        super(customer, time, restTimes);
        this.eventType = "dummy";
    }

    public String getEventType() {
        return this.eventType;
    }

    public void addStatistics(Statistics statistic) {

    }

    public void nextEvent(ArrayList<Server> servers, PriorityQueue<Event> eventQ) {
        return;
    }


}
