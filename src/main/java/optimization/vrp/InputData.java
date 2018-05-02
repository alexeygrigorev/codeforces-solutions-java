package optimization.vrp;

import java.util.List;

public class InputData {

    private final int numberOfCustoments;
    private final int numberOfVehicles;
    private final int vehicleCapacity;
    private final Location warehouse;
    private final List<Location> locations;

    public InputData(int numberOfCustoments, int numberOfVehicles, int vehicleCapacity, Location warehouse, List<Location> locations) {
        this.numberOfCustoments = numberOfCustoments;
        this.numberOfVehicles = numberOfVehicles;
        this.vehicleCapacity = vehicleCapacity;
        this.warehouse = warehouse;
        this.locations = locations;
    }

    public int getNumberOfCustoments() {
        return numberOfCustoments;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public int getVehicleCapacity() {
        return vehicleCapacity;
    }

    public Location getWarehouse() {
        return warehouse;
    }

    public List<Location> getLocations() {
        return locations;
    }

}
