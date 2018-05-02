package optimization.coloring;

import graphs.UndirectedGraph;
import notsandbox.Problem;

public class GreedySolver extends Problem {

    @Override
    public void run() {
        UndirectedGraph graph = readInput();
        new Greedy().solve(graph).outputTo(out);
    }

    public UndirectedGraph readInput() {
        int n = scanner.nextInt();
        int e = scanner.nextInt();

        UndirectedGraph graph = new UndirectedGraph(n);
        for (int i = 0; i < e; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();

            graph.addEdge(u, v);
        }

        return graph;
    }

    public static void main(String[] args) {
        new GreedySolver().setInput(System.in).run();
    }

}
