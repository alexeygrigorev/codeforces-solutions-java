package optimization.tsm;

import org.testng.annotations.Test;

import coursera.ProblemRunner;

public class BaBTest {

    @Test
    public void solve() {
        Solver solver = new Solver("bb");
        ProblemRunner test = new ProblemRunner(solver);
        test.inputFromFile("tsp_51_1");
        Result result = solver.solve();
        result.check();
        result.visualize("tsp_51_1.svg");

        String output = test.getOutput();
        System.out.println(output);
    }
}
