package optimization.coloring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Graph2 {
    private int n;
    private final List<Vertex> vertices;

    public Graph2(int n) {
        this.n = n;
        this.vertices = createAdjacencyList(n);
    }

    private static List<Vertex> createAdjacencyList(int n) {
        List<Vertex> res = new ArrayList<Vertex>(n);

        int i = 0;
        while (i < n) {
            res.add(new Vertex(i));
            i++;
        }

        return res;
    }

    public void addEdge(int from, int to) {
        Vertex vertex1 = vertices.get(from);
        Vertex vertex2 = vertices.get(to);
        vertex1.addNodeTo(vertex2);
        vertex2.addNodeTo(vertex1);
    }

    public int getN() {
        return n;
    }

    public List<Vertex> allVerticies() {
        return new ArrayList<Vertex>(vertices);
    }

    public static class Vertex {
        final int number;
        final Collection<Vertex> adjacent;

        public Vertex(int number) {
            this(number, new LinkedList<Vertex>());
        }

        public Vertex(int number, Collection<Vertex> adjacent) {
            this.number = number;
            this.adjacent = adjacent;
        }

        public void addNodeTo(Vertex to) {
            adjacent.add(to);
        }

        @Override
        public int hashCode() {
            return number;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj instanceof Vertex) {
                return ((Vertex) obj).number == number;
            }

            return false;
        }
    }

}
