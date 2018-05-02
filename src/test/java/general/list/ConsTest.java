package general.list;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ConsTest {

    @Test
    public void fromToList() {
        List<Integer> items = Arrays.asList(1, 2, 3, 4);
        Cons<Integer> result = Cons.from(items);
        assertEquals(result.toList(), items);
    }

    @Test
    public void append() {
        Cons<Integer> list1 = Cons.from(1, 2, 3, 4);
        Cons<Integer> list2 = Cons.from(10, 11, 12, 13);
        Cons<Integer> result = list1.append(list2);
        assertEquals(result.toList(), Arrays.asList(1, 2, 3, 4, 10, 11, 12, 13));
    }

    @Test
    public void join() {
        String join = Cons.from(1, 2, 3, 4).join(",");
        assertEquals(join, "1,2,3,4");
    }

    @Test
    public void joinOne() {
        String join = Cons.from(1).join(",");
        assertEquals(join, "1");
    }

    @Test
    public void reverse() {
        Cons<Integer> reverse = Cons.from(1, 2, 3, 4).reverse();
        assertEquals(reverse, Cons.from(4, 3, 2, 1));
    }

    @Test
    public void filter() {
        Cons<Integer> result = Cons.from(1, 2, 3, 4).filter(2).filter(3);
        assertEquals(result, Cons.from(1, 4));
    }
}
