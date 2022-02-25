package cs2030.simulator;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.function.Supplier;

import cs2030.simulator.RandomGenerator;

public class Simulator5 {
    private final int seed;
    private final int numServer;
    private final int numSCO;
    private final int queueLength;
    private final int numOfCustomers;
    private final double arrivalRate;
    private final double serviceRate;
    private final double restRate;
    private final double probRest;
    private final double probGreedy;

    public Simulator5(int seed, int numServer, int numSCO,
                      int queueLength, int numOfCustomers, double arrivalRate,
                      double serviceRate, double restRate, double probRest,
                      double probGreedy) {
        this.seed = seed;
        this.numServer = numServer;
        this.numSCO = numSCO;
        this.queueLength = queueLength;
        this.numOfCustomers = numOfCustomers;
        this.arrivalRate = arrivalRate;
        this.serviceRate = serviceRate;
        this.restRate = restRate;
        this.probRest = probRest;
        this.probGreedy = probGreedy;
    }


    public void simulate() {
        RandomGenerator rng = new RandomGenerator(seed, arrivalRate, serviceRate, restRate);
        Supplier<Double> rngService = () -> rng.genServiceTime();
        PriorityQueue<Event> eventQ = new PriorityQueue<Event>(new EventComparator());
        Statistics statistic = new Statistics();
        ArrayList<Server> serverList = new ArrayList<Server>();
        ArrayList<Double> restTimes = new ArrayList<Double>();

        for (int i = 0; i < this.numServer; i++) {
            serverList.add(new Server(i + 1, queueLength));
        }

        for (int i = 0; i < this.numSCO; i++) {
            serverList.add(new SelfCheckout(numServer + i + 1, queueLength));
        }

        int custID = 0;
        double arrival = 0;
        for (int i = 0; i < numOfCustomers; i++) {
            double rngArrival = rng.genInterArrivalTime();
            double rngGreedy = rng.genCustomerType();
            double rngRestRate = rng.genRandomRest();

            custID++;
            Customer customer = new Customer(custID, arrival, rngService);
            if (rngGreedy < this.probGreedy) {
                customer = new GreedyCustomer(custID, arrival, rngService);
            }

            arrival += rngArrival;
            double restingTime = 0.0;
            if (rngRestRate < this.probRest) {
                restingTime = rng.genRestPeriod();
            }
            restTimes.add(restingTime);
            ArrivalEvent event = new ArrivalEvent(customer, customer.getArrivalTime(),
                    restTimes, serverList);
            eventQ.add(event);
        }


        while (!eventQ.isEmpty()) {
            Event event1 = eventQ.poll();
            if (!event1.getEventType().equals("restdone")
                    && !event1.getEventType().equals("rest")
                    && !event1.getEventType().equals("newwait")
                    && !event1.getEventType().equals("dummy")) {
                System.out.println(event1);
            }

            event1.nextEvent(serverList, eventQ);
            event1.addStatistics(statistic);
        }
        System.out.println("[" + statistic + "]");


    }

}
