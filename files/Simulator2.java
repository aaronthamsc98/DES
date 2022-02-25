package cs2030.simulator;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Simulator2 {
    private final int numServer;
    private final int queueLength;
    private final ArrayList<Double> arrivalTimes;
    private final ArrayList<Double> serviceTimes;

    public Simulator2(int numServer, ArrayList<Double> arrivalTimes,
                      ArrayList<Double> serviceTimes, int queueLength) {
        this.arrivalTimes = arrivalTimes;
        this.numServer = numServer;
        this.queueLength = queueLength;
        this.serviceTimes = serviceTimes;
    }

    public void simulate() {
        PriorityQueue<Event> eventQ = new PriorityQueue<Event>(new EventComparator());
        ArrayList<Server> serverList = new ArrayList<Server>();
        Statistics statistic = new Statistics();
        ArrayList<Double> restTimes = new ArrayList<Double>();

        for (int i = 0; i < this.numServer; i++) {
            serverList.add(new Server(i + 1, queueLength));
        }

        for (int i = 0; i < this.arrivalTimes.size(); i++) {
            Customer customer = new Customer(i + 1, this.arrivalTimes.get(i),
                    this.serviceTimes.get(i));
            ArrivalEvent event = new ArrivalEvent(customer, customer.getArrivalTime(),
                    restTimes, serverList);
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
