package optimization.vrp;

import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.lang3.Validate;

public class Result {

    private final InputData input;
    private final List<Vehicle> vehicles;
    private final boolean optimal;

    public Result(InputData input, List<Vehicle> vehicles, boolean optimal) {
        this.input = input;
        this.vehicles = vehicles;
        this.optimal = optimal;
    }

    public void outputTo(PrintWriter out) {
        out.print(calcCost());
        out.print(' ');
        out.print(optimal ? 1 : 0);
        out.print('\n');

        for (Vehicle vehicle : vehicles) {
            List<Location> locations = vehicle.getLocations();
            for (Location location : locations) {
                out.print(location.getIndex());
                out.print(' ');
            }
            out.print('\n');
        }

        out.flush();
    }

    public double calcCost() {
        double cost = 0.0;
        for (Vehicle vehicle : vehicles) {
            cost = cost + vehicle.calcCost();
        }
        return cost;
    }

    public void visualizeTo(String filename) {
        new Drawer(input, this, filename).visualize();
    }

    public void checkFeasibility() {
        int numberOfVehicles = input.getNumberOfVehicles();
        Validate.isTrue(vehicles.size() == numberOfVehicles, "vehicle list size %d != number of vehicles %d", vehicles.size(),
                numberOfVehicles);

        List<Location> locations = input.getLocations();
        for (Location location : locations) {
            Validate.isTrue(location.hasAssignedVehicle());
            Validate.isTrue(vehicles.contains(location.getVehicle()), "location's vehicle %d is not in the solution list", location
                    .getVehicle().getIndex());
        }
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

}
