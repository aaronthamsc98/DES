package cs2030.simulator;

import java.util.ArrayList;

public class SelfCheckout extends Server {
    //arrive -> serve -> done
    //arrive -> wait -> serve -> done
    private static ArrayList<Customer> waitQ = new ArrayList<Customer>();
    private final double[] nextAvailTime;
    private final boolean[] serving;
    private final boolean human;
    private static final String serverType = "self-check";
    private final boolean[] resting;
    private final boolean[] incomingCust;
    private final Customer[] waitingCust;
    private static final int dummyNo = 9999;

    public SelfCheckout(int serverID, int qSize) {
        super(serverID, qSize);
        this.nextAvailTime = new double[1];
        this.serving = new boolean[1];
        this.human = false;
        this.resting = new boolean[1];
        this.incomingCust = new boolean[1];
        this.waitingCust = new Customer[]{new Customer(dummyNo,0)};
    }

    public boolean getIncomingCust() {
        return this.incomingCust[0];
    }

    public void updateWaitCust(Customer customer) {
        this.waitingCust[0] = customer;
    }

    public Customer getWaitCust() {
        return this.waitingCust[0];
    }

    public void updateIncoming() {
        this.incomingCust[0] = true;
    }

    public void clearIncoming() {
        this.incomingCust[0] = false;
    }

    public int getServerID() {
        return super.getServerID();
    }

    public ArrayList<Customer> getWaitQ() {
        return waitQ;
    }

    public boolean getServing() {
        return this.serving[0];
    }

    public boolean getResting() {
        return this.resting[0];
    }

    public String getServerType() {
        return this.serverType;
    }

    public void clearServe() {
        this.serving[0] = false;
    }

    public void updateServe() {
        this.serving[0] = true;
    }

    public double getNextAvailTime() {
        return this.nextAvailTime[0];
    }

    public void setNextAvailTime(double time) {
        this.nextAvailTime[0] = time;
    }

    public void updateWaitQ(Customer customer) {
        waitQ.add(customer);
    }

    public boolean isHuman() {
        return this.human;
    }

    public void clearQ() {
        if (!waitQ.isEmpty()) {
            waitQ.remove(0);
        }
    }

    public boolean serveStatus() {
        if (!this.serving[0]) {
            return true;
        } else {
            return false;
        }
    }

    public boolean waitQFull() {
        if (waitQ.size() < this.getQSize()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Self-checkout: " + this.getServerID();
    }

}
