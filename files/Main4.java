import cs2030.simulator.Simulator4;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main4 {
    public static void main(String[] args) {
        //Num servers, num self-checkout, max queue, num customers
        //note: if there are k human servers, then the SCO are identified from k + 1 onwards.
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> arrivalTimes = new ArrayList<Double>();
        ArrayList<Double> serviceTimes = new ArrayList<Double>();
        ArrayList<Double> restTimes = new ArrayList<Double>();
        int numServer = sc.nextInt();
        int numSCO = sc.nextInt();
        int queueLength = sc.nextInt();
        int numOfCustomers = sc.nextInt();

        for (int i = 0; i < numOfCustomers; i++) {
            arrivalTimes.add(sc.nextDouble());
            serviceTimes.add(sc.nextDouble());
        }

        while (sc.hasNextDouble()) {
            restTimes.add(sc.nextDouble());
        }

        Simulator4 s = new Simulator4(numServer, arrivalTimes, serviceTimes,
                queueLength, numOfCustomers, restTimes, numSCO);

        s.simulate();

    }

}