package codeforces.c197;

import java.io.*;

public class B implements Runnable {

    private PrintWriter out = new PrintWriter(System.out, true);
    private BufferedReader reader;

    private int[] p;
    private int[] q;

    @Override
    public void run() {
        try {
            reader.readLine();

            setP(toIntArray(reader.readLine().split("\\s+")));
            setQ(toIntArray(reader.readLine().split("\\s+")));

            out.print(calc());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        out.flush();
    }

    public Fraction calc() {
        int lenP = p.length, lenQ = q.length;

        if (lenP == lenQ) {
            return Fraction.fraction(p[0], q[0]);
        } else if (lenP > lenQ) {
            return Fraction.inf(p[0], q[0]);
        } else {
            return Fraction.ZERO;
        }
    }

    private static int[] toIntArray(String[] line) {
        int length = line.length;
        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            result[i] = Integer.parseInt(line[i]);
        }

        return result;
    }

    public void setP(int... p) {
        this.p = p;
    }

    public void setQ(int... q) {
        this.q = q;
    }

    public B setInput(InputStream inputStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        return this;
    }

    public static void main(String[] args) {
        new B().setInput(System.in).run();
    }

}

class Fraction {
    public static final Fraction POSITIVE_INFINITY = new Fraction(1, 0);
    public static final Fraction NEGATIVE_INFINITY = new Fraction(-1, 0);
    public static final Fraction ZERO = new Fraction(0, 1);
    
    public static Fraction fraction(int numerator, int denumerator) {
        return new Fraction(numerator, denumerator).reduced();
    }
    
    public static Fraction inf(int singNumerator, int singDenumerator) {
        boolean numPositive = singNumerator > 0;
        boolean denPositive = singDenumerator > 0;
        
        if ((numPositive && denPositive) || (!numPositive && !denPositive)) {
            return POSITIVE_INFINITY;
        } else {
            return NEGATIVE_INFINITY;
        }
    }

    final int numerator;
    final int denumerator;

    private Fraction(int numerator, int denumerator) {
        this.numerator = numerator;
        this.denumerator = denumerator;
    }

    public Fraction reduced() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denumerator));
        if (numerator < 0 && denumerator < 0) {
            gcd = -gcd;
        }
        return new Fraction(numerator / gcd, denumerator / gcd);
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    @Override
    public String toString() {
        if (isInfinity()) {
            return infinity();
        }

        return sign() + Math.abs(numerator) + "/" + Math.abs(denumerator);
    }

    private boolean isInfinity() {
        return denumerator == 0;
    }
    
    private String infinity() {
        if (numerator > 0) {
            return "Infinity";
        } else {
            return "-Infinity";
        }
    }

    private String sign() {
        // both can't be true
        if (numerator < 0 || denumerator < 0) {
            return "-";
        }
        return "";
    }
}