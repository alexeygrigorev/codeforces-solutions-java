package optimization.wl;

import org.testng.annotations.Test;

import coursera.ProblemRunner;

public class SolverTest {

    @Test
    public void greedyRunner() {
        Solver solver = new Solver("greedy");
        ProblemRunner test = new ProblemRunner(solver).noDebuggingStdOutput();
        test.inputFromFile("wl_100_1");
        test.run();

        String output = test.getOutput();
        System.out.println(output);
    }

    @Test
    public void greedy2Runner() {
        Solver solver = new Solver("greedy2");
        ProblemRunner test = new ProblemRunner(solver).noDebuggingStdOutput();
        test.inputFromFile("wl_50_1");
        test.run();

        String output = test.getOutput();
        System.out.println(output);
    }

    @Test
    public void greedyuncapacitatedRunner() {
        Solver solver = new Solver("greedyuncapacitated");
        ProblemRunner test = new ProblemRunner(solver).noDebuggingStdOutput();
        test.inputFromFile("wl_200_1");
        test.run();

        String output = test.getOutput();
        System.out.println(output);
    }

}
