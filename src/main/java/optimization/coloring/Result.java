package optimization.coloring;

import graphs.UndirectedGraph;

import java.io.PrintWriter;
import java.util.List;

public class Result {

    private final int solution;
    private final boolean optimal;
    private final int[] colors;

    public Result(int solution, boolean optimal, int[] colors) {
        this.solution = solution;
        this.optimal = optimal;
        this.colors = colors;
    }

    public int getSolution() {
        return solution;
    }

    public boolean isOptimal() {
        return optimal;
    }

    public void outputTo(PrintWriter out) {
        out.print(solution);
        out.print(' ');
        out.print(optimal ? 1 : 0);
        out.println();

        for (int i = 0; i < colors.length; i++) {
            out.print(colors[i]);
            out.print(' ');
        }
        out.flush();
    }

    public boolean checkAgainst(UndirectedGraph graph) {
        List<Integer> allVertices = graph.allVertices();

        for (int v : allVertices) {
            for (int u : graph.adjacent(v)) {
                if (colors[v] == colors[u]) {
                    return false;
                }
            }
        }

        return true;
    }

}
