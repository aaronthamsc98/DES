package cs2030.simulator;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ArrivalEvent extends Event {

    private final String eventType;
    private final ArrayList<Server> serverList;

    public ArrivalEvent(Customer customer, double time, ArrayList<Double> restTimes,
                        ArrayList<Server> serverList) {
        super(customer, time, restTimes);
        this.eventType = "arrival";
        this.serverList = serverList;
    }

    public void addStatistics(Statistics statistic) {

    }

    public String getEventType() {
        return this.eventType;
    }


    public void nextEvent(ArrayList<Server> servers, PriorityQueue<Event> eventQ) {
        for (Server server: servers) {
            if (server.getServing() == false && server.getResting() == false) {
                eventQ.add(new ServeEvent(this.getCustomer(), this.getTime(), server,
                        this.getRestTimes(), this.serverList));
                return;
            }
        }

        Server lowest = this.serverList.get(0);
        for (Server s: servers) {
            if (lowest.getWaitQ().size() > s.getWaitQ().size()) {
                lowest = s;
            }
        }

        for (Server server: servers) {
            if (this.getCustomer().isGreedy()) {
                //System.out.println("LOWEST: " + lowest + " " + lowest.getWaitQ().size());
                if (lowest.waitQFull() == false) {
                    eventQ.add(new WaitEvent(this.getCustomer(), this.getTime(), lowest,
                            this.getRestTimes(),
                            this.serverList));
                    return;
                } else {
                    eventQ.add(new LeaveEvent(this.getCustomer(), this.getTime(),
                            this.getRestTimes()));
                    return;
                }

            } else {
                if (server.waitQFull() == false) {
                    eventQ.add(new WaitEvent(this.getCustomer(), this.getTime(), server,
                            this.getRestTimes(),
                            this.serverList));
                    return;
                }

            }
        }

        eventQ.add(new LeaveEvent(this.getCustomer(), this.getTime(), this.getRestTimes()));
        return;
    }
    
    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + this.getCustomer() + " arrives";
    }



}
