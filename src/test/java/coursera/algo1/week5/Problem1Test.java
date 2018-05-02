package coursera.algo1.week5;

import org.testng.annotations.Test;

import coursera.ProblemRunner;

/**
 * @author Grigorev Alexey
 */
public class Problem1Test {

    @Test
    public void assignment() {
        Problem1 sut = new Problem1();
        ProblemRunner test = new ProblemRunner(sut);
        test.inputFromFile("dijkstraData.txt");
        test.run();

        String output = test.getOutput();
        System.out.println("output: " + output);

        String solution = "2599,2610,2947,2052,2367,2399,2029,2442,2505,3068";
        test.verifyOutput(solution);
    }

    @Test
    public void testCaseFromForum() {
        Problem1 sut = new Problem1();
        sut.setSource(141);
        sut.setToVertices(3, 22, 27, 28, 32, 33, 35, 47, 55, 59, 71, 95, 114, 124, 136, 139, 179, 186, 188, 194);

        ProblemRunner test = new ProblemRunner(sut);
        test.inputFromFile("dijkstraData.txt");
        test.run();

        String solution = "2207,3523,2535,2794,2544,1301,2887,3513,973,1865,1860,685,2397,168,2249,3916,3134,4178,1004,1668";
        test.verifyOutput(solution);
    }
}
