package mccme_shad;

import java.io.*;
import java.util.*;

/**
 * http://informatics.mccme.ru/moodle/mod/statements/view3.php?id=2585&chapterid=3080
 * 
 * @author Grigorev Alexey
 */
public class Problem3080 implements Runnable {

    private PrintWriter out = new PrintWriter(System.out, true);
    private Scanner scanner;

    @Override
    public void run() {
        int[] input = readData();

        int res = solve(input);

        out.print(res);
        out.flush();
    }

    public static int solve(int[] input) {
        int n = input.length;
        if (n >= 0 && n <= 2) {
            return 0;
        }

        int prevMaxIndex = findFirstLocalMaximum(input);
        int minDistance = Integer.MAX_VALUE;
        boolean found = false;

        while (true) {
            int currentMaxIndex = findNextLocalMaximum(input, prevMaxIndex + 1);
            if (withinBounds(currentMaxIndex, n)) {
                int dist = calcDistance(prevMaxIndex, currentMaxIndex);
                if (dist < minDistance) {
                    minDistance = dist;
                    found = true;
                }
                prevMaxIndex = currentMaxIndex;
            } else {
                break;
            }
        }

        if (found) {
            return minDistance;
        } else {
            return 0;
        }
    }

    public static boolean withinBounds(int maxIndex, int n) {
        return maxIndex < n - 1;
    }

    public static int calcDistance(int prev, int cur) {
        return cur - prev;
    }

    public static int findFirstLocalMaximum(int[] input) {
        return findNextLocalMaximum(input, 1);
    }

    public static int findNextLocalMaximum(int[] input, int from) {
        int n = input.length;
        int cur = from;
        while (withinBounds(cur, n) && !localMax(input, cur)) {
            cur++;
        }
        return cur;
    }

    private static boolean localMax(int[] input, int i) {
        int prev = input[i - 1];
        int cur = input[i];
        int next = input[i + 1];
        return cur > prev && cur > next;
    }

    private int[] readData() {
        int[] res = new int[10000];
        int i = 0;

        int next;
        while (true) {
            next = scanner.nextInt();
            if (next == 0) {
                break;
            }
            res[i] = next;
            i++;
        }

        return Arrays.copyOf(res, i);
    }

    public Problem3080 setInput(InputStream inputStream) {
        this.scanner = new Scanner(new BufferedReader(new InputStreamReader(inputStream)));
        return this;
    }

    public static void main(String[] args) {
        new Problem3080().setInput(System.in).run();
    }
}
