import cs2030.simulator.Simulator2;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> arrivalTimes = new ArrayList<Double>();
        ArrayList<Double> serviceTimes = new ArrayList<Double>();
        int numServer = sc.nextInt();
        int queueLength = sc.nextInt();


        while (sc.hasNextDouble()) {
            arrivalTimes.add(sc.nextDouble());
            serviceTimes.add(sc.nextDouble());
        }

        Simulator2 s = new Simulator2(numServer, arrivalTimes, serviceTimes, queueLength);
        //System.out.println(queueLength);
        s.simulate();
    }

}
