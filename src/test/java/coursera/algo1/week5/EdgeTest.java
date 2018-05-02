package coursera.algo1.week5;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class EdgeTest {

    @Test
    public void equals_sameDirection() {
        Edge edge1 = new Edge(1, 2, 10);
        Edge edge2 = new Edge(1, 2, 10);
        assertEquals(edge1, edge2);
        assertEquals(edge1.hashCode(), edge2.hashCode());
    }

    @Test
    public void equals_oppositeDirection() {
        Edge edge1 = new Edge(1, 2, 10);
        Edge edge2 = new Edge(2, 1, 10);
        assertEquals(edge1, edge2);
        assertEquals(edge1.hashCode(), edge2.hashCode());
    }
    
    @Test
    public void notEquals() {
        Edge edge1 = new Edge(1, 2, 11);
        Edge edge2 = new Edge(1, 2, 10);
        assertFalse(edge1.equals(edge2));
        assertFalse(edge1.hashCode() == edge2.hashCode());
    }
}
