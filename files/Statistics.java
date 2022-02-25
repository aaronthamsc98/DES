package cs2030.simulator;

import java.util.ArrayList;

public class Statistics {
    private final int[] left;
    private final int[] done;
    private final double[] totalWaitTime;

    public Statistics() {
        this.left = new int[1];
        this.done = new int[1];
        this.totalWaitTime = new double[1];
    }
    
    public void updateLeft(int count) {
        this.left[0] += count;
    }

    public void updateDone(int count) {
        this.done[0] += count;
    }
   
    public void updateWaitTime(double time) {
        this.totalWaitTime[0] += time;
    }

    @Override
    public String toString() {
        return String.format("%.3f", (double) totalWaitTime[0] / this.done[0])
                + " " + this.done[0] + " " + this.left[0];
    }

}
