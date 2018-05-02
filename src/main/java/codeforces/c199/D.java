package codeforces.c199;

import java.io.*;

public class D implements Runnable {

    public static final char WALL = '-';
    public static final char DANGEROUS_WALL = 'X';

    public static final int LEFT_WALL = 0;
    public static final int RIGHT_WALL = 1;

    private PrintWriter out = new PrintWriter(System.out, true);
    private BufferedReader reader;

    private int n, k;
    private char[][] walls;
    private boolean[][] visit;

    @Override
    public void run() {
        try {
            String[] first = reader.readLine().split("\\s+");
            n = Integer.parseInt(first[0]);
            k = Integer.parseInt(first[1]);

            char[] left = reader.readLine().toCharArray();
            char[] right = reader.readLine().toCharArray();
            walls = new char[][] { left, right };
            visit = new boolean[2][n];

            boolean res = calc();
            out.println(res ? "YES" : "NO");
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean calc() {
        return dfs(0, LEFT_WALL, 0);
    }

    public boolean dfs(int position, int wallSide, int waterLevel) {
        return notDrowned(position, waterLevel) && canGetOut(position, wallSide, waterLevel);
    }

    private boolean notDrowned(int position, int waterLevel) {
        return position >= waterLevel;
    }
    
    private boolean canGetOut(int position, int wallSide, int waterLevel) {
        return jumpedOut(position) || jump(position, wallSide, waterLevel + 1)
                || goUpwards(position, wallSide, waterLevel + 1) || goDownwards(position, wallSide, waterLevel + 1);
    }

    private boolean jumpedOut(int position) {
        return position >= n - 1 || position + k >= n;
    }

    private boolean jump(int position, int wallSide, int newWaterLevel) {
        return step(position + k, (wallSide + 1) % 2, newWaterLevel);
    }

    private boolean goUpwards(int position, int wallSide, int newWaterLevel) {
        return step(position + 1, wallSide, newWaterLevel);
    }

    private boolean goDownwards(int position, int wallSide, int newWaterLevel) {
        return step(position - 1, wallSide, newWaterLevel);
    }

    private boolean step(int position, int wall, int newWaterLevel) {
        return canProceed(position, wall) && visit(position, wall) && dfs(position, wall, newWaterLevel);
    }

    private boolean visit(int position, int wallSide) {
        visit[wallSide][position] = true;
        return true;
    }

    private boolean canProceed(int position, int wall) {
        return positionInBound(position) && notVisited(position, wall) && notDangerous(position, wall);
    }

    private boolean positionInBound(int position) {
        return position >= 0 && position < n;
    }

    private boolean notVisited(int position, int wall) {
        return !visit[wall][position];
    }

    private boolean notDangerous(int position, int wall) {
        return walls[wall][position] != DANGEROUS_WALL;
    }

    public D setInput(InputStream inputStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        return this;
    }

    public static void main(String[] args) {
        new D().setInput(System.in).run();
    }
}