package optimization.knapsack;

import general.list.Cons;

import java.util.Arrays;
import java.util.Comparator;

import optimization.knapsack.Knapsack.KnapsackResult;

public class BranchAndBounds {

    public static class Strategy implements Knapsack.KnapsackSolver {
        @Override
        public KnapsackResult solve(Knapsack data) {
            return new BranchAndBounds(data.getCapacity(), data.getItems()).solve();
        }
    }

    private final Knapsack.Item[] items;
    private final int capacity;
    private final int size;

    private double bestBound;

    public BranchAndBounds(int capacity, Knapsack.Item[] items) {
        this.capacity = capacity;
        this.items = preSort(items);
        this.size = items.length;
    }

    public Knapsack.KnapsackResult solve() {
        Cons<Knapsack.Item> list = Cons.from(items);
        bestBound = 0.0;

        double bound = calcBound(list, capacity, 0.0);
        Solution res = dfs(list, 0, capacity, bound, null);

        return buildSolution(res);
    }

    private Knapsack.KnapsackResult buildSolution(Solution res) {
        int value = 0;
        int[] result = new int[size];
        Cons<Knapsack.Item> cell = res.taken;
        while (cell != null) {
            Knapsack.Item item = cell.head();
            value = value + item.value;
            result[item.number] = 1;
            cell = cell.tail();
        }

        return new Knapsack.KnapsackResult(value, result);
    }

    public Solution dfs(Cons<Knapsack.Item> list, int value, int weight, double bound, Cons<Knapsack.Item> taken) {
        if (bound < bestBound) {
            return null;
        }
        if (list == null) {
            bestBound = bound;
            return new Solution(bound, taken);
        }
        Knapsack.Item item = list.head();
        if (item.weight > weight) {
            return notTaking(list, value, weight, taken);
        }

        // taking the item
        Solution left = dfs(list.tail(), value + item.value, weight - item.weight, bound, Cons.cons(item, taken));

        // not taking the item
        Solution right = notTaking(list, value, weight, taken);

        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        if (right.bound > left.bound) {
            return right;
        } else {
            return left;
        }
    }

    private Solution notTaking(Cons<Knapsack.Item> list, int value, int weight, Cons<Knapsack.Item> taken) {
        Cons<Knapsack.Item> tail = list.tail();
        Cons<Knapsack.Item> without;
        if (taken != null) {
            without = taken.reverse().append(tail);
        } else {
            without = tail;
        }
        double newBound = calcBound(without, capacity, 0.0);
        if (newBound <= bestBound) {
            return null;
        }
        return dfs(tail, value, weight, newBound, taken);
    }

    public static double calcBound(Cons<Knapsack.Item> list, int k, double acc) {
        if (list == null) {
            return acc;
        }

        Knapsack.Item head = list.head();
        if (head.weight <= k) {
            return calcBound(list.tail(), k - head.weight, acc + head.value);
        } else {
            return acc + head.relativeValue() * k;
        }
    }

    public static class Solution {
        private final double bound;
        private final Cons<Knapsack.Item> taken;

        public Solution(double bound, Cons<Knapsack.Item> taken) {
            this.bound = bound;
            this.taken = taken;
        }

        @Override
        public String toString() {
            return "(" + bound + ", [" + taken != null ? taken.join(",") : "" + "])";
        }
    }

    public static Knapsack.Item[] preSort(Knapsack.Item[] items) {
        Knapsack.Item[] clone = items.clone();
        Arrays.sort(clone, new Comparator<Knapsack.Item>() {
            @Override
            public int compare(Knapsack.Item o1, Knapsack.Item o2) {
                double relativeValue1 = o1.relativeValue();
                double relativeValue2 = o2.relativeValue();
                return -Double.compare(relativeValue1, relativeValue2);
            }
        });
        return clone;
    }

}
