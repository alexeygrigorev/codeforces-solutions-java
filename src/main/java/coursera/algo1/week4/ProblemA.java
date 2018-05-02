package coursera.algo1.week4;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.testng.collections.Lists;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;
import com.google.common.collect.Ordering;
import coursera.algo1.week4.UndirectedGraph;
import notsandbox.Problem;

/**
 * The file contains the edges of a directed graph. Vertices are labeled as positive integers from 1 to 875714. Every
 * row indicates an edge, the vertex label in first column is the tail and the vertex label in second column is the head
 * (recall the graph is directed, and the edges are directed from the first column vertex to the second column vertex).
 * So for example, the row looks liks : "2 47646". This just means that the vertex with label 2 has an outgoing edge to
 * the vertex with label 47646
 * 
 * Url with data: http://spark-public.s3.amazonaws.com/algo1/programming_prob/SCC.zip
 * 
 * Your task is to code up the algorithm from the video lectures for computing strongly connected components (SCCs), and
 * to run this algorithm on the given graph.
 * 
 * Output Format: You should output the sizes of the 5 largest SCCs in the given graph, in decreasing order of sizes,
 * separated by commas (avoid any spaces). So if your algorithm computes the sizes of the five largest SCCs to be 500,
 * 400, 300, 200 and 100, then your answer should be "500,400,300,200,100". If your algorithm finds less than 5 SCCs,
 * then write 0 for the remaining terms. Thus, if your algorithm computes only 3 SCCs whose sizes are 400, 300, and 100,
 * then your answer should be "400,300,100,0,0".
 * 
 * WARNING: This is the most challenging programming assignment of the course. Because of the size of the graph you may
 * have to manage memory carefully. The best way to do this depends on your programming language and environment, and we
 * strongly suggest that you exchange tips for doing this on the discussion forums.
 * 
 * JVM params to run with: -Xms512m -Xmx1024m -Xss1m
 * 
 * @author Grigorev Alexey
 * 
 */
public class ProblemA extends Problem {

    public static int VERTEX_AMOUNT = 875714;
    public static int LINES_AMOUNT = 5105043;

    private UndirectedGraph graph;

    private int counter = 0;
    private int currentLeaderVertex = -1;

    private boolean visited[];
    private int leaders[];
    private int finishingTime[];
    private int finishingTimeReversed[];

    @Override
    public void run() {
        graph = readGraph(VERTEX_AMOUNT, LINES_AMOUNT);

        dfs1Loop();
        dfs2Loop();

        List<Integer> result = sortedSizes(5);
        out.println(StringUtils.join(result, ","));
    }

    public UndirectedGraph readGraph(int n, int l) {
        UndirectedGraph graph = new UndirectedGraph(n);

        for (int i = 0; i < l; i++) {
            int v = scanner.nextInt();
            int u = scanner.nextInt();

            graph.addEdge(v - 1, u - 1);

            if (i % 1000 == 0) {
                System.out.println(i + "th has been just read");
            }
        }

        return graph;
    }

    public void dfs1Loop() {
        visited = new boolean[graph.getN()];
        finishingTime = new int[graph.getN()];
        Arrays.fill(finishingTime, -1);
        finishingTimeReversed = new int[graph.getN()];
        Arrays.fill(finishingTimeReversed, -1);

        for (int i = graph.getN() - 1; i >= 0; i--) {
            if (!visited[i]) {
                currentLeaderVertex = i;
                dfs1(i);
            }
        }
    }

    private void dfs1(int u) {
        visited[u] = true;

        for (int v : graph.reverse(u)) {
            if (!visited[v]) {
                dfs1(v);
            }
        }

        finishingTime[u] = counter;
        finishingTimeReversed[counter] = u;
        counter++;
    }

    public void dfs2Loop() {
        visited = new boolean[graph.getN()];
        leaders = new int[graph.getN()];
        Arrays.fill(leaders, -1);

        for (int i = graph.getN() - 1; i >= 0; i--) {
            int ft = finishingTimeReversed[i];
            if (!visited[ft]) {
                currentLeaderVertex = ft;
                dfs2(ft);
            }
        }
    }

    private void dfs2(int u) {
        visited[u] = true;
        leaders[u] = currentLeaderVertex;

        for (int v : graph.adjacent(u)) {
            if (!visited[v]) {
                dfs2(v);
            }
        }
    }

    public void setGraph(UndirectedGraph graph) {
        this.graph = graph;
    }

    public int[] getFinishingTime() {
        return finishingTime;
    }

    public int[] getLeaders() {
        return leaders;
    }

    public List<Integer> sortedSizes(int desiredSize) {
        List<Integer> result = sort(countFrequencies());

        if (desiredSize <= result.size()) {
            return result.subList(0, desiredSize);
        } else {
            return addUpZeros(desiredSize, result);
        }
    }

    private static List<Integer> sort(List<Integer> result) {
        Ordering<Integer> ordering = Ordering.natural().reverse();
        return ordering.sortedCopy(result);
    }

    private List<Integer> countFrequencies() {
        Multiset<Integer> distinctLeaders = distinctLeaders();
        List<Integer> result = Lists.newArrayList();

        for (Entry<Integer> entry : distinctLeaders.entrySet()) {
            result.add(entry.getCount());
        }
        return result;
    }

    public Multiset<Integer> distinctLeaders() {
        Multiset<Integer> result = HashMultiset.create();
        for (int i : leaders) {
            result.add(i);
        }
        return result;
    }

    private static List<Integer> addUpZeros(int desiredSize, List<Integer> result) {
        List<Integer> newResult = Lists.newArrayList(result);
        int n = desiredSize - result.size();
        while (n > 0) {
            newResult.add(0);
            n--;
        }
        return newResult;
    }
}