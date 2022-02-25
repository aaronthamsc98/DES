import cs2030.simulator.Simulator;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> arrivalTimes = new ArrayList<Double>();
        int numServer = sc.nextInt();

        while (sc.hasNextDouble()) {
            arrivalTimes.add(sc.nextDouble());
        }
        Simulator s = new Simulator(numServer, arrivalTimes);
        s.simulate();
    }
}
