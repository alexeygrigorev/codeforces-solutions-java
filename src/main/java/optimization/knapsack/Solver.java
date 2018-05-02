package optimization.knapsack;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import optimization.knapsack.Knapsack.KnapsackResult;

public class Solver implements Runnable {

    protected PrintWriter out = new PrintWriter(System.out, true);
    protected Scanner scanner;
    private static String mode = "branchbound";

    @Override
    public void run() {
        solve().printTo(out);
    }

    public KnapsackResult solve() {
        Knapsack knapsack = readInput();
        if ("dynamic".equals(mode)) {
            return knapsack.solve(new KnapsackClassic());
        } else if ("dynamicoptimized".equals(mode)) {
            return knapsack.solve(new DynamicOptimized());
        } else if ("branchbound".equals(mode)) {
            return knapsack.solve(new BranchAndBounds.Strategy());
        }

        throw new IllegalStateException("wrong mode " + mode);
    }

    public Knapsack readInput() {
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();

        Knapsack.Item[] items = new Knapsack.Item[n];
        int number = 0;
        while (scanner.hasNext()) {
            items[number] = new Knapsack.Item(number, scanner.nextInt(), scanner.nextInt());
            number++;
        }

        return new Knapsack(capacity, items);
    }

    public Solver setInput(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
        return this;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public static void main(String[] args) {
        new Solver().setInput(System.in).run();
    }

}
