package coursera.algo2.week1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import coursera.ProblemRunner;

/**
 * @author Grigorev Alexey
 */
public class MSTProblemTest {

    @Test(dataProvider = "testCases")
    public void run(String file, int cost) {
        MSTProblem mstProblem = new MSTProblem();
        ProblemRunner runner = new ProblemRunner(mstProblem);

        runner.inputFromFile(file);
        runner.run();

        runner.verifyOutput(String.valueOf(cost));
    }

    @DataProvider
    public Object[][] testCases() {
        return new Object[][] { 
            { "edges_test1.txt", -27534 }, 
            { "edges_test2.txt", 37 }, 
        };
    }

    @Test
    public void assignment() {
        MSTProblem mstProblem = new MSTProblem();
        ProblemRunner runner = new ProblemRunner(mstProblem);

        runner.inputFromFile("edges.txt");
        runner.run();

        runner.verifyOutput("-3612829");
    }

}
