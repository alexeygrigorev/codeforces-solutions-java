package optimization.wl;

import java.util.List;

import com.google.common.collect.Lists;

import optimization.wl.InputData.Customer;
import optimization.wl.InputData.Warehouse;

import notsandbox.Problem;

public class Solver extends Problem {

    private final WlSolver algo;

    public Solver(String algo) {
        if ("greedy".equals(algo)) {
            this.algo = new GreedySolver();
        } else if ("greedy2".equals(algo)) {
            this.algo = new GreedySolver2();
        } else if ("greedyuncapacitated".equals(algo)) {
            this.algo = new GreedyUncapacitatedSolver();
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

    public InputData readData() {
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<Warehouse> warehouses = Lists.newArrayListWithCapacity(n);
        for (int i = 0; i < n; i++) {
            int capacity = scanner.nextInt();
            double cost = scanner.nextDouble();
            Warehouse w = new Warehouse(i, capacity, cost);
            warehouses.add(w);
        }

        List<Customer> customers = Lists.newArrayListWithCapacity(m);
        for (int i = 0; i < m; i++) {
            int demand = scanner.nextInt();
            double[] cost = new double[n];
            for (int j = 0; j < n; j++) {
                cost[j] = scanner.nextDouble();
            }
            Customer customer = new Customer(i, demand, cost);
            customers.add(customer);
        }

        return new InputData(warehouses, customers);
    }

    public static void main(String[] args) {
        new Solver(args[0]).setInput(System.in).run();
    }

}