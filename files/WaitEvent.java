package cs2030.simulator;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.PriorityQueue;

public class WaitEvent extends Event {
    
    private final Server server;
    private final String eventType;
    private final ArrayList<Server> serverList;

    public WaitEvent(Customer customer, double time, Server server,
                     ArrayList<Double> restTimes, ArrayList<Server> serverList) {
        super(customer, time, restTimes);
        this.server = server;
        this.eventType = "wait";
        this.serverList = serverList;
    }

    public String getEventType() {
        return this.eventType;
    }

    public void addStatistics(Statistics statistic) {

    }

    public Server getServer() {
        return this.server;
    }

    public void nextEvent(ArrayList<Server> servers, PriorityQueue<Event> eventQ) {
        if (this.server.isHuman()) {
            double output = this.server.getNextAvailTime();
            this.server.updateWaitQ(this.getCustomer());
            if (this.server.getResting() == false && this.server.getServing() == false) {
                ServeEvent newEvent = new ServeEvent(this.getCustomer(),
                        this.server.getNextAvailTime(),
                        this.server, this.getRestTimes(),
                        this.serverList);
                eventQ.add(newEvent);
                return;
            } else {
                eventQ.add(new NewWaitEvent(this.getCustomer(), this.server.getNextAvailTime(),
                        this.server, this.getRestTimes(),
                        this.serverList));
                return;
            }

        } else {
            //FOR SCO
            ArrayList<Server> scoList = new ArrayList<>();
            for (Server s: this.serverList) {
                if (!s.isHuman()) {
                    scoList.add(s);
                }
            }
            //System.out.println(SCOList);
            this.server.updateWaitQ(this.getCustomer());

            double lowest = this.server.getNextAvailTime();
            Server nextServer = this.server;
            for (Server sco : scoList) {
                if (lowest > sco.getNextAvailTime()) {
                    lowest = sco.getNextAvailTime();
                    nextServer = sco;
                }
            }

            if (!this.server.getWaitQ().isEmpty()) {
                if (this.server.getNextAvailTime() > lowest) {
                    //System.out.println("IN: " + this.getCustomer());
                    eventQ.add(new NewWaitEvent(this.getCustomer(), lowest, nextServer,
                            this.getRestTimes(), this.serverList));
                    return;
                } else {
                    eventQ.add(new NewWaitEvent(this.getCustomer(),
                            this.server.getNextAvailTime(),
                            this.server, this.getRestTimes(), this.serverList));
                    return;
                }
            }
        }
    }


    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + this.getCustomer()
                + " waits at " + this.server.getServerType()
                + " " + this.server.getServerID();
    }


}
