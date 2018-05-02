package optimization.tsm;

import org.testng.annotations.Test;

import coursera.ProblemRunner;

public class SolverTest {

    @Test
    public void greedyRunner() {
        Solver solver = new Solver("greedy");
        ProblemRunner test = new ProblemRunner(solver);
        test.inputFromFile("tsp_2103_1");
        test.run();

        String output = test.getOutput();
        System.out.println(output);
    }

    @Test
    public void greedy1Runner() {
        Solver solver = new Solver("greedy");
        ProblemRunner test = new ProblemRunner(solver);
        test.inputFromFile("tsp_2103_1");

        String output = test.getOutput();
        System.out.println(output);
    }

    @Test
    public void greedy2Runner() {
        Solver solver = new Solver("greedy2");
        ProblemRunner test = new ProblemRunner(solver);
        test.inputFromFile("tsp_51_1");
        Result result = solver.solve();
        result.visualize("tsp_51_1.svg");
        String output = test.getOutput();
        System.out.println(output);
    }

    @Test
    public void mstRunner() {
        Solver solver = new Solver("mst");
        ProblemRunner test = new ProblemRunner(solver);
        test.inputFromFile("tsp_2103_1");
        test.run();

        String output = test.getOutput();
        System.out.println(output);
    }
}
