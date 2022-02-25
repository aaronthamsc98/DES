package cs2030.simulator;

import java.util.ArrayList;

public class Server {
    private final int serverID;
    private final ArrayList<Customer> waitQ;
    private final double[] nextAvailTime;
    private final boolean[] serving;
    private final int qSize;
    private final boolean[] resting;
    private final Customer[] currServing;
    private final boolean human;
    private final String serverType = "server";
    private final boolean[] incomingCust;
    private final Customer[] waitingCust;
    private static final int dummyNo = 9999;

    public Server(int serverID) {
        this.serverID = serverID;
        this.waitQ = new ArrayList<Customer>(1);
        this.serving = new boolean[1];
        this.nextAvailTime = new double[1];
        this.qSize = 1;
        this.resting = new boolean[1];
        this.currServing = new Customer[1];
        this.human = true;
        this.incomingCust = new boolean[1];
        this.waitingCust = new Customer[]{new Customer(dummyNo,0)};
    }

    public Server(int serverID, int qSize) {
        this.serverID = serverID;
        this.waitQ = new ArrayList<Customer>();
        this.serving = new boolean[1];
        this.nextAvailTime = new double[1];
        this.qSize = qSize;
        this.resting = new boolean[1];
        this.currServing = new Customer[1];
        this.human = true;
        this.incomingCust = new boolean[1];
        this.waitingCust = new Customer[]{new Customer(dummyNo,0)};
    }

    public Server(int serverID, int qSize, ArrayList<Double> restTimes) {
        this.serverID = serverID;
        this.waitQ = new ArrayList<Customer>();
        this.serving = new boolean[1];
        this.nextAvailTime = new double[1];
        this.qSize = qSize;
        this.resting = new boolean[1];
        this.currServing = new Customer[1];
        this.human = true;
        this.incomingCust = new boolean[1];
        this.waitingCust = new Customer[]{new Customer(dummyNo,0)};
    }

    public Customer getWaitCust() {
        return this.waitingCust[0];
    }

    public void updateWaitCust(Customer customer) {
        this.waitingCust[0] = customer;
    }

    public boolean getIncomingCust() {
        return this.incomingCust[0];
    }

    public void updateIncoming() {
        this.incomingCust[0] = true;
    }

    public void clearIncoming() {
        this.incomingCust[0] = false;
    }

    public void setNextAvailTime(double time) {
        this.nextAvailTime[0] = time;
    }

    public double getNextAvailTime() {
        return this.nextAvailTime[0];
    }

    public Customer getCurrServing() {
        return this.currServing[0];
    }

    public boolean getResting() {
        return this.resting[0];
    }

    public int getServerID() {
        return this.serverID;
    }

    public ArrayList<Customer> getWaitQ() {
        return this.waitQ;
    }

    public boolean isResting() {
        return this.resting[0];
    }

    public void updateRest() {
        if (this.resting[0] == false) {
            this.resting[0] = true;
        }
    }

    public void clearRest() {
        this.resting[0] = false;
    }


    public boolean serveStatus() {
        if (this.serving[0] == false && this.resting[0] == false) {
            return true;
        } else {
            return false;
        }
    }

    public boolean waitQFull() {
        if (this.waitQ.size() < this.qSize) {
            return false;
        } else {
            return true;
        }
    }

    public boolean waitStatus() {
        if (this.waitQ.size() < this.qSize && this.resting[0] == false) {
            return true;
        } else if (this.waitQ.size() < this.qSize && this.resting[0] == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getServing() {
        return this.serving[0];
    }

    public Customer getWaitingCust() {
        return this.waitQ.get(0);
    }


    public void updateWaitQ(Customer customer) {
        this.waitQ.add(customer);
    }

    public void clearQ() {
        if (!this.waitQ.isEmpty()) {
            this.waitQ.remove(0);
        }
    }

    public void clearServe() {
        this.serving[0] = false;
    }

    public void updateServe() {
        this.serving[0] = true;
    }

    public void doneServing() {
        clearServe();
        this.nextAvailTime[0] = 0;
        if (!waitQ.isEmpty()) {
            this.nextAvailTime[0] += waitQ.get(0).getServiceTime();
            clearQ();
        }
    }

    public double getNextDoneTime() {
        double output = this.nextAvailTime[0];
        if (!this.waitQ.isEmpty()) {
            for (int i = 0; i < this.waitQ.size(); i++) {
                output += this.waitQ.get(i).getServiceTime();
            }
        }
        return output;
    }

    public boolean isHuman() {
        return this.human;
    }

    public String getServerType() {
        return this.serverType;
    }

    public int getQSize() {
        return this.qSize;
    }

    @Override
    public String toString() {
        return "Server: " + this.serverID;
    }

}
