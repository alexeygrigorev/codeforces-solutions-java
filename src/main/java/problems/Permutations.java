package problems;

import java.util.*;

public class Permutations {

    public static long perm(List<Integer> p, List<Integer> x) {
        if (x.size() == 2) {
            return 1;
        }

        long c = 0;
        for (Integer xi : x) {
            LinkedList<Integer> withoutXi = without(x, xi);
            int cur = countInversions(xi, withoutXi);

            LinkedList<Integer> pAndXi = with(p, xi);
            c = c + cur + perm(pAndXi, withoutXi);
        }

        return c;
    }

    private static int countInversions(Integer xi, List<Integer> withoutXi) {
        int cur = 0;
        for (Integer notXi : withoutXi) {
            if (xi > notXi) {
                cur++;
            }
        }
        return cur;
    }

    private static LinkedList<Integer> without(List<Integer> x, Integer xi) {
        LinkedList<Integer> withoutXi = new LinkedList<Integer>();
        for (Integer notXi : x) {
            if (!xi.equals(notXi)) {
                withoutXi.add(notXi);
            }
        }
        return withoutXi;
    }

    private static LinkedList<Integer> with(List<Integer> p, Integer xi) {
        LinkedList<Integer> pAndXi = new LinkedList<Integer>(p);
        pAndXi.add(xi);
        return pAndXi;
    }

    public static long permDyn(int n) {
        long m[] = new long[n];
        m[0] = n;
        long c = 0;

        for (int i = 1; i < n; i++) {
            m[i] = m[i - 1] * (n - i);
            c = c + m[i];
        }

        return c / 2;
    }

    public static void main(String[] args) {
        List<Integer> toPermute = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        long perm = perm(Collections.<Integer> emptyList(), toPermute);
        System.out.println(perm);

        int last = toPermute.get(toPermute.size() - 1);
        long perm3 = permDyn(last);
        System.out.println(perm3);
    }
}
