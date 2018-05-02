package codeforces.c197;

import java.io.*;

public class D implements Runnable {

    public static final char WALL = '#';
    public static final char BOY = 'S';

    private PrintWriter out = new PrintWriter(System.out, true);
    private BufferedReader reader;
    private int n;

    private Point start;
    private char[][] labs;

    @Override
    public void run() {
        try {
            String[] first = reader.readLine().split("\\s+");
            n = Integer.parseInt(first[0]);

            readLab();

            String res = calc(labs, start);

            out.print(res);
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void readLab() throws Exception {
        labs = new char[n][];

        for (int y = 0; y < n; y++) {
            String line = reader.readLine();

            int boyX = line.indexOf(BOY);
            if (boyX >= 0) {
                start = new Point(boyX, y);
            }

            labs[y] = line.toCharArray();
        }
    }

    public String calc(char[][] lab, Point start) {
        return new DFS(lab).calc(start);
    }

    public D setInput(InputStream inputStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        return this;
    }

    public static void main(String[] args) {
        new D().setInput(System.in).run();
    }
}

class Point {
    final int x;
    final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class DFS {

    static final int[][] dxy = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    private final int n;
    private final int m;
    private final char[][] lab;

    private Point[][] used;
    private QuickStack<Point> stack;

    public DFS(char[][] lab) {
        this.n = lab.length;
        this.m = lab[0].length;
        this.lab = lab;

        this.used = new Point[n][m];
        this.stack = new QuickStack<Point>(m * n + 1);
    }

    public String calc(Point enter) {
        stack.push(enter);

        while (!stack.isEmpty()) {
            if (iteration()) {
                return "YES";
            }
        }

        return "NO";
    }

    private boolean iteration() {
        Point current = stack.pop();

        int x = normalizeX(current.x);
        int y = normalizeY(current.y);

        used[y][x] = current;

        for (int[] d : dxy) {
            int dx = current.x + d[0], dy = current.y + d[1];
            int ndx = normalizeX(dx), ndy = normalizeY(dy);

            if (isWall(ndx, ndy)) {
                continue;
            }

            Point prev = used[ndy][ndx];
            if (prev == null) {
                stack.push(new Point(dx, dy));
            } else {
                boolean same = prev.x == dx && prev.y == dy;
                if (!same) {
                    return true;
                }
            }
        }
        
        return false;
    }

    private boolean isWall(int ndx, int ndy) {
        return lab[ndy][ndx] == D.WALL;
    }

    private int normalizeX(int x) {
        int r = x % m;
        return r < 0 ? r + m : r;
    }

    private int normalizeY(int y) {
        int r = y % n;
        return r < 0 ? r + n : r;
    }
}

class QuickStack<Elem> {
    private final Elem[] values;
    private int head = -1;

    @SuppressWarnings("unchecked")
    public QuickStack(int capacity) {
        values = (Elem[]) new Object[capacity];
    }

    public void push(Elem element) {
        head++;
        values[head] = element;
    }

    public Elem pop() {
        Elem res = values[head];
        values[head] = null;
        head--;
        return res;
    }

    public boolean isEmpty() {
        return head < 0;
    }
}