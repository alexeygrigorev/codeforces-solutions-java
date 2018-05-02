package mccme_shad;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * http://informatics.mccme.ru/moodle/mod/statements/view3.php?id=2585&chapterid=3079
 * 
 * Элемент последовательности называется локальным максимумом, если он строго больше предыдущего и последующего элемента
 * последовательности. Первый и последний элемент последовательности не являются локальными максимумами.
 * 
 * @author Grigorev Alexey
 */
public class Problem3079 implements Runnable {

    private PrintWriter out = new PrintWriter(System.out, true);
    private Scanner scanner;

    @Override
    public void run() {
        int[] input = readData();

        int res = solve(input);

        out.print(res);
        out.flush();
    }

    public int solve(int[] input) {
        int n = input.length;
        if (n >= 0 && n <= 2) {
            return 0;
        }

        int c = 0;

        for (int i = 1; i < n - 1; i++) {
            int prev = input[i - 1];
            int cur = input[i];
            int next = input[i + 1];

            if (cur > prev && cur > next) {
                c++;
            }
        }

        return c;
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

    public Problem3079 setInput(InputStream inputStream) {
        this.scanner = new Scanner(new BufferedReader(new InputStreamReader(inputStream)));
        return this;
    }

    public static void main(String[] args) {
        new Problem3079().setInput(System.in).run();
    }
}
