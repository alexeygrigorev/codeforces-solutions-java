package codeforces.c197;

import java.util.Arrays;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ETest {

    E sut = new E();
    
    @Test
    public void mostLeft0() {
        PointWithIndex p1 = new PointWithIndex(0, 0, 0), 
                p2 = new PointWithIndex(1, 0, 1),
                p3 = new PointWithIndex(2, 0, 2);
        
        PointWithIndex mostLeft = sut.findMostLeft(Arrays.asList(p1, p2, p3));
        
        assertEquals(mostLeft, p1);
    }
    
    @Test
    public void mostLeft1() {
        PointWithIndex p1 = new PointWithIndex(-1, -1, 0), 
                p2 = new PointWithIndex(0, 0, 1);
        
        PointWithIndex mostLeft = sut.findMostLeft(Arrays.asList(p1, p2));
        
        assertEquals(mostLeft, p1);
    }
    
    @Test
    public void mostLeft2() {
        PointWithIndex p1 = new PointWithIndex(-1, -1, 0), 
                p2 = new PointWithIndex(-1, 0, 1);
        
        PointWithIndex mostLeft = sut.findMostLeft(Arrays.asList(p1, p2));
        
        assertEquals(mostLeft, p1);
    }
    
    @Test
    public void AnglePointComparator_0_sameAngle() {
        PointWithIndex root = new PointWithIndex(0, 0, 0), 
                p1 = new PointWithIndex(1, 0, 1),
                p2 = new PointWithIndex(2, 0, 2);
        
        AnglePointComparator cmp = new AnglePointComparator(root);
        
        assertTrue(cmp.compare(p1, p2) == 0);
        assertTrue(cmp.compare(p2, p1) == 0);
    }
    
    @Test
    public void AnglePointComparator_somePoint_sameAngle() {
        PointWithIndex root = new PointWithIndex(-2, 2, 0), 
                p1 = new PointWithIndex(1, -1, 1),
                p2 = new PointWithIndex(2, -2, 2);
        
        AnglePointComparator cmp = new AnglePointComparator(root);
        
        assertEquals(cmp.compare(p1, p2), 0);
        assertEquals(cmp.compare(p2, p1), 0);
    }
    
    @Test
    public void AnglePointComparator_somePoint_notSame() {
        PointWithIndex root = new PointWithIndex(-2, 2, 0), 
                p1 = new PointWithIndex(1, -1, 1),
                p2 = new PointWithIndex(2, 0, 2);
        
        AnglePointComparator cmp = new AnglePointComparator(root);
        
        assertTrue(cmp.compare(p1, p2) > 0);
        assertTrue(cmp.compare(p2, p1) < 0);
    }
    
    @Test
    public void AnglePointComparator_0_notSame() {
        PointWithIndex root = new PointWithIndex(0, 0, 0), 
                p0 = new PointWithIndex(0, -1, 2),
                p1 = new PointWithIndex(1, 0, 2),
                p2 = new PointWithIndex(1, 1, 1);
        
        AnglePointComparator cmp = new AnglePointComparator(root);
        
        assertTrue(cmp.compare(p0, p1) > 0); 
        assertTrue(cmp.compare(p1, p2) > 0);
    }
    
    @Test
    public void doubleNegInfinity() {
        assertEquals(Double.NEGATIVE_INFINITY, -23423.432 * Double.POSITIVE_INFINITY);
    }
    
    @Test
    public void doubleZeroInfinity() {
        assertEquals(Double.NaN, 0 * Double.POSITIVE_INFINITY);
    }
}
