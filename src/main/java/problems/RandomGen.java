package problems;

import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang3.time.StopWatch;

public class RandomGen {
    private final static Random RANDOM = new Random();

    public static void main(String[] args) {
        char begin = 'а' - 1;

        String s0 = "ленин";
        int s[] = new int[6];
        for (int i = 0; i < 5; i++) {
            s[i] = s0.charAt(i) - begin;
        }

        System.out.println(Arrays.toString(s));

        tryAll(s);
    }

    public static void tryAll(int[] s) {
        long c = 0;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        while (true) {
            int[] trial = new int[6];
            for (int i = 0; i < 6; i++) {
                int next = RANDOM.nextInt(34);
                trial[i] = next;
            }

            c++;

            boolean b = true;
            for (int i = 0; i < 6; i++) {
                b = b && trial[i] == s[i];
            }

            if (b) {
                System.out.println("Done");
                break;
            }

            if (c % 5000000 == 0) {
                System.out.println(c);
            }
        }
        stopWatch.stop();

        System.out.println(stopWatch.getTime() + " ms");
        System.out.println("Took " + c + " trials");
    }
}
