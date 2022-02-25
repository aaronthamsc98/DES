package cs2030.simulator;

import java.util.ArrayList;
import java.util.PriorityQueue;

public abstract class Event {

    private final Customer customer;
    private final double time;
    private final ArrayList<Double> restTimes;
    private static final int dummyNo = 999;

    public Event(Customer customer, double time, ArrayList<Double> restTimes) {
        this.customer = customer;
        this.time = time;
        this.restTimes = restTimes;
    }

    public Server getServer() {
        return new Server(dummyNo);
    }

    public double getTime() {
        return this.time;
    }

    public abstract void addStatistics(Statistics statistic);

    public abstract void nextEvent(ArrayList<Server> servers, PriorityQueue<Event> eventQ);

    public abstract String getEventType();

    public Customer getCustomer() {
        return this.customer;
    }

    public int getCustomerID() {
        return this.customer.getCustomerID();
    }

    public ArrayList<Double> getRestTimes() {
        return this.restTimes;
    }


}
