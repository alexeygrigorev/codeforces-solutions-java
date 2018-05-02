package coursera.algo1.week4;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableSet;

import static org.testng.Assert.assertEquals;

import coursera.ProblemRunner;
import coursera.algo1.week4.ProblemA;
import coursera.algo1.week4.UndirectedGraph;

public class ProblemATest {

    @Test
    public void readGraph() {
        UndirectedGraph graph = new UndirectedGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);

        assertEquals(graph.adjacent(1), Arrays.asList(2, 3));
        assertEquals(graph.reverse(3), Arrays.asList(1, 2));
    }

    @Test
    public void graphFromLecture() {
        UndirectedGraph graph = new UndirectedGraph(9);
        graph.addEdge(6, 0);
        graph.addEdge(3, 6);
        graph.addEdge(0, 3);

        graph.addEdge(8, 6);

        graph.addEdge(5, 8);
        graph.addEdge(8, 2);
        graph.addEdge(2, 5);

        graph.addEdge(7, 5);

        graph.addEdge(1, 7);
        graph.addEdge(4, 1);
        graph.addEdge(7, 4);

        ProblemA sut = new ProblemA();
        sut.setGraph(graph);

        sut.dfs1Loop();
        List<Integer> actualFinishedTime = asList(sut.getFinishingTime());
        List<Integer> expectedFinishingTime = Arrays.asList(6, 2, 0, 7, 1, 4, 8, 3, 5);
        assertEquals(actualFinishedTime, expectedFinishingTime);

        sut.dfs2Loop();

        List<Integer> actualLeaders = asList(sut.getLeaders());
        List<Integer> expectedLeaders = Arrays.asList(6, 7, 8, 6, 7, 8, 6, 7, 8);
        assertEquals(actualLeaders, expectedLeaders);

        assertEquals(sut.distinctLeaders().elementSet(), ImmutableSet.of(6, 7, 8));

        List<Integer> sortedSizes = sut.sortedSizes(5);
        assertEquals(sortedSizes, Arrays.asList(3, 3, 3, 0, 0));
    }

    @Test(enabled = false)
    public void readEntireGraph() {
        ProblemA sut = new ProblemA();
        ProblemRunner test = new ProblemRunner(sut).noDebuggingStdOutput();
        test.inputFromFile("SCC.txt");
        sut.setGraph(sut.readGraph(ProblemA.VERTEX_AMOUNT, ProblemA.LINES_AMOUNT));
        sut.dfs1Loop();
        sut.dfs2Loop();
        // no OutOfMemoryExceptions or StackOverflowExceptions
    }

    @Test
    public void assigment() {
        ProblemA sut = new ProblemA();
        ProblemRunner test = new ProblemRunner(sut).noDebuggingStdOutput();
        test.inputFromFile("SCC.txt");
        test.run();

        String output = test.getOutput();
        System.out.println("output: " + output);
        assertEquals(test.getOutput().trim(), "434821,968,459,313,211");
    }

    @Test(dataProvider = "testCasesFromForum")
    public void withTestCasesFromForum(int n, int[][] edges, List<Integer> answer) {
        UndirectedGraph graph = new UndirectedGraph(n);
        for (int[] pair : edges) {
            graph.addEdge(pair[0] - 1, pair[1] - 1);
        }

        ProblemA sut = new ProblemA();
        sut.setGraph(graph);

        sut.dfs1Loop();
        sut.dfs2Loop();

        List<Integer> sortedSizes = sut.sortedSizes(5);
        assertEquals(sortedSizes, answer);
    }

    @DataProvider
    public Object[][] testCasesFromForum() {
        return new Object[][] {
            { 8, 
                new int[][] { {1, 2}, {2, 3}, {2, 5}, {2, 6}, {3, 4}, {3, 7}, {4, 3}, 
                    {4, 8}, {5, 1}, {5, 6}, {6, 7}, {7, 6}, {8, 4}, {8, 7} },
                Arrays.asList(3, 3, 2, 0, 0)
            },
            { 11,
                new int[][] { {1, 5}, {2, 4}, {3, 1}, {3, 2}, {3, 6}, {4, 10}, {5, 3}, 
                    {6, 1}, {6, 10}, {7, 8}, {8, 9}, {8, 11}, {9, 3}, {9, 5}, {9, 7}, 
                    {10, 2}, {11, 2}, {11, 4} },
                Arrays.asList(4, 3, 3, 1, 0)
            },
            { 12,
                new int[][] { {1, 2}, {2, 3}, {2, 4}, {2, 5}, {3, 6}, {4, 5}, {4, 7}, 
                    {5, 2}, {5, 6}, {5, 7}, {6, 3}, {6, 8}, {7, 8}, {7, 10}, {8, 7}, 
                    {9, 7}, {10, 9}, {10, 11}, {11, 12}, {12, 10} },
                Arrays.asList(6, 3, 2, 1, 0)
            },
            { 10,
                new int[][] { {9, 7}, {7, 8}, {8, 9}, {6, 9}, {5, 6}, {1, 5}, {6, 1}, 
                    {4, 5}, {3, 4}, {2, 3}, {4, 2}, {10, 2} },
                Arrays.asList(3, 3, 3, 1, 0)
            },
            { 10,
                new int[][] { {9, 7}, {7, 8}, {8, 9}, {6, 9}, {5, 6}, {1, 5}, {6, 1}, 
                    {4, 5}, {3, 4}, {2, 3}, {4, 2}, {2, 10} },
                Arrays.asList(3, 3, 3, 1, 0)
            }, 
            { 50,
                new int[][] {{1, 8}, {1, 35}, {2, 15}, {2, 22}, {2, 23}, {2, 23}, {2, 46}, 
                    {2, 50}, {3, 20}, {3, 26}, {3, 34}, {4, 5}, {4, 18}, {4, 28}, {4, 37}, 
                    {4, 43}, {5, 18}, {5, 18}, {5, 28}, {6, 44}, {7, 14}, {7, 14}, {7, 29}, 
                    {7, 29}, {8, 42}, {8, 45}, {9, 20}, {9, 49}, {10, 10}, {10, 12}, {10, 31}, 
                    {10, 47}, {11, 1}, {11, 8}, {11, 29}, {11, 29}, {11, 29}, {11, 30}, 
                    {11, 30}, {11, 35}, {11, 42}, {12, 22}, {12, 31}, {13, 10}, {13, 12}, 
                    {13, 22}, {13, 22}, {13, 27}, {14, 23}, {14, 24}, {14, 48}, {15, 9}, 
                    {15, 22}, {15, 49}, {16, 9}, {16, 35}, {16, 50}, {17, 10}, {18, 21}, 
                    {18, 25}, {18, 39}, {19, 7}, {19, 29}, {19, 33}, {19, 43}, {20, 16}, 
                    {20, 41}, {21, 4}, {21, 36}, {21, 39}, {21, 47}, {23, 7}, {24, 12}, 
                    {24, 22}, {24, 23}, {25, 5}, {25, 6}, {25, 39}, {25, 44}, {26, 3}, 
                    {26, 35}, {27, 10}, {27, 13}, {27, 17}, {28, 6}, {28, 25}, {28, 32}, 
                    {28, 33}, {28, 43}, {29, 23}, {29, 30}, {29, 40}, {29, 45}, {30, 23}, 
                    {30, 50}, {31, 24}, {31, 38}, {32, 19}, {32, 33}, {33, 6}, {33, 7}, 
                    {33, 14}, {33, 38}, {33, 48}, {34, 3}, {34, 9}, {34, 20}, {35, 3}, 
                    {35, 20}, {35, 41}, {36, 10}, {36, 38}, {36, 47}, {37, 21}, {37, 43}, {38, 6}, 
                    {38, 10}, {38, 36}, {38, 48}, {38, 48}, {39, 36}, {39, 38}, {39, 39}, {40, 19}, 
                    {40, 43}, {41, 16}, {42, 29}, {42, 45}, {43, 32}, {44, 38}, {44, 39}, {45, 40}, 
                    {46, 9}, {46, 15}, {46, 15}, {46, 16}, {46, 50}, {47, 17}, {48, 24}, {48, 31}, 
                    {49, 13}, {49, 22}, {49, 34}, {49, 34}, {50, 23}, {50, 35}, {50, 50}},
                Arrays.asList(35, 7, 1, 1, 1)
            }, 
            { 50,
                new int[][] {{1, 8}, {1, 35}, {2, 15}, {2, 22}, {2, 23}, {2, 23}, {2, 46}, {2, 50}, 
                    {3, 20}, {3, 26}, {3, 26}, {3, 34}, {3, 34}, {4, 5}, {4, 18}, {4, 28}, {4, 37}, 
                    {4, 43}, {5, 18}, {5, 18}, {5, 28}, {5, 28}, {6, 44}, {7, 14}, {7, 14}, {7, 29}, 
                    {7, 29}, {8, 42}, {8, 45}, {9, 20}, {9, 49}, {10, 12}, {10, 31}, {10, 47}, {11, 1}, 
                    {11, 8}, {11, 29}, {11, 29}, {11, 29}, {11, 30}, {11, 30}, {11, 35}, {11, 42}, {12, 22}, 
                    {12, 31}, {13, 10}, {13, 12}, {13, 22}, {13, 22}, {13, 27}, {13, 27}, {14, 23}, 
                    {14, 24}, {14, 48}, {15, 9}, {15, 22}, {15, 49}, {16, 9}, {16, 35}, {16, 50}, {17, 10}, 
                    {18, 21}, {18, 25}, {18, 39}, {19, 7}, {19, 29}, {19, 33}, {19, 43}, {20, 16}, 
                    {20, 41}, {21, 4}, {21, 36}, {21, 39}, {21, 47}, {22, 23}, {23, 7}, {24, 12}, 
                    {24, 22}, {24, 23}, {25, 5}, {25, 6}, {25, 39}, {25, 44}, {26, 35}, {27, 10}, 
                    {27, 17}, {27, 49}, {28, 6}, {28, 25}, {28, 32}, {28, 33}, {28, 43}, {29, 23}, 
                    {29, 30}, {29, 40}, {29, 45}, {30, 23}, {30, 50}, {31, 24}, {31, 38}, {32, 19}, 
                    {32, 33}, {33, 6}, {33, 7}, {33, 14}, {33, 38}, {33, 48}, {34, 9}, {34, 20}, 
                    {35, 3}, {35, 20}, {35, 30}, {35, 41}, {36, 10}, {36, 47}, {37, 21}, {37, 43}, 
                    {38, 6}, {38, 10}, {38, 36}, {38, 36}, {38, 48}, {38, 48}, {39, 36}, {39, 38}, 
                    {40, 19}, {40, 43}, {41, 16}, {42, 29}, {42, 45}, {43, 32}, {44, 38}, {44, 39}, 
                    {45, 40}, {46, 9}, {46, 15}, {46, 15}, {46, 16}, {46, 50}, {47, 17}, {48, 24}, 
                    {48, 31}, {49, 13}, {49, 22}, {49, 34}, {49, 34}, {50, 23}, {50, 35}},
                Arrays.asList(36, 7, 1, 1, 1)
            },
            { 16,
                new int[][] {{1, 2}, {1, 6}, {1, 15}, {1, 16}, {2, 7}, {2, 11}, {3, 8}, {3, 9}, {3, 14}, 
                    {4, 16}, {5, 4}, {6, 4}, {6, 7}, {6, 8}, {7, 4}, {8, 7}, {8, 13}, {8, 16}, {9, 2}, 
                    {9, 14}, {10, 5}, {10, 11}, {10, 14}, {11, 9}, {12, 13}, {13, 5}, {13, 12}, 
                    {14, 5}, {14, 10}, {15, 1}, {15, 10}, {16, 6}, {16, 13}},
                Arrays.asList(8, 5, 2, 1, 0)
            }
        };
    }

    private static List<Integer> asList(int[] actual) {
        return Arrays.asList(ArrayUtils.toObject(actual));
    }
}
