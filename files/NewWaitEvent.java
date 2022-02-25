package cs2030.simulator;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class NewWaitEvent extends Event {

    private final Server server;
    private final String eventType;
    private final ArrayList<Server> serverList;

    public NewWaitEvent(Customer customer, double time, Server server, ArrayList<Double> restTimes,
                        ArrayList<Server> serverList) {
        super(customer, time, restTimes);
        this.server = server;
        this.eventType = "newwait";
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
            if (this.server.getResting() == false && this.server.getServing() == false) {
                eventQ.add(new ServeEvent(this.getCustomer(), this.server.getNextAvailTime(),
                        this.server, this.getRestTimes(),
                        this.serverList));
                return;
            } else {
                eventQ.add(new NewWaitEvent(this.getCustomer(),
                        this.server.getNextAvailTime(), this.server,
                        this.getRestTimes(), this.serverList));
                return;
            }
            //return;
        } else {
            ArrayList<Server> scoList = new ArrayList<Server>();
            for (Server sco : servers) {
                if (sco.isHuman() == false) {
                    scoList.add(sco);
                }
            }

            double lowest = this.server.getNextAvailTime();
            Server nextServer = this.server;

            for (Server s : scoList) {
                if (!this.server.getWaitQ().isEmpty() && s != this.server) {
                    if (this.server.getNextAvailTime() < s.getNextAvailTime()) {
                        if (this.server.getServing() == false) {
                            this.server.updateServe();
                            eventQ.add(new ServeEvent(this.server.getWaitQ().get(0),
                                    this.server.getNextAvailTime(),
                                    this.server,
                                    this.getRestTimes(), this.serverList));
                            return;
                        } else {
                            eventQ.add(new NewWaitEvent(this.getCustomer(),
                                    this.server.getNextAvailTime(),
                                    this.server,
                                    this.getRestTimes(), this.serverList));
                            return;
                        }
                    } else if (this.server.getNextAvailTime() > s.getNextAvailTime()) {
                        if (s.getServing() == false) {
                            s.updateServe();
                            eventQ.add(new ServeEvent(this.server.getWaitQ().get(0),
                                    s.getNextAvailTime(),
                                    s, this.getRestTimes(),
                                    this.serverList));
                            return;
                        } else {
                            eventQ.add(new NewWaitEvent(this.getCustomer(), s.getNextAvailTime(),
                                    s, this.getRestTimes(),
                                    this.serverList));
                            return;
                        }
                    } else {
                        continue;
                    }
                }
            }

        }
    }

}
