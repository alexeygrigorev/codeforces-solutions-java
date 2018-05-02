package optimization.tsm;

import java.util.List;

public interface TspSolver {

    Result solve(List<Point> input);

}
