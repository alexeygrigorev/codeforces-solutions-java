package coursera.algo1.week6;

import java.util.Iterator;

import org.apache.commons.io.LineIterator;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;

import notsandbox.Problem;

/**
 * Download the text file from http://spark-public.s3.amazonaws.com/algo1/programming_prob/Median.txt.
 * 
 * The goal of this problem is to implement the "Median Maintenance" algorithm (covered in the Week 5 lecture on heap
 * applications). The text file contains a list of the integers from 1 to 10000 in unsorted order; you should treat this
 * as a stream of numbers, arriving one by one. Letting xi denote the ith number of the file, the kth median mk is
 * defined as the median of the numbers x1,...,xk. (So, if k is odd, then mk is ((k+1)/2)th smallest number among x1,...,xk;
 * if k is even, then mk is the (k/2)th smallest number among x1,...,xk.)
 * 
 * In the box below you should type the sum of these 10000 medians, modulo 10000 (i.e., only the last 4 digits). That
 * is, you should compute (m1+m2+m3+...+m10000) mod 10000.
 * 
 * @author Grigorev Alexey
 */
public class Problem2 extends Problem {

    @Override
    public void run() {
        Iterator<Integer> stream = readFile();
        int sum = calcSumOfMedians(stream);
        out.println(sum);
    }

    public int calcSumOfMedians(Iterator<Integer> stream) {
        NextMedian medians = new NextMedian(stream);

        long sum = 0;
        while (medians.hasNext()) {
            sum = sum + medians.next();
        }

        return (int) (sum % 10000);
    }

    public Iterator<Integer> readFile() {
        return Iterators.transform(new LineIterator(reader), new Function<String, Integer> () {
            @Override
            public Integer apply(String input) {
                return Integer.parseInt(input);
            }
        });
    }

}
