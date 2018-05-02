package mccme_shad;

import java.io.*;
import java.util.*;

/**
 * http://informatics.mccme.ru/moodle/mod/statements/view3.php?id=2741&chapterid=3176
 * 
 * @author Grigorev Alexey
 */
public class Problem3176_8Queens implements Runnable {

    private PrintWriter out = new PrintWriter(System.out, true);
    private Scanner scanner;

    @Override
    public void run() {
        List<Queen> queens = readData();

        boolean res = solve(queens);

        out.print(res ? "NO" : "YES");
        out.flush();
    }

    public boolean solve(List<Queen> queens) {
        int n = queens.size();
        for (int i = 0; i < n; i++) {
            Queen firstQueen = queens.get(i);
            for (int j = i + 1; j < n; j++) {
                Queen secondQueen = queens.get(j);
                if (firstQueen.strikes(secondQueen)) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<Queen> readData() {
        List<Queen> queens = new ArrayList<Queen>(8);
        while (scanner.hasNext()) {
            String next = scanner.nextLine();
            String[] input = next.split("\\s+");
            int x = Integer.parseInt(input[0]) - 1;
            int y = Integer.parseInt(input[1]) - 1;
            queens.add(new Queen(x, y));
        }
        return queens;
    }

    public Problem3176_8Queens setInput(InputStream inputStream) {
        this.scanner = new Scanner(new BufferedReader(new InputStreamReader(inputStream)));
        return this;
    }

    public static void main(String[] args) {
        new Problem3176_8Queens().setInput(System.in).run();
    }
}

class Queen {
    private final int x;
    private final int y;

    public Queen(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean strikes(Queen that) {
        return sameHorizontal(that) || sameVertical(that) || sameDiagonal(that);
    }

    public boolean sameHorizontal(Queen that) {
        return this.y == that.y;
    }

    public boolean sameVertical(Queen that) {
        return this.x == that.x;
    }

    public boolean sameDiagonal(Queen that) {
        return Math.abs(this.x - that.x) == Math.abs(this.y - that.y);
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + "}";
    }
}