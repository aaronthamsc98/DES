package cs2030.simulator;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class DoneEvent extends Event {

    private final Server server;
    private final String eventType;


    public DoneEvent(Customer customer, double time, Server server, ArrayList<Double> restTimes) {
        super(customer, time, restTimes);
        this.server = server;
        this.eventType = "done";
    }

    public String getEventType() {
        return this.eventType;
    }

    public void addStatistics(Statistics statistic) {
    
    }

    public void nextEvent(ArrayList<Server> servers, PriorityQueue<Event> eventQ) {
        if (this.server.isHuman() == true) {
            this.server.clearServe(); //set Serving = false
            eventQ.add(new RestEvent(this.getCustomer(), this.getTime(),
                    this.server, this.getRestTimes()));
            return;
        } else {
            //FOR SCO
            this.server.clearServe();
            this.server.clearIncoming();
            return;

        }
    }

    public Server getServer() {
        return this.server;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + this.getCustomer()
                + " done serving by " + this.server.getServerType()
                + " " + this.server.getServerID();
    }


}
