package coursera.algo1.week3;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.tuple.Pair;

import notsandbox.Problem;
import notsandbox.Utils;

/**
 * The file contains the adjacency list representation of a simple undirected graph. There are 200 vertices labeled 1 to
 * 200. The first column in the file represents the vertex label, and the particular row (other entries except the first
 * column) tells all the vertices that the vertex is adjacent to. So for example, the 6th row looks like :
 * "6 155 56 52 120 ......". This just means that the vertex with label 6 is adjacent to (i.e., shares an edge with) the
 * vertices with labels 155,56,52,120,......,etc
 * 
 * Your task is to code up and run the randomized contraction algorithm for the min cut problem and use it on the above
 * graph to compute the min cut. (HINT: Note that you'll have to figure out an implementation of edge contractions.
 * Initially, you might want to do this naively, creating a new graph from the old every time there's an edge
 * contraction. But you should also think about more efficient implementations.)
 * 
 * (WARNING: As per the video lectures, please make sure to run the algorithm many times with different random seeds,
 * and remember the smallest cut that you ever find.)
 * 
 * Write your numeric answer in the space provided. So e.g., if your answer is 5, just type 5 in the space provided.
 * 
 * @author Alexey Grigorev
 */
public class ProblemA extends Problem {

    private UndirectedGraph graph;
    private UndirectedGraph initialGraphCopy;
    private int best;

    @Override
    public void run() {
        graph = readGraph(200);
        initialGraphCopy = graph.copy();
        best = Integer.MAX_VALUE;

        int trials = 25; // might be wrong, 200 should work for sure

        while (trials > 0) {
            iteration();
            graph = initialGraphCopy.copy();
            trials--;
        }

        out.println(best);
    }

    private void iteration() {
        while (graph.getN() > 2) {
            Pair<Integer, Integer> randomVertex = graph.randomEdge();
            graph.contract(randomVertex.getLeft(), randomVertex.getRight());
        }

        Map<Integer, List<Integer>> adjacencyList = graph.adjacencyList();

        Iterator<Entry<Integer, List<Integer>>> iterator = adjacencyList.entrySet().iterator();
        List<Integer> first = iterator.next().getValue();
        List<Integer> second = iterator.next().getValue();
        assertEquals(first.size(), second.size());

        if (best > first.size()) {
            best = first.size();
        }
    }

    public UndirectedGraph readGraph(int n) {
        UndirectedGraph graph = new UndirectedGraph(n);

        for (int i = 0; i < n; i++) {
            String nextLine = scanner.nextLine();
            int[] line = Utils.parseIntArray(nextLine.split("\\s+"));

            int v = line[0];

            for (int j = 1; j < line.length; j++) {
                int u = line[j];
                graph.addEdge(v - 1, u - 1);
            }
        }

        return graph;
    }

}
