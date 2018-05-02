package coursera.algo2.week1;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import coursera.ProblemRunner;
import coursera.algo2.week1.JobSchedulingProblem.DifferenceJobComparator;
import coursera.algo2.week1.JobSchedulingProblem.Job;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Grigorev Alexey
 */
public class JobSchedulingProblemTest {

    JobSchedulingProblem sut = new JobSchedulingProblem();

    @Test
    public void sumOfWeightedCompletionTimes() {
        List<Job> list = Arrays.asList(j(30, 50), j(20, 40), j(10, 30), j(20, 60), j(10, 50), j(30, 90));
        long result = sut.sumOfWeightedCompletionTimes(list);
        assertEquals(result, 20000);
    }

    @Test
    public void problem1Comparator() {
        DifferenceJobComparator comparator = new DifferenceJobComparator();
        Job j1 = j(10, 20); // diff -10
        Job j2 = j(20, 40); // diff -20

        // diff -20 should come first
        assertTrue(comparator.compare(j1, j2) < 0);
    }

    @Test
    public void problem1() {
        List<Job> list = Arrays.asList(j(30, 90), j(10, 30), j(20, 40), j(30, 50), j(20, 60), j(10, 50));

        List<Job> actualSorting = sut.problem1Sort(list);

        List<Job> expectedResult = Arrays.asList(j(30, 50), j(20, 40), j(10, 30), j(20, 60), j(10, 50), j(30, 90));
        assertEquals(actualSorting, expectedResult);

        long result = sut.problem1(list);
        assertEquals(result, 20000);
    }

    @Test
    public void problem2() {
        List<Job> list = Arrays.asList(j(30, 90), j(10, 30), j(20, 40), j(30, 50), j(20, 60), j(10, 50));
        long result = sut.problem2(list);
        assertEquals(result, 19400);
    }

    @Test
    public void assignment1_2() {
        JobSchedulingProblem problem = new JobSchedulingProblem();
        ProblemRunner runner = new ProblemRunner(problem).noDebuggingStdOutput();

        runner.inputFromFile("jobs.txt");
        runner.run();

        System.out.println(runner.getOutput());
        runner.verifyOutput("69119377652 67311454237");
    }

    @Test(dataProvider = "testCases")
    public void testCasesFromForum(String file, int problem1res, int problem2res) {
        JobSchedulingProblem problem = new JobSchedulingProblem();
        ProblemRunner runner = new ProblemRunner(problem);

        runner.inputFromFile(file);
        runner.run();

        runner.verifyOutput(problem1res + " " + problem2res);
    }

    @DataProvider
    public Object[][] testCases() {
        return new Object[][] { 
            { "jobs_test1.txt", 40135, 38638 }, 
            { "jobs_test2.txt", 79805, 76719 } 
        };
    }

    private static Job j(int weight, int length) {
        return new Job(weight, length);
    }

    @Test
    public void compareInt_firstLessThanSecond() {
        int result = Utils.compareInt(0, 1);
        assertTrue(result < 0);
    }

    @Test
    public void compareInt_firstGreaterThanSecond() {
        int result = Utils.compareInt(10, 1);
        assertTrue(result > 0);
    }

    @Test
    public void compareInt_equal() {
        int result = Utils.compareInt(10, 10);
        assertTrue(result == 0);
    }

}
