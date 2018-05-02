package optimization.knapsack;

import optimization.knapsack.Knapsack.Item;
import optimization.knapsack.Knapsack.KnapsackResult;

public class KnapsackClassic implements Knapsack.KnapsackSolver {

    @Override
    public KnapsackResult solve(Knapsack data) {
        Item[] items = data.getItems();
        int capacity = data.getCapacity();

        int[][] table = calcTable(data);
        int[] res = backtrack(data, table);

        return new KnapsackResult(table[items.length][capacity], res);
    }

    private int[][] calcTable(Knapsack data) {
        Item[] items = data.getItems();
        int capacity = data.getCapacity();

        int size = items.length;
        int[][] table = new int[size + 1][capacity + 1];

        for (Item item : items) {
            int weight = item.weight;
            int value = item.value;
            int[] prevRow = table[item.number];
            int[] currentRow = table[item.number + 1];

            for (int k = 1; k <= capacity; k++) {
                currentRow[k] = prevRow[k];
                if (weight <= k) {
                    int with = prevRow[k - weight] + value;
                    int without = prevRow[k];
                    if (without < with) {
                        currentRow[k] = with;
                    }
                }
            }
        }

        return table;
    }

    private int[] backtrack(Knapsack data, int[][] table) {
        Item[] items = data.getItems();
        int capacity = data.getCapacity();
        int size = items.length;
        int[] res = new int[size];

        int k = capacity;
        for (int item = size; item > 0; item--) {
            if (table[item][k] != table[item - 1][k]) {
                res[item - 1] = 1;
                int weight = items[item - 1].weight;
                k = k - weight;
            }
        }

        return res;
    }

}
