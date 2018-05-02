package coursera.algo1.week6;

import org.testng.annotations.Test;

import coursera.ProblemRunner;

public class Problem2Test {

    @Test
    public void assignment() {
        Problem2 sut = new Problem2();
        ProblemRunner test = new ProblemRunner(sut).noDebuggingStdOutput();
        test.inputFromFile("Median.txt");
        test.run();

        System.out.println("output: " + test.getOutput());
        test.verifyOutput("1213");
    }

}
