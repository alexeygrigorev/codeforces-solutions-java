package problems;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.testng.collections.Lists;

import com.google.common.collect.Sets;

public class AsFewerAsPossible implements Runnable {

    private PrintWriter out = new PrintWriter(System.out, true);

    @Override
    public void run() {
        out.flush();
    }

    public Set<Integer> calc(Collection<Integer> in, int sum) {
        Result result = Sum.find(Lists.newArrayList(in), sum);
        return Sets.newHashSet(result.getElements());
    }
}

class Sum {
    public static Result find(final List<Integer> elements, final int minSum) {
        if (elements.isEmpty()) {
            return null;
        }
        
        Collections.sort(elements);
        return findInSortedArray(elements, minSum);
    }

    public static Result findInSortedArray(final List<Integer> elements, final int minSum) {
        int index = Collections.binarySearch(elements, minSum);
        if (index >= 0) {
            return new Result(elements.get(index));
        }

        int upperBoundIndex = -index - 1;
        
        if (upperBoundIndex == 0) {
            return new Result(elements.get(upperBoundIndex));
            
        }
        
        List<Integer> leftElements = elements.subList(0, upperBoundIndex);
        Result leftResult = findBestSum(leftElements, minSum);
        Result rightResult = null;
        
        if (upperBoundIndex != elements.size()) {
            rightResult = new Result(elements.get(upperBoundIndex));
        }

        return Result.getBetter(rightResult, leftResult);
    }

    public static Result findBestSum(List<Integer> elements, final int minSum) {
        Result result = null;

        while (true) {
            int lastIndex = elements.size() - 1;
            int lastValue = elements.get(lastIndex);
            elements = elements.subList(0, lastIndex);

            if (elements.isEmpty()) {
                break;
            }

            Result subResult = findInSortedArray(elements, minSum - lastValue);
            if (subResult == null) {
                break;
            }

            result = Result.getBetter(result, new Result(subResult, lastValue));
        }

        return result;
    }
}

class Result {
    private final List<Integer> elements;
    private final int sum;

    public Result(int oneElement) {
        this.elements = Collections.singletonList(oneElement);
        this.sum = oneElement;
    }

    public Result(Result subResult, int newValue) {
        this.elements = new ArrayList<Integer>(subResult.getElements());
        this.elements.add(newValue);
        this.sum = subResult.getSum() + newValue;
    }

    public int get(int index) {
        return elements.get(index);
    }

    public int size() {
        return elements.size();
    }

    public int getSum() {
        return sum;
    }

    public static Result getBetter(Result left, Result right) {
        if (right == null) {
            return left;
        }
        if (left == null) {
            return right;
        }
        if (left.getSum() < right.getSum()) {
            return left;
        }
        if (left.getSum() > right.getSum()) {
            return right;
        }
        if (left.size() > right.size()) {
            return right;
        }

        return left;
    }

    public List<Integer> getElements() {
        return elements;
    }
}
