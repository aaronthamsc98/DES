package cs2030.simulator;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class RestEvent extends Event {

    private final Server server;
    private final String eventType;

    public RestEvent(Customer customer, double time, Server server, ArrayList<Double> restTimes) {
        super(customer, time, restTimes);
        this.server = server;
        this.eventType = "rest";
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
        //System.out.println(this.server.getRestTimes());
        this.server.updateRest();
        double readyTime = 0.0;
        if (!this.getRestTimes().isEmpty()) {
            readyTime += this.getRestTimes().get(0);
        }
        //System.out.println(this.getRestTimes());
        if (!this.getRestTimes().isEmpty()) {
            this.getRestTimes().remove(0);
        }
        //System.out.println(this.getRestTimes());
        double finalTime = readyTime + this.getTime();

        this.server.setNextAvailTime(finalTime);

        eventQ.add(new RestDoneEvent(this.getCustomer(), this.server.getNextAvailTime(),
                this.server, this.getRestTimes()));
        return;
    }

    @Override
    public String toString() {
        //return "server: " + this.server.getServerID() + " resting";
        return "";
    }



}
