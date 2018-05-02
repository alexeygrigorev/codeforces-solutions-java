package codeforces.c197;

import org.testng.annotations.Test;

import com.google.common.collect.Iterables;

import static org.testng.Assert.*;

public class EGraphTest {

    @Test
    public void testAdd() {
        Graph g = new Graph(2);
        g.addEdge(0, 1);
        
        assertTrue(Iterables.contains(g.adjacent(0), 1));
        assertTrue(Iterables.contains(g.adjacent(1), 0));
    }
    
}
