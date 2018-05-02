package optimization.wl;

import java.io.PrintWriter;

import optimization.wl.InputData.Warehouse;

public class Result {

    private final InputData input;
    private final int[] assignment;
    private final boolean optimal;
    private final double objective;

    public Result(InputData input, int[] assignment, boolean optimal) {
        this.input = input;
        this.assignment = assignment;
        this.objective = calcObjective();
        this.optimal = optimal;
    }

    private double calcObjective() {
        double res = 0.0;
        for (Warehouse wh : input.getWarehouses()) {
            res = res + wh.calcCost();
        }
        return res;
    }

    public void checkFeasibility() {
        //  
    }

    public void outputTo(PrintWriter out) {
        out.print(objective);
        out.print(' ');
        out.print(optimal ? 1 : 0);
        out.println();

        for (int w : assignment) {
            out.print(w);
            out.print(' ');
        }

        out.flush();
    }

}
