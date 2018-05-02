package coursera.algo1.week2;

import org.testng.annotations.Test;

import coursera.ProblemRunner;

import static org.testng.Assert.*;

public class ProgrammingAssignment2 {

    @Test
    public void problemA() {
        ProblemRunner test = new ProblemRunner(new ProblemA());

        test.inputFromFile("QuickSort.txt");
        test.run();

        String output = test.getOutput().trim();
        System.out.println(output);
        assertEquals(output, "162085");
    }

    @Test
    public void problemB() {
        ProblemRunner test = new ProblemRunner(new ProblemB());

        test.inputFromFile("QuickSort.txt");
        test.run();

        String output = test.getOutput().trim();
        System.out.println(output);
        assertEquals(output, "164123");
    }

    @Test
    public void problemC() {
        ProblemRunner test = new ProblemRunner(new ProblemC());

        test.inputFromFile("QuickSort.txt");
        test.run();

        String output = test.getOutput().trim();
        System.out.println(output);
        assertEquals(output, "138382");
    }
}
