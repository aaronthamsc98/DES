package cs2030.simulator;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class RestDoneEvent extends Event {

    private final Server server;
    private final String eventType;

    public RestDoneEvent(Customer customer, double time,
                         Server server, ArrayList<Double> restTimes) {
        super(customer, time, restTimes);
        this.server = server;
        this.eventType = "restdone";
    }

    public String getEventType() {
        return this.eventType;
    }

    public Server getServer() {
        return this.server;
    }

    public void addStatistics(Statistics statistic) {

    }

    public void nextEvent(ArrayList<Server> servers, PriorityQueue<Event> eventQ) {
        this.server.clearRest(); //Set Rest to back false, finish resting
        if (!this.server.getWaitQ().isEmpty()) {
            this.server.getWaitQ().remove(0);
        }
        this.server.clearServe(); //Make Server able to Serve again
        return;
    }

    @Override
    public String toString() {
        return "";
    }


}
