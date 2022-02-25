package cs2030.simulator;

import java.util.function.Supplier;

public class GreedyCustomer extends Customer {
    //private final int customerID;
    //private final double arrivalTime;
    private final boolean greedy;

    public GreedyCustomer(int customerID, double arrivalTime, Supplier<Double> rng) {
        super(customerID, arrivalTime, rng);
        this.greedy = true;
    }

    @Override
    public boolean isGreedy() {
        return this.greedy;
    }

    @Override
    public int getCustomerID() {
        return super.getCustomerID();
    }

    @Override
    public double getArrivalTime() {
        return super.getArrivalTime();
    }

    @Override
    public String toString() {
        return getCustomerID() + "(greedy)";
    }


}
