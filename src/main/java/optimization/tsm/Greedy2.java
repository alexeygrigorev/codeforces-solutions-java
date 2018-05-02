package optimization.tsm;

import general.list.Cons;

import java.util.List;
import java.util.Set;


import org.apache.commons.lang3.Validate;

import com.google.common.collect.Sets;

public class Greedy2 implements TspSolver {
    private static final double INFTY = 1.0 / 0.0;

    @Override
    public Result solve(List<Point> input) {
        Set<Point> toVisit = Sets.newLinkedHashSet(input);

        Point first = input.get(0);
        Cons<Point> path = Cons.cons(first, null);
        toVisit.remove(first);

        while (!toVisit.isEmpty()) {
            Point best = findBest(path, toVisit);
            path = Cons.cons(best, path);
            toVisit.remove(best);
        }

        return new Result(input, path.reverse().toList(), false);
    }

    private Point findBest(Cons<Point> path, Set<Point> toVisit) {
        double min = INFTY;
        Point best = null;

        for (Point p : toVisit) {
            Cons<Point> newPath = Cons.cons(p, path);

            double d = calcTotalDistance(newPath);
            if (d < min) {
                min = d;
                best = p;
            }
        }

        return Validate.notNull(best);
    }

    public static double calcTotalDistance(Cons<Point> path) {
        if (path == null) {
            return 0.0;
        }

        double res = 0.0;

        Cons<Point> it = path;
        Point prev = path.head();
        Point first = prev;

        while (it != null) {
            Point next = it.head();
            res = res + prev.distanceTo(next);
            prev = next;
            it = it.tail();
        }

        res = res + prev.distanceTo(first);
        return res;
    }
}
