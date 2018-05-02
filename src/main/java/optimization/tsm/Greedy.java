package optimization.tsm;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Greedy implements TspSolver {

    private static final double INFTY = 1.0 / 0.0;

    @Override
    public Result solve(List<Point> input) {
        List<Point> path = Lists.newLinkedList();
        Set<Point> toVisit = Sets.newLinkedHashSet(input);

        Point cur = input.get(0);
        path.add(cur);
        toVisit.remove(cur);

        while (!toVisit.isEmpty()) {
            Point closest = findClosed(cur, toVisit);
            path.add(closest);
            toVisit.remove(closest);
            cur = closest;
        }

        return new Result(input, path, false);
    }

    private Point findClosed(Point cur, Set<Point> toVisit) {
        double min = INFTY;
        Point closest = null;

        for (Point p : toVisit) {
            double d = cur.squareDistanceTo(p);
            if (d < min) {
                min = d;
                closest = p;
            }
        }

        return Validate.notNull(closest);
    }
}
