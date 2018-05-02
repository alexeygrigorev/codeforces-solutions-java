package problems;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableSet;

import static org.testng.Assert.assertEquals;

public class AsFewerAsPossibleTest {
    
    AsFewerAsPossible sut = new AsFewerAsPossible();
    
    @Test(dataProvider = "input")
    public void calc(Collection<Integer> input, int sum, Set<Integer> expectedResult) {
        Set<Integer> result = sut.calc(input, sum);
        assertEquals(result, expectedResult);
    }
    
    @DataProvider
    public Object[][] input() {
        return new Object[][] {
            { Arrays.asList(20, 30, 100, 50), 100, Collections.singleton(100) },
            { Arrays.asList(10, 10, 10, 20, 100, 30, 50, 10, 10), 80, ImmutableSet.of(30, 50) },
            { Arrays.asList(20, 100, 30, 50, 10, 10, 10, 10), 85, ImmutableSet.of(30, 50, 10) },
        };
    }
}
