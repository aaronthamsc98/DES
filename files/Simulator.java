package cs2030.simulator;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Simulator {

    private final int numServer;
    private final ArrayList<Double> arrivalTimes;
    
    public Simulator(int numServer, ArrayList<Double> arrivalTimes) {
        this.arrivalTimes = arrivalTimes;
        this.numServer = numServer;
    }

    public void simulate() {
        PriorityQueue<Event> eventQ = new PriorityQueue<Event>(new EventComparator());
        ArrayList<Server> serverList = new ArrayList<Server>();
        ArrayList<Double> restTimes = new ArrayList<Double>();
        Statistics statistic = new Statistics();
        //System.out.println(statistic);

        for (int i = 0; i < this.numServer; i++) {
            serverList.add(new Server(i + 1));
        }

        for (int i = 0; i < this.arrivalTimes.size(); i++) {
            Customer customer = new Customer(i + 1, this.arrivalTimes.get(i));
            ArrivalEvent event = new ArrivalEvent(customer,
                    customer.getArrivalTime(), restTimes, serverList);
            eventQ.add(event);
        }
        
        while (!eventQ.isEmpty()) {
            Event event1 = eventQ.poll();

            if (!event1.getEventType().equals("restdone")
                    && !event1.getEventType().equals("rest")
                    && !event1.getEventType().equals("newwait")) {
                System.out.println(event1);
            }
            event1.nextEvent(serverList, eventQ);
            event1.addStatistics(statistic);
        }
        System.out.println("[" + statistic + "]");

    }
    
}
