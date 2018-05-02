package coursera.algo1.week6;

import notsandbox.Problem;

import org.testng.annotations.Test;

import coursera.ProblemRunner;

public class Problem1Test {

    @Test
    public void assignment() {
        Problem sut = new Problem1();
        ProblemRunner test = new ProblemRunner(sut).noDebuggingStdOutput();
        test.inputFromFile("HashInt.txt");
        test.run();

        System.out.println("output: " + test.getOutput());
        test.verifyOutput("1477");
    }
}
