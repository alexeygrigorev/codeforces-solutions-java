package optimization.puzzles;

import notsandbox.Problem;

public class NQueens extends Problem {
    private final int n;

    public NQueens(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        int[][] grid = new int[n][n];
        solve(grid, 0);
        int[] result = prepare(grid);
        print(result);
    }

    private int[] prepare(int[][] grid) {
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    result[i] = j;
                    break;
                }
            }
        }
        return result;
    }

    private void print(int[] result) {
        out.println(n);
        for (int i = 0; i < n; i++) {
            out.print(result[i]);
            out.print(' ');
        }
        out.println();
        out.flush();
    }

    private boolean solve(int[][] grid, int col) {
        if (col == grid.length) {
            return true;
        }

        for (int row = 0; row < grid.length; row++) {
            if (isValid(grid, row, col)) {
                grid[row][col] = 1;
                if (solve(grid, col + 1)) {
                    return true;
                }
                grid[row][col] = 0;
            }
        }

        return false;
    }

    private static boolean isValid(int[][] grid, int row, int col) {
        for (int i = row; i >= 0; i--) {
            if (grid[i][col] == 1) {
                return false;
            }
        }

        for (int i = col; i >= 0; i--) {
            if (grid[row][i] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (grid[i][j] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i < grid.length && j >= 0; i++, j--) {
            if (grid[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int n = 50;//Integer.parseInt(args[0]);
        new NQueens(n).setInput(System.in).run();
    }

}
