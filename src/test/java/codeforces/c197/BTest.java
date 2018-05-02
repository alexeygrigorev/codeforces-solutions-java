package codeforces.c197;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class BTest {

    private B sut;

    @BeforeMethod
    public void setUp() {
        sut = new B();
    }

    @Test
    public void calc_infinity() {
        sut.setP(1, 1, 1);
        sut.setQ(2, 5);

        Fraction res = sut.calc();

        assertEquals(res.toString(), "Infinity");
    }

    @Test
    public void calc_minusInfinity() {
        sut.setP(-1, 3);
        sut.setQ(2);

        Fraction res = sut.calc();

        assertEquals(res.toString(), "-Infinity");
    }
    
    @Test
    public void calc_minus_inf() {
        sut.setP(4, 3, 1, 2);
        sut.setQ(-5, 7, 0);

        Fraction res = sut.calc();

        assertEquals(res.toString(), "-Infinity");
    }

    @Test
    public void calc_zero() {
        sut.setP(1);
        sut.setQ(1, 0);

        Fraction res = sut.calc();

        assertEquals(res.toString(), "0/1");
    }

    @Test
    public void calc_zero2() {
        sut.setP(1);
        sut.setQ(2, 2);

        Fraction res = sut.calc();

        assertEquals(res.toString(), "0/1");
    }

    @Test
    public void calc_reduced() {
        sut.setP(2, 1, 6);
        sut.setQ(4, 5, -7);

        Fraction res = sut.calc();

        assertEquals(res.toString(), "1/2");
    }

    @Test
    public void calc_reduced_minus() {
        sut.setP(-2, 1, 6);
        sut.setQ(-4, 5, -7);

        Fraction res = sut.calc();

        assertEquals(res.toString(), "1/2");
    }

    @Test
    public void calc_minus() {
        sut.setP(1, 1, 6);
        sut.setQ(-4, 5, -7);

        Fraction res = sut.calc();

        assertEquals(res.toString(), "-1/4");
    }

    @Test
    public void calc_minus2() {
        sut.setP(-1, 1, 6);
        sut.setQ(4, 5, -7);

        Fraction res = sut.calc();

        assertEquals(res.toString(), "-1/4");
    }

    @Test
    public void calc_no_minus() {
        sut.setP(1, 1, 6);
        sut.setQ(4, 5, -7);

        Fraction res = sut.calc();

        assertEquals(res.toString(), "1/4");
    }

    @Test
    public void fraction_gcd() {
        int gcd = Fraction.gcd(1, 1);
        assertEquals(gcd, 1);
    }

    @Test
    public void fraction_gcd3() {
        int gcd = Fraction.gcd(3, 6);
        assertEquals(gcd, 3);
    }

    @Test
    public void fraction_gcd0() {
        int gcd = Fraction.gcd(0, 1);
        assertEquals(gcd, 1);
    }
}
