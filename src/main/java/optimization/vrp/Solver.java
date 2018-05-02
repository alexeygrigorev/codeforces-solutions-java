package optimization.vrp;

import java.util.List;

import org.testng.collections.Lists;

import notsandbox.Problem;

public class Solver extends Problem {

    private VrpSolver algo;

    public Solver(String algo) {
        if ("naivegreedy".equals(algo)) {
            this.algo = new NaiveGreedySolver();
        } else if ("randomizedgreedy".equals(algo)) {
            this.algo = new RandomizedGreedySolver();
        } else if ("cw".equals(algo)) {
            this.algo = new ClarkeWrightSolver();
        } else {
            throw new IllegalArgumentException("unknown algo " + algo);
        }
    }

    @Override
    public void run() {
        solve();
    }

    public Result solve() {
        InputData input = readData();
        Result result = algo.solve(input);
        result.outputTo(out);
        return result;
    }

    private InputData readData() {
        int numberOfCustoments = scanner.nextInt();
        int numberOfVehicles = scanner.nextInt();
        int vehicleCapacity = scanner.nextInt();

        Location warehouse = readLocation(0);

        List<Location> locations = Lists.newArrayList();
        for (int i = 1; i < numberOfCustoments; i++) {
            Location location = readLocation(i);
            locations.add(location);
        }

        return new InputData(numberOfCustoments, numberOfVehicles, vehicleCapacity, warehouse, locations);
    }

    private Location readLocation(int index) {
        int demand = scanner.nextInt();
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();
        return new Location(index, demand, x, y);
    }

    public static void main(String[] args) {
        new Solver(args[0]).setInput(System.in).run();
    }

}
