package codeforces.c197;

import java.io.*;
import java.util.*;

public class E implements Runnable {

    private static final int NO_PARENT = -1;
    private PrintWriter out = new PrintWriter(System.out, true);
    private Scanner scanner;

    private int n;
    private Graph graph;
    private List<PointWithIndex> points;

    private int sizes[];
    private int ans[];

    @Override
    public void run() {
        readData();

        calcLeavesSize();

        int[] answer = drawTree();
        print(answer);
    }

    private void readData() {
        n = scanner.nextInt();
        graph = readGraph();
        points = readPoints();
    }

    private Graph readGraph() {
        Graph graph = new Graph(n);
        for (int i = 0; i < n - 1; i++) {
            int v = scanner.nextInt();
            int u = scanner.nextInt();
            graph.addEdge(v - 1, u - 1);
        }

        return graph;
    }

    private List<PointWithIndex> readPoints() {
        List<PointWithIndex> points = new ArrayList<PointWithIndex>(n);

        for (int i = 0; i < n; i++) {
            long x = scanner.nextLong();
            long y = scanner.nextLong();
            points.add(new PointWithIndex(x, y, i));
        }

        return points;
    }

    private void calcLeavesSize() {
        // подвешиваем дерево за первую вешину и считаем размер всех поддеревьев
        sizes = new int[n];
        dfsSize(0, NO_PARENT);
    }

    private void dfsSize(int v, int parent) {
        sizes[v] = 1;

        for (int u : graph.adjacent(v)) {
            if (u != parent) {
                dfsSize(u, v);
                sizes[v] = sizes[v] + sizes[u];
            }
        }
    }

    public int[] drawTree() {
        PointWithIndex mostLeft = findMostLeft(points);
        Collections.swap(points, 0, mostLeft.index);

        ans = new int[n];
        Arrays.fill(ans, -1);

        // самая левая точка идет первой
        dfsDraw(0, NO_PARENT, 0, n);

        return ans;
    }

    public PointWithIndex findMostLeft(List<PointWithIndex> points) {
        return Collections.min(points, new MostLeftPointComparator());
    }

    private void dfsDraw(int v, int parent, int left, int rigth) {
        // рекурсивно рисуем дерево
        PointWithIndex point = points.get(left);
        ans[point.index] = v;

        int nextLeft = left + 1;
        int nextRight;

        Collections.sort(points.subList(nextLeft, rigth), new AnglePointComparator(point));

        for (int u : graph.adjacent(v)) {
            if (u != parent) {
                nextRight = nextLeft + sizes[u];
                dfsDraw(u, v, nextLeft, nextRight);
                nextLeft = nextRight;
            }
        }
    }

    private void print(int[] answer) {
        for (int i : answer) {
            out.print(i + 1);
            out.print(' ');
        }

        out.flush();
    }

    public E setInput(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
        return this;
    }

    public static void main(String[] args) {
        new E().setInput(System.in).run();
    }
}

class Graph {
    private final int n;
    private final List<List<Integer>> adj;

    public Graph(int n) {
        this.n = n;
        this.adj = createAdjacentList(n);
    }

    private static List<List<Integer>> createAdjacentList(int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>(n);

        while (n > 0) {
            res.add(new ArrayList<Integer>());
            n--;
        }

        return res;
    }

    public void addEdge(int v, int u) {
        adj.get(v).add(u);
        adj.get(u).add(v);
    }

    public Iterable<Integer> adjacent(int v) {
        return adj.get(v);
    }

    public int getN() {
        return n;
    }
}

class PointWithIndex {
    public final long x;
    public final long y;
    public final int index;

    public PointWithIndex(long x, long y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }
}

class MostLeftPointComparator implements Comparator<PointWithIndex> {
    @Override
    public int compare(PointWithIndex p1, PointWithIndex p2) {
        int byx = compare(p1.x, p2.x);
        if (byx == 0) {
            return compare(p1.y, p2.y);
        }
        return byx;
    }

    public static int compare(long a, long b) {
        return a < b ? -1 : (a == b ? 0 : 1);
    }
}

class AnglePointComparator implements Comparator<PointWithIndex> {
    PointWithIndex root;

    public AnglePointComparator(PointWithIndex root) {
        this.root = root;
    }

    @Override
    public int compare(PointWithIndex p1, PointWithIndex p2) {
        // сравнение тангенсов, что же самое, что dy2/dx2 - dy1/dx1
        long dx1 = p1.x - root.x, dx2 = p2.x - root.x;
        long dy1 = p1.y - root.y, dy2 = p2.y - root.y;
        return MostLeftPointComparator.compare(dx1 * dy2, dy1 * dx2);
    }
}