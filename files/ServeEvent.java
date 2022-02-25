package cs2030.simulator;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ServeEvent extends Event {
    private final Server server;
    private static final double double1 = 1.0;
    private final String eventType;
    private final ArrayList<Server> serverList;

    public ServeEvent(Customer customer, double time, Server server, ArrayList<Double> restTimes,
                      ArrayList<Server> serverList) {
        super(customer, time, restTimes);
        this.server = server;
        this.eventType = "serve";
        this.serverList = serverList;
    }

    public String getEventType() {
        return this.eventType;
    }

    public Server getServer() {
        return this.server;
    }

    public void addStatistics(Statistics statistic) {
        statistic.updateWaitTime(this.getTime() - this.getCustomer().getArrivalTime());
        statistic.updateDone(1);
    }

    public void nextEvent(ArrayList<Server> servers, PriorityQueue<Event> eventQ) {
        if (this.server.isHuman() == true) {
            if (this.server.getServing() == false && this.server.getResting() == false) {
                double finishTime = this.getTime() + this.getCustomer().getServiceTime();
                this.server.setNextAvailTime(finishTime);
                DoneEvent finishEvent = new DoneEvent(this.getCustomer(), finishTime,
                        this.server, this.getRestTimes());
                this.server.updateServe(); //set Serving = true
                //return finishEvent;
                eventQ.add(finishEvent);
                return;
            }

        } else {
            double endTime = this.getTime() + this.getCustomer().getServiceTime();
            this.server.updateServe();
            this.server.setNextAvailTime(endTime);
            if (!this.server.getWaitQ().isEmpty()) {
                this.server.clearQ();
            }
            eventQ.add(new DoneEvent(this.getCustomer(),
                    endTime, this.server, this.getRestTimes()));
            return;
        }
    }


    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + this.getCustomer()
                + " serves by " + this.server.getServerType()
                + " " + this.server.getServerID();
    }


}
