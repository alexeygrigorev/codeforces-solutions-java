package optimization.tsm;

import java.util.List;
import java.util.UUID;

import notsandbox.Problem;
import optimization.tsm.mst.MST;

import com.google.common.collect.Lists;

public class Solver extends Problem {

    private final TspSolver algo;

    public Solver(String algorithm) {
        this.algo = find(algorithm);
    }

    private static TspSolver find(String algo) {
        if ("greedy".equals(algo)) {
            return new Greedy();
        } else if ("greedy2".equals(algo)) {
            return new Greedy2();
        } else if ("mst".equals(algo)) {
            return new MST();
        } else if ("bb".equals(algo)) {
            return new BaB();
        } else {
            throw new IllegalArgumentException("not valid algorithm argument");
        }
    }

    @Override
    public void run() {
        Result result = solve();
        result.visualize(algo + " " + UUID.randomUUID().toString().substring(0, 10) + ".svg");
    }

    public Result solve() {
        List<Point> points = readData();
        Result result = algo.solve(points);
        result.outputTo(out);
        return result;
    }

    public List<Point> readData() {
        int n = scanner.nextInt();

        List<Point> points = Lists.newArrayListWithCapacity(n);

        for (int i = 0; i < n; i++) {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();

            Point point = new Point(i, x, y);
            points.add(point);
        }
        return points;
    }

    public static void main(String[] args) {
        new Solver(args[0]).setInput(System.in).run();
    }

}