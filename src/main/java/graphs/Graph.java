package graphs;

import java.util.Collection;

public interface Graph {

    void addEdge(int from, int to);

    Collection<Vertex> adjacent(int v);

}
