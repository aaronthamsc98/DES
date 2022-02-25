package cs2030.simulator;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class LeaveEvent extends Event {

    private final String eventType;

    public LeaveEvent(Customer customer, double time, ArrayList<Double> restTimes) {
        super(customer, time, restTimes);
        this.eventType = "leave";
    }

    public String getEventType() {
        return this.eventType;
    }

    public void addStatistics(Statistics statistic) {
        statistic.updateLeft(1);
    }

    public void nextEvent(ArrayList<Server> servers, PriorityQueue<Event> eventQ) {
        return;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " +
                this.getCustomer().toString() + " leaves";
    }

}
