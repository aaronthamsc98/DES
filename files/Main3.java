import cs2030.simulator.Simulator3;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> arrivalTimes = new ArrayList<Double>();
        ArrayList<Double> serviceTimes = new ArrayList<Double>();
        ArrayList<Double> restTimes = new ArrayList<Double>();
        int numServer = sc.nextInt();
        int queueLength = sc.nextInt();
        int numOfCustomers = sc.nextInt();

        for (int i = 0; i < numOfCustomers; i++) {
            arrivalTimes.add(sc.nextDouble());
            serviceTimes.add(sc.nextDouble());
        }

        while (sc.hasNextDouble()) {
            restTimes.add(sc.nextDouble());
        }

        Simulator3 s = new Simulator3(numServer, arrivalTimes, serviceTimes,
                queueLength, numOfCustomers, restTimes);
        //System.out.println(queueLength);
        s.simulate();
        //System.out.println(arrivalTimes);
        //System.out.println(serviceTimes);
        //System.out.println(restTimes);
    }

}