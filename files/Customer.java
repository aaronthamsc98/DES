package cs2030.simulator;

import java.util.function.Supplier;

public class Customer {
    private final int customerID;
    private final double arrivalTime;
    private final double serviceTime;
    private final boolean greedy;
    private final Supplier<Double> rng;
    private static final int dummyNo = -100;

    public Customer(int customerID, double arrivalTime) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = 1.0;
        this.greedy = false;
        this.rng = new Supplier<Double>() {
            @Override
            public Double get() {
                return 0.0;
            }
        };
    }

    public Customer(int customerID, double arrivalTime, double serviceTime) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.greedy = false;
        this.rng = new Supplier<Double>() {
            @Override
            public Double get() {
                return 0.0;
            }
        };
    }

    public Customer(int customerID, double arrivalTime, Supplier<Double> rng) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = dummyNo;
        this.greedy = false;
        this.rng = rng;
    }

    public boolean isGreedy() {
        return this.greedy;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    public double getServiceTime() {
        if (this.serviceTime < 0) {
            return this.rng.get();
        }
        return this.serviceTime;
    }

    @Override 
    public String toString() {
        return this.getCustomerID() + "";
    }


}
