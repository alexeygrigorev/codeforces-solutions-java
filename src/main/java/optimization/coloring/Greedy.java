package optimization.coloring;

import graphs.UndirectedGraph;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * Новиков - дискретная математика для программистов, последняя глава
 * 
 * @author Grigorev Alexey
 */
public class Greedy {

    public Result solve(UndirectedGraph graph) {
        int[] colors = colorsArray(graph.size());
        int solution = color(graph, colors);
        return new Result(solution, false, colors);
    }

    private static int[] colorsArray(int n) {
        int[] colors = new int[n];
        Arrays.fill(colors, -1);
        return colors;
    }

    private int color(UndirectedGraph graph, int[] colors) {
        int color = 0;

        UndirectedGraph g = graph;
        while (g.notEmpty()) {
            Set<Integer> toRemove = Sets.newHashSet();

            List<Integer> nodes = g.nodesOrderedByDegree();

            for (Integer from : nodes) {
                boolean allGood = true;
                Collection<Integer> adjacent = g.adjacent(from);
                for (Integer to : adjacent) {
                    if (colors[to] == color) {
                        allGood = false;
                        break;
                    }
                }
                if (allGood) {
                    colors[from] = color;
                    toRemove.add(from);
                }
            }
            color++;
            g = g.removeVertices(toRemove);
        }
        return color;
    }

}
