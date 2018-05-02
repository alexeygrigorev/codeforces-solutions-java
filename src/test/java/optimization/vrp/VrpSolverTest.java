package optimization.vrp;

import org.testng.annotations.Test;

import coursera.ProblemRunner;

public class VrpSolverTest {

    @Test
    public void solveNaiveGreedy() {
        Solver solver = new Solver("naivegreedy");
        ProblemRunner test = new ProblemRunner(solver);
        test.inputFromFile("vrp_26_8_1");
        Result result = solver.solve();

        result.visualizeTo("vrp_26_8_1.svg");

        String output = test.getOutput();
        System.out.println(output);
    }

    @Test
    public void solveCw() {
        Solver solver = new Solver("cw");
        ProblemRunner test = new ProblemRunner(solver);
        test.inputFromFile("vrp_26_8_1");
        Result result = solver.solve();
        // result.checkFeasibility();

        result.visualizeTo("vrp_26_8_1.svg");

        String output = test.getOutput();
        System.out.println(output);
    }

    @Test
    public void solveRandomizedGreedy() {
        Solver solver = new Solver("randomizedgreedy");
        ProblemRunner test = new ProblemRunner(solver);
        test.inputFromFile("vrp_26_8_1");
        Result result = solver.solve();
        result.checkFeasibility();

        result.visualizeTo("vrp_26_8_1.svg");

        String output = test.getOutput();
        System.out.println(output);
    }
}
