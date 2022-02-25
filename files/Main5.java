import cs2030.simulator.Simulator5;

import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println(sc.nextInt());

        int seed = sc.nextInt();
        int numServer = sc.nextInt();
        int numSCO = sc.nextInt();
        int queueLength = sc.nextInt();
        int numOfCustomers = sc.nextInt();
        double arrivalRate = sc.nextDouble();
        double serviceRate = sc.nextDouble();
        double restRate = sc.nextDouble();
        double probRest = sc.nextDouble();
        double probGreedy = sc.nextDouble();

        Simulator5 s = new Simulator5(seed, numServer, numSCO, queueLength,
                numOfCustomers, arrivalRate,
                serviceRate, restRate, probRest, probGreedy);

        s.simulate();


    }

}
