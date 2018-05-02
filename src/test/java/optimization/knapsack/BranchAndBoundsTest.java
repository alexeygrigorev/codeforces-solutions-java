package optimization.knapsack;

import general.list.Cons;
import optimization.knapsack.Knapsack.Item;
import optimization.knapsack.Knapsack.KnapsackResult;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BranchAndBoundsTest {

    @Test
    public void preSort() {
        Item[] items = new Item[] { i(0, 10, 5), i(1, 30, 2), i(2, 10, 1), i(3, 10, 3) };
        Item[] result = BranchAndBounds.preSort(items);

        assertEquals(result[0].number, 1);
        assertEquals(result[1].number, 2);
        assertEquals(result[2].number, 3);
        assertEquals(result[3].number, 0);
    }

    @Test
    public void calcBound() {
        Item[] items = BranchAndBounds.preSort(new Item[] { i(0, 10, 5), i(1, 30, 2), i(2, 10, 1), i(3, 10, 3) });
        Cons<Item> cons = Cons.from(items);

        double res = BranchAndBounds.calcBound(cons, 100, 0.0);
        assertEquals(res, 60.0);

        double res2 = BranchAndBounds.calcBound(cons, 5, 0.0);
        assertEquals(res2, 46.66, 0.1);

        double res3 = BranchAndBounds.calcBound(cons.tail(), 5, 0.0);
        assertEquals(res3, 22.0);
    }

    private Item i(int number, int value, int weight) {
        return new Item(number, value, weight);
    }

    @Test
    public void solution_ks4() {
        // "4 11\n" + "8 4\n" + "10 5\n" + "15 8\n" + "4 3\n"
        Item[] items = new Item[] { i(0, 8, 4), i(1, 10, 5), i(2, 15, 8), i(3, 4, 3) };
        BranchAndBounds branchAndBounds = new BranchAndBounds(11, items);

        KnapsackResult result = branchAndBounds.solve();
        assertEquals(result.getValue(), 19);
    }

    @Test
    public void fromLecture() {
        Item[] items = new Item[] { i(0, 45, 5), i(1, 48, 8), i(2, 35, 3) };
        BranchAndBounds branchAndBounds = new BranchAndBounds(10, items);

        KnapsackResult result = branchAndBounds.solve();
        assertEquals(result.getValue(), 80);
    }
}
