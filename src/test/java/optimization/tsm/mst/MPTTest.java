package optimization.tsm.mst;

import java.util.Arrays;
import java.util.List;

import optimization.tsm.Point;
import optimization.tsm.mst.Edge;
import optimization.tsm.mst.MST;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class MPTTest {

    @Test
    public void mst() {
        Point p0 = new Point(0, 2, 1);
        Point p1 = new Point(1, 3, 4);
        Point p2 = new Point(2, 3, 7);
        Point p3 = new Point(3, 4, 5);
        Point p4 = new Point(4, 6, 7);
        Point p5 = new Point(5, 6, 4);
        Point p6 = new Point(6, 8, 5);
        Point p7 = new Point(7, 7, 1);
        List<Point> list = Arrays.asList(p0, p1, p2, p3, p4, p5, p6, p7);

        List<Edge> edges = new MST().kruskal(list);

        System.out.println(edges);

        assertEquals(7, edges.size());
        assertTrue(edges.contains(new Edge(p0, p1)));
        assertTrue(edges.contains(new Edge(p1, p3)));
        assertTrue(edges.contains(new Edge(p2, p3)));
        assertTrue(edges.contains(new Edge(p3, p4)));
        assertTrue(edges.contains(new Edge(p3, p5)));
        assertTrue(edges.contains(new Edge(p5, p6)));
        assertTrue(edges.contains(new Edge(p5, p7)));

    }
}
