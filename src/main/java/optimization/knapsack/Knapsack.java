package optimization.knapsack;

import java.io.PrintWriter;

public class Knapsack {

    public static interface KnapsackSolver {
        KnapsackResult solve(Knapsack data);
    }

    private final int capacity;
    private final Item[] items;

    public Knapsack(int capacity, Item[] items) {
        this.capacity = capacity;
        this.items = items;
    }

    public KnapsackResult solve(KnapsackSolver solver) {
        return solver.solve(this);
    }

    public int getCapacity() {
        return capacity;
    }

    public Item[] getItems() {
        return items;
    }

    public static class KnapsackResult {
        private final int value;
        private final int[] result;

        public KnapsackResult(int value, int[] result) {
            this.value = value;
            this.result = result;
        }

        public void printTo(PrintWriter out) {
            out.print(value);
            out.print(' ');
            out.print(1); // indicates that the solution is optimal
            out.print('\n');
            for (int i = 0; i < result.length; i++) {
                out.print(result[i]);
                out.print(' ');
            }
            out.flush();
        }

        public int[] getResult() {
            return result.clone();
        }

        public int getValue() {
            return value;
        }
    }

    public static class Item {
        protected final int number;
        protected final int value;
        protected final int weight;

        public Item(int number, int value, int weight) {
            this.number = number;
            this.value = value;
            this.weight = weight;
        }

        public double relativeValue() {
            return value / (double) weight;
        }

        @Override
        public String toString() {
            return "Item [number=" + number + ", value=" + value + ", weight=" + weight + "]";
        }
    }
}
