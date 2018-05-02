package codeforces.c199;

import java.io.*;
import java.util.*;

public class C implements Runnable {
    private PrintWriter out = new PrintWriter(System.out, true);
    private Scanner scanner;

    @Override
    public void run() {
        long k = scanner.nextInt();
        long b = scanner.nextInt();
        long n = scanner.nextInt();
        long t = scanner.nextInt();

        out.print(calc(k, b, n, t));
        out.flush();
    }

    public long calc(long k, long b, long n, long t) {
        if (t == 1) {
            return n;
        }
        
        long steps = n, cur = 1;
        
        while (t >= cur && steps >= 0) {
            cur = cur * k + b;
            steps--;
        }
        
        return steps + 1;
    }

    public C setInput(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
        return this;
    }

    public static void main(String[] args) {
        new C().setInput(System.in).run();
    }
}