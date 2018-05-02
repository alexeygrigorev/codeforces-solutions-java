package optimization.coloring;

import graphs.UndirectedGraph;

import org.testng.annotations.Test;

import coursera.ProblemRunner;
import static org.testng.Assert.*;

public class GreedyTest {
    @Test
    public void gc_250_9_greedy() {
        GreedySolver solver = new GreedySolver();
        ProblemRunner test = new ProblemRunner(solver);
        test.inputFromFile("gc_250_9");
        UndirectedGraph graph = solver.readInput();

        Result result = new Greedy().solve(graph);
        assertTrue(result.checkAgainst(graph));
        assertTrue(result.getSolution() <= 90);
        
        result.outputTo(test.outputStream());
        System.out.println(test.capturedOutput());
    }
}
