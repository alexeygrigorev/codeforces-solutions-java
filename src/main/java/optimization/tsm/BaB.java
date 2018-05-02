package optimization.tsm;

import general.list.Cons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * Greedy randomized branch-and-bounds approach
 * 
 * @author Grigorev Alexey
 */
public class BaB implements TspSolver {

    private static final Random RANDOM = new Random();

    private double optCost;
    private PartialResult best;
    private int totalSize;
    private long totalLimit = 0;

    @Override
    public Result solve(List<Point> input) {
        totalSize = input.size();

        ArrayList<Point> random = Lists.newArrayList(input);
        Collections.shuffle(random);

        Result greedyResult = new Greedy().solve(random);
        double greedyDistance = greedyResult.getDistance();

        best = null;
        optCost = greedyDistance;

        Cons<Point> all = Cons.from(random);
        for (Point start : random) {
            double coeff = 2.0;
            totalLimit = (long) (totalSize * totalSize * totalSize * coeff);

            Cons<Point> head = Cons.cons(start, null);
            PartialResult partial = new PartialResult(head);
            tsp(all.filter(start), partial);
        }

        if (best == null) {
            return greedyResult;
        } else {
            return best.toFull(input);
        }
    }

    public void tsp(Cons<Point> notUsed, PartialResult partial) {
        totalLimit--;
        if (totalLimit < 0) {
            return;
        }

        Queue<Point> queue = priorityByDistance(partial, notUsed);
        int limit = 2;

        while (!queue.isEmpty() && limit > 0) {
            Point p = queue.poll();

            double distanceWhenAdding = partial.distanceWhenAdding(p);
            if (distanceWhenAdding < optCost) {
                PartialResult nextBest = partial.augment(p);
                if (nextBest.isComplete(totalSize)) {
                    nextBest.checkIfComplete(totalSize);
                    double cost = nextBest.totalCost();
                    if (cost < optCost) {
                        optCost = cost;
                        best = nextBest;
                    } else {
                        return;
                    }
                } else {
                    tsp(notUsed.filter(p), nextBest);
                }
            } else {
                return;
            }
            if (RANDOM.nextBoolean()) {
                return;
            }
            limit--;
        }
    }

    private PriorityQueue<Point> priorityByDistance(PartialResult partial, Cons<Point> notUsed) {
        return queue(notUsed, partial.head(), totalSize);
    }

    private static PriorityQueue<Point> queue(Cons<Point> notUsed, final Point head, int totalSize) {
        PriorityQueue<Point> queue = new PriorityQueue<Point>(totalSize, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                double d1 = head.squareDistanceTo(p1);
                double d2 = head.squareDistanceTo(p2);
                return Double.compare(d1, d2);
            }
        });
        Cons<Point> l = notUsed;
        while (l != null) {
            queue.add(l.head());
            l = l.tail();
        }
        return queue;
    }

    static class PartialResult {

        private final Point first;
        private final Cons<Point> last;
        private final double cost;
        private final int step;

        private PartialResult(Cons<Point> head) {
            this.last = head;
            this.first = head.head();
            this.cost = 0;
            this.step = 1;
        }

        public void checkIfComplete(int totalSize) {
            Validate.isTrue(totalSize == step);
            boolean[] checked = new boolean[totalSize];
            Cons<Point> l = last;
            while (l != null) {
                Point p = l.head();
                Validate.isTrue(!checked[p.getNumber()]);
                checked[p.getNumber()] = true;
                l = l.tail();
            }
        }

        private PartialResult(Cons<Point> head, double cost, int nextStep, Point first) {
            this.last = head;
            this.cost = cost;
            this.step = nextStep;
            this.first = first;
        }

        public Set<Point> notUsed(List<Point> allPoint) {
            Set<Point> used = Sets.newLinkedHashSet(last.toList());
            Set<Point> all = Sets.newLinkedHashSet(allPoint);
            SetView<Point> result = Sets.difference(all, used);
            return result.immutableCopy();
        }

        public PartialResult augment(Point next) {
            Cons<Point> cons = Cons.cons(next, last);
            double newCost = distanceWhenAdding(next);
            return new PartialResult(cons, newCost, step + 1, first);
        }

        public double totalCost() {
            return cost + first.distanceTo(last.head());
        }

        public double distanceWhenAdding(Point next) {
            return cost + head().distanceTo(next);
        }

        public Point head() {
            return last.head();
        }

        public boolean better(PartialResult other) {
            return this.cost > other.cost;
        }

        public boolean isComplete(int totalSize) {
            return totalSize == step;
        }

        public Result toFull(List<Point> input) {
            List<Point> path = last.reverse().toList();
            return new Result(input, path, false);
        }

    }

}
