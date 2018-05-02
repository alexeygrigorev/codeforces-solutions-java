package coursera.algo1.week3;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;

import org.testng.annotations.Test;

import coursera.ProblemRunner;

public class ProblemATest {

    @Test
    public void readGraph() {
        ProblemA sut = new ProblemA();
        ProblemRunner test = new ProblemRunner(sut);
        test.inputFromFile("kargerMinCut.txt");

        UndirectedGraph graph = sut.readGraph(200);

        Iterable<Integer> row6th = graph.adjacentTo(5);

        // 6th row - 1
        assertEquals(row6th, Arrays.asList(154, 55, 51, 119, 130, 159, 123, 118, 13, 195, 143, 24, 74, 75, 165, 34, 86,
                25, 19, 31, 22));
    }

    @Test
    public void joinSeveralTimes() {
        ProblemRunner test = new ProblemRunner(new ProblemA());
        test.inputFromFile("kargerMinCut.txt");

        test.run();

        assertEquals(test.getOutput().trim(), "17");
    }

}
