package mccme_shad;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class Problem3079Test {

    Problem3079 problem = new Problem3079();

    @Test
    public void solve() {
        int ans = problem.solve(new int[] { 1, 2, 1, 2, 1 });
        assertEquals(ans, 2);
    }

}
